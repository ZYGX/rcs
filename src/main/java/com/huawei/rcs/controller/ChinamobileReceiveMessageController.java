package com.huawei.rcs.controller;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import com.huawei.rcs.enums.EnumChinamobileReportStatus;
import com.huawei.rcs.service.ChinamobileReportService;
import com.huawei.rcs.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author : zygx
 * @description : 模拟移动RCS提供压力，包括延时递送报告，模拟错误等
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ChinamobileReceiveMessageController {

    private final ChinamobileReportService chinamobileReportService;

    @Value("${rcs.times-required-to-fail-once}")
    private int timeRequiredToFailOnce;

    /**
     * 接收maap下行消息
     *
     * @return
     */
    @PostMapping(value = "/messaging/{apiVersion}/outbound/{userId}/requests")
    public ResponseEntity inboundRequests(HttpServletRequest request,
                                          @PathVariable(value = "apiVersion") String apiVersion,
                                          @PathVariable(value = "userId") String userId,
                                          @RequestBody String text) {

        log.info("chinamobile rcs recieve maap |  apiVersion:{}, userId:{}, message:{}", apiVersion, userId, text);

        //i 默认-1:永远不会报错
        int i = -1;
        if (timeRequiredToFailOnce > 0) {
            i = RandomUtils.nextInt(0, timeRequiredToFailOnce);
        }

        if (i == 0) {
            //发送失败递送报告
            chinamobileReportService.sendReport(userId, text, EnumChinamobileReportStatus.DELIVERY_IMPOSSIBLE.getValue());
        } else {
            //发送成功递送报告
            chinamobileReportService.sendReport(userId, text, EnumChinamobileReportStatus.DELIVERED_TO_TERMINAL.getValue());
            chinamobileReportService.sendReport(userId, text, EnumChinamobileReportStatus.MESSAGE_DISPLAYED.getValue());
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(Header.DATE.toString(), DateUtils.getGmtTime(new Date()));
        httpHeaders.set(Header.LOCATION.toString(), request.getRequestURL().toString());
        httpHeaders.set(Header.CONTENT_TYPE.toString(), ContentType.XML.toString());
        httpHeaders.set(Header.CONTENT_LENGTH.toString(), text == null ? "0" : (text.length() + ""));

        return new ResponseEntity(text, httpHeaders, HttpStatus.CREATED);
    }

    /**
     * 接收maap下行状态报告
     *
     * @return
     */
    @RequestMapping(value = "/messaging/{apiVersion}/inbound/registrations/{userId}/messages/{messageId}/status",
            method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity inboundStatus(@PathVariable(value = "apiVersion") String apiVersion,
                                        @PathVariable(value = "userId") String userId,
                                        @PathVariable(value = "messageId") String messageId,
                                        @RequestBody String text) {

        log.info("chinamobile rcs recieve maap |  apiVersion:{}, userId:{}, messageId:{}, message:{}", apiVersion, userId, messageId, text);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(Header.DATE.toString(), DateUtils.getGmtTime(new Date()));

        return new ResponseEntity(httpHeaders, HttpStatus.NO_CONTENT);
    }


    /**
     * 接收maap下行撤回消息
     *
     * @return
     */
    @RequestMapping(value = "/messaging/{apiVersion}/outbound/{userId}/requests/{messageId}/status",
            method = {RequestMethod.POST, RequestMethod.PUT})

    public ResponseEntity revoke(@PathVariable(value = "apiVersion") String apiVersion,
                                 @PathVariable(value = "userId") String userId,
                                 @PathVariable(value = "messageId") String messageId,
                                 @RequestBody String text) {

        log.info("chinamobile rcs recieve maap |  apiVersion:{}, userId:{}, messageId:{}, message:{}", apiVersion, userId, messageId, text);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(Header.DATE.toString(), DateUtils.getGmtTime(new Date()));

        //i 默认-1:永远不会报错
        int i = -1;
        if (timeRequiredToFailOnce > 0) {
            i = RandomUtils.nextInt(0, timeRequiredToFailOnce);
        }
        if (i == 0) {
            //发送失败递送报告
            chinamobileReportService.sendRevokeReport(userId, messageId, text, EnumChinamobileReportStatus.REVOKEFAILED.getValue());
        } else {
            //发送成功递送报告
            chinamobileReportService.sendRevokeReport(userId, messageId, text, EnumChinamobileReportStatus.REVOKED.getValue());
        }

        return new ResponseEntity(httpHeaders, HttpStatus.NO_CONTENT);
    }
}
