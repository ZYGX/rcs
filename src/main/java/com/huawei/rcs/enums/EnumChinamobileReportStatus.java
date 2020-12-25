package com.huawei.rcs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumChinamobileReportStatus {
    //消息已发送到平台
    MESSAGE_SENT("MessageSent"),
    //RCS消息达到终端
    DELIVERED_TO_TERMINAL("DeliveredToTerminal"),
    //发送失败
    DELIVERY_IMPOSSIBLE("DeliveryImpossible"),
    //已转短消息发送
    DELIVERED_TO_NETWORK("DeliveredToNetwork"),
    //消息已阅
    MESSAGE_DISPLAYED("MessageDisplayed"),
    REVOKED("Revoked"),
    REVOKEFAILED("RevokeFailed");

    private String value;

}
