/*
 * MmsMessage.java
 *
 * Created on 14 mai 2002, 10:33
 */

package com.huawei.rcs.domain;

/**
 * Encapsulates a MMS message
 *
 * @author  Nextenso R&D
 */
public class DeliveryInfo {
	
	/**M
	 * 消息状态对应的那条消息的消息ID，uuid生成方 式生成
	 */
	public String messageId;
	
	/**M
	 * 消息时间戳， YYYY-MM-DDTHH:MM:SS.FFF+08:00 
	 * 例如：2020-01-17T14:42:20.840+08:00 
	 */
	public String dateTime;
	
	/**M
	 * 发送方用户URI ，tel格式 
	 */
	public String senderAddress;
	
	/**C
	 * 如果状态为fail，则需要带此参数。包含以下值：
 		1：未找到终端； 2：非5G消息终端； 3：终端离线； 4：终端已拒收
	 */
	public Integer errorCode;
	
	/**C
	 * 如果状态为fail，则需要带此参数。此参数表明发 送失败的原因。
	 * 比如说：未找到终端；终端不支 持且本消息不支持回落等等 
	 */
	public Object errorMessage;
	
	/**
	 * 消息状态，主要有如下几种状态： 
	 * sent：消息已发送
	 *  fail：消息发送失败 
	 *  delivered：消息已送达 
	 *  displayed：消息已阅读 
	 *  revokeOk：消息撤回成功
	 *   revokeFail：消息撤回失败 
	 *   deliveredToNetwork：已转短信发送 
	 */
	public String status;
	
	public String destinationAddress;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}


	public Object getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(Object errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
