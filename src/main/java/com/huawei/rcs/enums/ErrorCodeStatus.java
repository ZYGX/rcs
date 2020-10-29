package com.huawei.rcs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeStatus {

    UNKNOW(0,"其他未明确的原因"),
    UE_NOT_FOUND(1,"未找到终端"),
    NOT_5G(2,"非5G消息终端"),
    UE_OUT_OF_LINE(3,"终端离线"),
    UE_REFUSED(4,"终端已拒收");

    private Integer code;
    private String value;

}
