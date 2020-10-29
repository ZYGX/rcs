package com.huawei.rcs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumReportStatus {

    FAILED("failed"),
    DELIVERED("delivered"),
    SENT("sent"),
    DISPLAYED("displayed"),
    DELIVEREDTONETWORK("deliveredToNetwork"),
    REVOKEOK("revokeOk"),
    REVOKEFAIL("revokeFail");

    private String value;

}
