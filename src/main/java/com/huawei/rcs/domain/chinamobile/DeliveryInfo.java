package com.huawei.rcs.domain.chinamobile;

public class DeliveryInfo {
	private String address;
	private String messageId;
	
	/**
	 * MessageSent 消息已发送到平台，由MaaP产生。
		DeliveredToTerminal  RCS消息达到终端
		DeliveryImpossible  发送失败
		DeliveredToNetwork  已转短消息发送
		MessageDisplayed 消息已阅
	 */
	private String deliveryStatus;
	private String description;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
