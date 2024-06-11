package mk.ukim.finki.wp.commonmodel.examschedule;

import lombok.Getter;
import mk.ukim.finki.wp.commonmodel.base.RoomType;

@Getter
public enum ExamType {
    LAB(RoomType.LAB),
    CLASSROOM(RoomType.CLASSROOM),
    ONLINE(RoomType.VIRTUAL),
    HOMEWORK(RoomType.VIRTUAL);

    private RoomType roomType;

    ExamType(RoomType roomType) {
        this.roomType = roomType;
    }
}
