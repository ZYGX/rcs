package com.huawei.rcs.listener;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.huawei.rcs.domain.chinaunicom.mq.MqReport;
import com.huawei.rcs.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;

/**
 * @author zygx
 * @description RocketMQ监听 发送递送报告
 */

@Service
@RocketMQMessageListener(consumerGroup = "${rcs.rocketmq.consume-group}", topic = "${rcs.rocketmq.message-topic}")
@Slf4j
@RefreshScope
public class MqReportListener implements RocketMQListener<String> {
    @Value("${maap.service-url}")
    private String maapServiceUrl;

    @Override
    public void onMessage(String message) {
        if(StringUtils.isNotEmpty(message)){
            log.info("MQ receive message:{}",message);
            MqReport mqReport= JSON.parseObject(message, MqReport.class);
            String host="127.0.0.1";
            if(StringUtils.isNotEmpty(maapServiceUrl)){
                host=StringUtils.substringBetween(maapServiceUrl,"://","/");
            }
            String apiVersion=mqReport.getApiVersion();
            mqReport.setApiVersion(null);
            String result=HttpUtil.createPost(maapServiceUrl+"/messaging/"+apiVersion+"/report")
                    .removeHeader("Accept-Encoding").header("Accept","application/json")
                    .header("Host",host).header("Date", DateUtils.getGmtTime(new Date()))
                    .body(JSON.toJSONString(mqReport)).execute().body();
            log.info("send report request to maap messageId:{},maap url:{},result:{}",
                    CollectionUtils.isEmpty(mqReport.getDeliveryInfoList())?null:mqReport.getDeliveryInfoList().get(0).getMessageId(),maapServiceUrl,result);

        }
    }
}
