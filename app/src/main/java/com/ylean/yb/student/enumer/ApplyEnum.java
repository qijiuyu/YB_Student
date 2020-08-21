package com.ylean.yb.student.enumer;

public enum ApplyEnum {
    批次申报成功(1),  其他(100);

    private final int value;

    ApplyEnum(int value) {
        this.value = value;
    }

    public static ApplyEnum valueOf(int value) {
        switch (value) {
            case 1:
                return 批次申报成功;
            default:
                return 其他;
        }
    }

    public int value() {
        return value;
    }
}