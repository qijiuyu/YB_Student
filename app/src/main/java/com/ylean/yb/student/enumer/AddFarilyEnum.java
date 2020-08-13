package com.ylean.yb.student.enumer;

public enum AddFarilyEnum {
    关系(0), 姓名(1), 单位(2),职位(3), 收入来源(4), 其他(100);

    private final int value;

    AddFarilyEnum(int value) {
        this.value = value;
    }

    public static AddFarilyEnum valueOf(int value) {
        switch (value) {
            case 0:
                return 关系;
            case 1:
                return 姓名;
            case 2:
                return 单位;
            case 3:
                return 职位;
            case 4:
                return 收入来源;
            default:
                return 其他;
        }
    }

    public int value() {
        return value;
    }
}