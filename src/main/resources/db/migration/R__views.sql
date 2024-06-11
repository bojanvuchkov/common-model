Drop VIEW if exists events_view;

CREATE VIEW events_view AS
(
select concat('semester:', code) as id,
       'SEMESTER'                as type,
       code                      as name,
       ''                        as description,
       start_date::timestamp     as start_time,
       end_date::timestamp       as end_time,
       null                      as physical_location_name,
       null                      as online_url,
       code                      as reference_id,
       'Semester'                as reference_class
from semester
union
select concat('semester-enrollment:', code) as id,
       'SEMESTER'                           as type,
       code                                 as name,
       ''                                   as description,
       enrollment_start_date::timestamp     as start_time,
       enrollment_end_date::timestamp       as end_time,
       null                                 as physical_location_name,
       null                                 as online_url,
       code                                 as reference_id,
       'Semester'                           as reference_class
from semester);

Drop VIEW if exists teacher_allocation_stats_view;

create view teacher_allocation_stats_view as
(
select concat(tsa.professor_id, tsa.semester_code)                                      as id,
       tsa.professor_id                                                                 as professor_id,
       tsa.semester_code                                                                as semester_code,
       (select cast(SUM(case
                            when tsa1.number_of_lecture_groups < 1 then tsa1.number_of_lecture_groups
                            else 1
           end) as float)
        from teacher_subject_allocations tsa1
        where tsa1.professor_id = tsa.professor_id
          and tsa1.semester_code = tsa.semester_code
          and tsa1.number_of_lecture_groups > 0)                                        as number_of_lecture_subjects,
       (select cast(SUM(case
                            when tsa1.number_of_exercise_groups < 1 then tsa1.number_of_exercise_groups
                            else 1
           end) as float)
        from teacher_subject_allocations tsa1
        where tsa1.professor_id = tsa.professor_id
          and tsa1.semester_code = tsa.semester_code
          and tsa1.number_of_exercise_groups > 0)                                       as number_of_exercise_subjects,
       (select cast(SUM(case
                            when tsa1.number_of_lab_groups < 1 then tsa1.number_of_lab_groups
                            else 1
           end) as float)
        from teacher_subject_allocations tsa1
        where tsa1.professor_id = tsa.professor_id
          and tsa1.semester_code = tsa.semester_code
          and tsa1.number_of_lab_groups > 0)                                            as number_of_lab_subjects,
       (select cast(SUM(case
                            when tsa1.number_of_lecture_groups < 1 then tsa1.number_of_lecture_groups
                            else 1
           end) as float)
        from teacher_subject_allocations tsa1
        where tsa1.professor_id = tsa.professor_id
          and tsa1.semester_code = tsa.semester_code
          and tsa1.number_of_lecture_groups > 0
          and tsa1.english_group = true)                                                as number_of_lecture_en_groups,
       (select cast(SUM(case
                            when tsa1.number_of_lecture_groups < 1 then tsa1.number_of_lecture_groups
                            else 1
           end) as float)
        from teacher_subject_allocations tsa1
                 join teacher_subject_allocations tsa2
                      on tsa1.semester_code = tsa2.semester_code and tsa1.subject_id = tsa2.subject_id
                          and tsa1.english_group = true and tsa2.english_group = false
                          and tsa1.professor_id = tsa2.professor_id
        where tsa1.professor_id = tsa.professor_id
          and tsa1.semester_code = tsa.semester_code
          and tsa1.number_of_lecture_groups > 0)                                        as number_of_overlapping_en_subjects,
       (select cast(SUM(case
                            when tsa1.number_of_lecture_groups < 1 then tsa1.number_of_lecture_groups
                            else 1
           end) as float)
        from teacher_subject_allocations tsa1
        where tsa1.professor_id = tsa.professor_id
          and tsa1.semester_code = tsa.semester_code
          and tsa1.number_of_exercise_groups > 0
          and tsa1.english_group = true)                                                as number_of_exercise_en_groups,

       sum(tsa.number_of_exercise_groups)                                               as number_of_exercise_groups,
       sum(tsa.number_of_lab_groups)                                                    as number_of_lab_groups,
       sum(tsa.number_of_lecture_groups)                                                as number_of_lecture_groups,
       cast(sum(tsa.number_of_lecture_groups * sas.average_students_per_group) as int)  as total_lecture_students,
       cast(sum(tsa.number_of_exercise_groups * sas.average_students_per_group) as int) as total_exercise_students,
       cast(sum(tsa.number_of_lab_groups * sas.average_students_per_group) as int)      as total_lab_students,
       sum(tsa.number_of_lecture_groups * js.weekly_lectures_classes)                   as total_lecture_classes,
       sum(tsa.number_of_exercise_groups * js.weekly_auditorium_classes)                as total_exercise_classes,
       sum(tsa.number_of_lab_groups * js.weekly_lab_classes)                            as total_lab_classes,
       sum(tsa.number_of_lecture_groups * js.weekly_lectures_classes) +
       sum(tsa.number_of_exercise_groups * js.weekly_auditorium_classes) +
       sum(case
               when (cp.lab_exercises_as_consultations) then js.weekly_lab_classes
               else tsa.number_of_lab_groups * js.weekly_lab_classes
           end)                                                                         as total_classes
from teacher_subject_allocations tsa
         join joined_subject js on tsa.subject_id = js.abbreviation
         join subject_allocation_stats sas on js.abbreviation = sas.subject_id and sas.semester_code = tsa.semester_code
         left join course_preference cp on cp.subject_id = js.abbreviation
group by tsa.professor_id, tsa.semester_code
order by (total_classes) desc
    );




Drop VIEW if exists joined_subject_group_size;
Drop VIEW if exists course_size;

create view course_size as
(
select concat(sse.semester_code, '-', group_id, '-', joined_subject_abbreviation, '-',sg.english) as id,
       sse.semester_code,
       group_id,
       group_name,
       sg.english,
       joined_subject_abbreviation,
       sum(case when num_enrollments = 1 then 1 else 0 end)                   as first_time_enrollments,
       sum(case when num_enrollments > 1 then 1 else 0 end)                   as re_enrollments
from student_subject_enrollment sse join student_group sg on sse.group_id = sg.id
group by sse.semester_code, group_id, group_name, joined_subject_abbreviation, sg.english
order by sse.semester_code, group_id, joined_subject_abbreviation);