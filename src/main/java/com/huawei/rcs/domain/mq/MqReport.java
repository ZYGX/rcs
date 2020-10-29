package com.huawei.rcs.domain.mq;

import com.huawei.rcs.domain.DeliveryInfo;
import lombok.Data;

import java.util.List;

/**
 * @author zygx
 * @description MQ发送 递送报告消息接收类
 */

@Data
public class MqReport {

    private String apiVersion;

    private List<DeliveryInfo> deliveryInfoList;


}
