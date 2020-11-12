package com.ylean.yb.student.enumer;

public enum ApplyEnum {
    批次申报成功(1), 银行卡变更成功(2), 在校情况(3),申请补发(4),申请退还奖学金(5),  其他(100);

    private final int value;

    ApplyEnum(int value) {
        this.value = value;
    }

    public static ApplyEnum valueOf(int value) {
        switch (value) {
            case 1:
                return 批次申报成功;
            case 2:
                return 银行卡变更成功;
            case 3:
                return 在校情况;
            case 4:
                return 申请补发;
            case 5:
                return 申请退还奖学金;
            default:
                return 其他;
        }
    }

    public int value() {
        return value;
    }
}