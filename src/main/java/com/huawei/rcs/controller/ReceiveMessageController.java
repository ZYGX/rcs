package com.huawei.rcs.controller;

import com.huawei.rcs.domain.chinaunicom.MaapResponse;
import com.huawei.rcs.domain.chinaunicom.MessageBody;
import com.huawei.rcs.enums.EnumReportStatus;
import com.huawei.rcs.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zygx
 * @description : 模拟RCS提供压力，包括延时递送报告，模拟错误等
 */
@RestController
@RefreshScope
@RequiredArgsConstructor
@Slf4j
public class ReceiveMessageController {

    @Value("${rcs.times-required-to-fail-once}")
    private int timeRequiredToFailOnce;

    private final ReportService reportService;

    /**
     * RCS 接收Maap消息
     * @param messageBody
     * @param apiVersion
     * @param chatbotId
     * @return
     */
    @PostMapping(value = "/messaging/{apiVersion}/{chatbotId}/submit")
    public ResponseEntity<MaapResponse> submit(@RequestBody MessageBody messageBody,
                                              @PathVariable(value = "apiVersion") String apiVersion,
                                              @PathVariable(value = "chatbotId") String chatbotId){

        StopWatch stopWatch=new StopWatch();
        stopWatch.start();

        MaapResponse maapResponse=new MaapResponse();

        //i 默认-1:永远不会报错
        int i=-1;
        if(timeRequiredToFailOnce>0){
            i=RandomUtils.nextInt(0,timeRequiredToFailOnce);
        }

        if(i==0){
            //模拟错误消息响应，返回码400
            maapResponse.setErrorMessage("other error");
            maapResponse.setMessageId(messageBody.getMessageId());
            maapResponse.setContributionId(messageBody.getContributionId());
            maapResponse.setConversationId(messageBody.getConversationId());
            stopWatch.stop();
            log.warn("rcs return 400 Bad Request,consume :{} ms",stopWatch.getTotalTimeMillis());
            return ResponseEntity.badRequest().body(maapResponse);
        }

        maapResponse.setMessageId(messageBody.getMessageId());
        maapResponse.setContributionId(messageBody.getContributionId());
        maapResponse.setConversationId(messageBody.getConversationId());

        if(i==(timeRequiredToFailOnce-1)){
            //模拟错误的递送报告，返回码201
            reportService.sendReport(messageBody, EnumReportStatus.FAILED.getValue(),apiVersion);
        }else{
            //正确递送报告，返回码201
            reportService.sendReport(messageBody, EnumReportStatus.DELIVERED.getValue(),apiVersion);
            reportService.sendReport(messageBody, EnumReportStatus.DISPLAYED.getValue(),apiVersion);
        }
        stopWatch.stop();
        log.info("rcs return 201 Created,consume :{} ms",stopWatch.getTotalTimeMillis());
        return ResponseEntity.status(HttpStatus.CREATED).body(maapResponse);
    }

    /**
     * RCS 接收Maap撤回消息
     * @param messageBody
     * @param apiVersion
     * @param chatbotId
     * @return
     */
    @PostMapping("/messaging/{apiVersion}/{chatbotId}/revoke")
    public ResponseEntity<MaapResponse> revoke(@RequestBody MessageBody messageBody,
                                               @PathVariable(value = "apiVersion") String apiVersion,
                                               @PathVariable(value = "chatbotId") String chatbotId){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        MaapResponse maapResponse=new MaapResponse();
        //i 默认-1:永远不会报错
        int i=-1;
        if(timeRequiredToFailOnce>0){
            i=RandomUtils.nextInt(0,timeRequiredToFailOnce);
        }

        if(i==0){
            //模拟错误消息，返回码400
            maapResponse.setErrorMessage("other error");
            maapResponse.setMessageId(messageBody.getMessageId());
            stopWatch.stop();
            log.warn("rcs return 400 Bad Request,consume :{} ms",stopWatch.getTotalTimeMillis());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(maapResponse);
        }

        maapResponse.setMessageId(messageBody.getMessageId());

        if(i==(timeRequiredToFailOnce-1)){
            //模拟错误的递送报告，返回码201
            reportService.sendReport(messageBody, EnumReportStatus.REVOKEFAIL.getValue(),apiVersion);
        }else{
            //正确递送报告，返回码201
            reportService.sendReport(messageBody, EnumReportStatus.REVOKEOK.getValue(),apiVersion);
        }
        stopWatch.stop();
        log.info("rcs return 201 Created,consume :{} ms",stopWatch.getTotalTimeMillis());
        return ResponseEntity.status(HttpStatus.CREATED).body(maapResponse);

    }

    /**
     * RCS 接收Maap递送报告
     * @param text
     * @param apiVersion
     * @return
     */
    @PostMapping("/messaging/{apiVersion}/report")
    public ResponseEntity<MaapResponse> report(@RequestBody String text,
                                               @PathVariable(value = "apiVersion") String apiVersion){
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
