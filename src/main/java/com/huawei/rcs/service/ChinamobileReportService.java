package com.huawei.rcs.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.huawei.rcs.domain.chinamobile.ChinamobileMqReport;
import com.huawei.rcs.domain.chinamobile.DeliveryInfo;
import com.huawei.rcs.domain.chinamobile.DeliveryInfoNotification;
import com.huawei.rcs.domain.chinamobile.DeliveryInfoNotificationDto;
import com.huawei.rcs.domain.chinamobile.Link;
import com.huawei.rcs.domain.chinamobile.MessageStatusNotification;
import com.huawei.rcs.domain.chinamobile.MessageStatusReport;
import com.huawei.rcs.domain.chinamobile.OutboundMessageRequest;
import com.huawei.rcs.enums.EnumChinamobileReportStatus;
import com.huawei.rcs.utils.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


/**
 * @author: zygx
 * @description: 移动RCS发送递送报告service
 */
@Service
@Slf4j
@RefreshScope
public class ChinamobileReportService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rcs.rocketmq.message-chinamobile-topic}")
    private String messageChinaMobileRcsTopic;

    public void sendReport(String chatbotId,String text,String deliveryStatus){

        if(StrUtil.isNotBlank(text)){
            OutboundMessageRequest outboundMessageRequest= XmlUtil.convertToBean(text, OutboundMessageRequest.class);

            if(ObjectUtil.isNotNull(outboundMessageRequest)){
                //发送回执消息的发送方地址（原消息的目的方地址）
                List<String> addresses=outboundMessageRequest.getDestinationAddress();
                //源消息ID
                String messageId=outboundMessageRequest.getOutboundIMMessage().getMessageId();

                DeliveryInfo deliveryInfo=new DeliveryInfo();
                if(CollectionUtil.isNotEmpty(addresses)){
                    //拆分发送方地址分多个递送报告发送
                    addresses.parallelStream().forEach(addr -> {
                        deliveryInfo.setAddress(addr);
                        deliveryInfo.setMessageId(messageId);
                        deliveryInfo.setDeliveryStatus(deliveryStatus);

                        //发送失败的递送报告
                        if(EnumChinamobileReportStatus.DELIVERY_IMPOSSIBLE.getValue().equals(deliveryStatus)){
                            deliveryInfo.setDescription("SVC5001");
                        }

                        DeliveryInfoNotification deliveryInfoNotification=new DeliveryInfoNotification();
                        deliveryInfoNotification.setDeliveryInfo(deliveryInfo);

                        Link link=new Link();
                        link.setRel("OutboundMessageRequest");
                        //对应源消息resourceURL
                        link.setHref(outboundMessageRequest.getResourceURL());

                        deliveryInfoNotification.setLink(Arrays.asList(link));

                        ChinamobileMqReport report=new ChinamobileMqReport();
                        report.setChatbotId(chatbotId);

                        DeliveryInfoNotificationDto deliveryInfoNotificationDto=new DeliveryInfoNotificationDto();
                        BeanUtils.copyProperties(deliveryInfoNotification,deliveryInfoNotificationDto);
                        report.setMessage(XmlUtil.convertToXml(deliveryInfoNotificationDto,"deliveryInfoNotification","urn:oma:xml:rest:netapi:messaging:1"));
                        report.setAddress(addr);
                        report.setUrl("/DeliveryInfoNotification/");

                        SendResult sendResult=rocketMQTemplate.syncSendOrderly(messageChinaMobileRcsTopic,
                                MessageBuilder.withPayload(JSON.toJSONString(report)).build(),messageId,3000);
                        log.info("rcs syncSend MQ status:{}",sendResult.getSendStatus());

                    });
                }

            }

        }

    }

    public void sendRevokeReport(String chatbotId,String messageId,String text,String deliveryStatus){

        if(StrUtil.isNotBlank(text)){
            MessageStatusReport messageStatusReport= XmlUtil.convertToBean(text, MessageStatusReport.class);

            if(ObjectUtil.isNotNull(messageStatusReport)){
                //源消息接收方地址
                String address=messageStatusReport.getAddress();

                MessageStatusNotification messageStatusNotification=new MessageStatusNotification();
                messageStatusNotification.setAddress(address);
                messageStatusNotification.setMessageId(messageId);
                messageStatusNotification.setStatus(deliveryStatus);


                ChinamobileMqReport report=new ChinamobileMqReport();
                report.setChatbotId(chatbotId);
                report.setAddress(address);
                report.setMessage(XmlUtil.convertToXml(messageStatusNotification,"messageStatusNotification","urn:oma:xml:rest:netapi:messaging:1"));
                report.setUrl("/MessageStatusNotification/");

                SendResult sendResult=rocketMQTemplate.syncSendOrderly(messageChinaMobileRcsTopic,
                        MessageBuilder.withPayload(JSON.toJSONString(report)).build(),messageId,3000);
                log.info("rcs syncSend MQ status:{}",sendResult.getSendStatus());
            }

        }

    }

    public static void main(String[] args) {
        String text="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\n" +
                "    <address>tel:+8619585550103</address>\n" +
                "    <destinationAddress>tel:+8619585550103</destinationAddress>\n" +
                "    <senderAddress>sip:12599@botplatform.rcs.chinamobile.com</senderAddress>\n" +
                "    <outboundIMMessage>\n" +
                "        <contentType>multipart/mixed; boundary=\"next\"</contentType>\n" +
                "        <conversationID>XSFDEREW#\n" +
                "        </conversationID>\n" +
                "        <contributionID>SFF$#yyyy\n" +
                "        </contributionID>\n" +
                "        <serviceCapability>\n" +
                "            <capabilityId> ChatbotSA </capabilityId>\n" +
                "            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\n" +
                "        </serviceCapability>\n" +
                "        <messageId>5eae954c-42ca-4181-9ab4-9c0ef2e2ac66</messageId>\n" +
                "        <bodyText>\n" +
                "            <![CDATA[\n" +
                "--next\n" +
                "Content-Type: text/plain\n" +
                "Content-Disposition: inline; filename=\"Message\"\n" +
                "Content-Length: [content length]\n" +
                "\n" +
                "hello world\n" +
                "\n" +
                "--next\n" +
                "Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\n" +
                "Content-Disposition: inline; filename=\"Chiplist.lst\"\n" +
                "Content-Length: [content length]\n" +
                "\n" +
                "{\n" +
                "\"suggestions\": [\n" +
                "{\n" +
                "\"reply\": {\n" +
                "\"displayText\": \"Yes\",\n" +
                "\"postback\": {\n" +
                "\"data\": \"set_by_chatbot_reply_yes\"\n" +
                "}}},\n" +
                "{\n" +
                "\"reply\": {\n" +
                "\"displayText\": \"No\",\n" +
                "\"postback\": {\n" +
                "\"data\": \"set_by_chatbot_reply_no\"\n" +
                "}}},\n" +
                "{\n" +
                "\"action\": {\n" +
                "\"urlAction\": {\n" +
                "\"openUrl\": {\n" +
                "\"url\": \"https://www.google.com\"\n" +
                "}},\n" +
                "\"displayText\": \"Open website or deep link\",\n" +
                "\"postback\": {\n" +
                "\"data\": \"set_by_chatbot_open_url\"\n" +
                "}}},\n" +
                "{\n" +
                "\"action\": {\n" +
                "\"dialerAction\": {\n" +
                "\"dialPhoneNumber\": {\n" +
                "\"phoneNumber\": \"+1650253000\"\n" +
                "}},\n" +
                "\"displayText\": \"Call a phone number\",\n" +
                "\"postback\": {\n" +
                "\"data\": \"set_by_chatbot_open_dialer\"\n" +
                "}}}]}\n" +
                "--next--\n" +
                "  ]]>\n" +
                "        </bodyText>\n" +
                "    </outboundIMMessage>\n" +
                "    <clientCorrelator>567895</clientCorrelator>\n" +
                "    <resourceURL>http://example.com/exampleAPI/messaging/v1/outbound/sip%3A12599%40botplatform.rcs.chinamobile.com/requests/5eae954c-42ca-4181-9ab4-9c0ef2e2ac55</resourceURL>\n" +
                "</msg:outboundMessageRequest>";
        OutboundMessageRequest outboundMessageRequest=XmlUtil.convertToBean(text, OutboundMessageRequest.class);
        System.out.println(outboundMessageRequest.getDestinationAddress());
    }
}
