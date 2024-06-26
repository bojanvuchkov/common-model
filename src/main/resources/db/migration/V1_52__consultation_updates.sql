alter table if exists consultation
    add column if not exists status varchar(255);

create table if not exists consultation_attendance
(
    id                     bigint generated by default as identity not null,
    attendee_student_index varchar(255)
        constraint fk_consultation_attendance_on_attendee_student_index references student (student_index),
    consultation_id        bigint
        constraint fk_consultation_attendance_on_consultation references consultation (id),
    comment                varchar(1000),
    constraint pk_consultation_attendance primary key (id)
);

create table if not exists consultation_canceled_dates
(
    consultation_id bigint not null
        constraint pk_consultation_canceled_dates primary key,
    canceled_dates  date   not null
);



DO
$$
    BEGIN
        alter table if exists consultation
            rename column "date" to one_time_date;
    EXCEPTION
        WHEN undefined_column THEN raise notice 'date not exist';
    END;
$$;

DO
$$
    BEGIN
        alter table if exists consultation
            rename column day_of_week to weekly_day_of_week;
    EXCEPTION
        WHEN undefined_column THEN raise notice 'date not exist';
    END;
$$;
