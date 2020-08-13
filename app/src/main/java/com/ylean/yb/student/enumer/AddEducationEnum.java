package com.ylean.yb.student.enumer;

public enum AddEducationEnum {
    学校类型(0), 省(1), 市(2),区(3), 学校名称(4),入学时间(5), 其他(100);

    private final int value;

    AddEducationEnum(int value) {
        this.value = value;
    }

    public static AddEducationEnum valueOf(int value) {
        switch (value) {
            case 0:
                return 学校类型;
            case 1:
                return 省;
            case 2:
                return 市;
            case 3:
                return 区;
            case 4:
                return 学校名称;
            case 5:
                return 入学时间;
            default:
                return 其他;
        }
    }

    public int value() {
        return value;
    }
}