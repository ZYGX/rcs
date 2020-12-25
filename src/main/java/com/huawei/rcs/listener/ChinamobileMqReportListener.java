package com.huawei.rcs.listener;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.huawei.rcs.domain.chinamobile.ChinamobileMqReport;
import com.huawei.rcs.utils.DateUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;
import java.util.Date;

/**
 * @author zygx
 * @description RocketMQ监听 发送递送报告
 */

@Service
@RocketMQMessageListener(consumerGroup = "${rcs.rocketmq.consume-chinamobile-group}",
        topic = "${rcs.rocketmq.message-chinamobile-topic}",
        consumeMode= ConsumeMode.ORDERLY)
@Slf4j
@RefreshScope
public class ChinamobileMqReportListener implements RocketMQListener<MessageExt> {

    Logger mqErrorLog= LoggerFactory.getLogger("mq-error");

    @Value("${maap.service-url}")
    private String maapServiceUrl;

    @Value("${rcs.rocketmq.consumer.max-retry}")
    private Integer maxRetry=3;

    @SneakyThrows
    @Override
    public void onMessage(MessageExt messageExt) {
        String message = new String(messageExt.getBody());
        log.info("MQ receive message:{}",message);

        if(StringUtils.isNotEmpty(message)){
            try{
                ChinamobileMqReport report= JSON.parseObject(message,ChinamobileMqReport.class);
                if(report!=null){

                    String host="127.0.0.1";
                    if(StringUtils.isNotEmpty(maapServiceUrl)){
                        host=StringUtils.substringBetween(maapServiceUrl,"://","/");
                    }

                    String requestUrl=maapServiceUrl+report.getUrl()+report.getChatbotId();

                    HttpResponse httpResponse = HttpUtil.createPost(requestUrl)
                            .header(Header.ACCEPT, ContentType.XML.toString()).header(Header.CONTENT_TYPE, ContentType.XML.toString())
                            .header(Header.HOST,host).header(Header.DATE, DateUtils.getGmtTime(new Date()))
                            .header("Address",report.getAddress())
                            .header("X-Office-Code","HDN").header(Header.CONTENT_LENGTH,report.getMessage().length()+"")
                            .body(report.getMessage()).execute();

                    log.info("send report request to maap url:{},status:{},result:{}",requestUrl,httpResponse.getStatus(),httpResponse.body());

                    if(httpResponse.getStatus()>=HttpStatus.HTTP_BAD_REQUEST){
                        throw new UnexpectedException("get response status "+httpResponse.getStatus());
                    }

                }
            }catch (Throwable e){
                log.error("ChinaMobile RCS consume message error:",e);
                if(messageExt.getReconsumeTimes()>=maxRetry){
                    mqErrorLog.error(messageExt.toString());
                    return;
                }
                throw e;
            }
        }
    }

}
