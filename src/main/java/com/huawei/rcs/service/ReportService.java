package com.huawei.rcs.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huawei.rcs.constant.Constant;
import com.huawei.rcs.domain.chinaunicom.DeliveryInfo;
import com.huawei.rcs.domain.chinaunicom.MessageBody;
import com.huawei.rcs.domain.chinaunicom.mq.MqReport;
import com.huawei.rcs.enums.EnumReportStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: zygx
 * @description: 发送递送报告service
 */
@Service
@Slf4j
@RefreshScope
public class ReportService {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rcs.rocketmq.message-topic:message-topic}")
    private String messageRcsTopic;

    /**
     * 延时等级 messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    @Value("${rcs.rocketmq.delay-level:1}")
    private Integer delayLevel;

    /**
     * 发送递送报告
     * @param messageBody
     * @param status 递送报告状态
     * @param apiVersion api版本号
     */
    public void sendReport(MessageBody messageBody,String status,String apiVersion){
        if(messageBody==null){
            log.error("send report failed : messageBody is empty");
            return;
        }

        if(!CollectionUtils.isEmpty(messageBody.getDestinationAddress())){
            List<String> destinationAddress=messageBody.getDestinationAddress();
            destinationAddress.forEach(destAddress -> {
                DeliveryInfo deliveryInfo=new DeliveryInfo();
                deliveryInfo.setMessageId(messageBody.getMessageId());
                deliveryInfo.setStatus(status);
                if(EnumReportStatus.FAILED.getValue().equals(status)){
                    deliveryInfo.setErrorCode(2);
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("reason","SIP");
                    jsonObject.put("cause","580");
                    jsonObject.put("text","Precondition Failure");
                    deliveryInfo.setErrorMessage(jsonObject);
                }
                deliveryInfo.setDateTime(DateUtil.format(new Date(), Constant.formatTzzz));
                deliveryInfo.setDestinationAddress(messageBody.getSenderAddress());
                deliveryInfo.setSenderAddress(destAddress);

                List<DeliveryInfo> deliveryInfoList=new ArrayList<>();
                deliveryInfoList.add(deliveryInfo);

                //发送消息到mq
                MqReport mqReport=new MqReport();
                mqReport.setApiVersion(apiVersion);
                mqReport.setDeliveryInfoList(deliveryInfoList);
                SendResult sendResult=rocketMQTemplate.syncSend(messageRcsTopic,
                        MessageBuilder.withPayload(JSON.toJSONString(mqReport)).build(),3000,delayLevel);
                log.info("rcs syncSend MQ status:{}",sendResult.getSendStatus());

            });

        }

    }

}
