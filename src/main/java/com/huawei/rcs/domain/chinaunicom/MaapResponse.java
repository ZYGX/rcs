package com.huawei.rcs.domain.chinaunicom;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaapResponse
{
	/**
	 * 消息ID 
	 */
	private String messageId;
	/**
	 * 消息状态码 0：成功 1：失败 
	 */
	private Integer errorCode;
	/**
	 * 状态描述
	 */
	private String errorMessage;
	/**
	 * 唯一标识主被叫用户间的一个聊天会话 
	 */
	private String conversationId;
	/**
	 * 唯一标识一个会话中的一条消息 
	 */
	private String contributionId;

}
