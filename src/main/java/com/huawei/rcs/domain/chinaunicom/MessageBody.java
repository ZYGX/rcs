/*
 * MmsMessage.java
 *
 * Created on 14 mai 2002, 10:33
 */

package com.huawei.rcs.domain.chinaunicom;

import java.util.List;

/**
 * Encapsulates a MMS message
 *
 * @author  Nextenso R&D
 */
public class MessageBody implements Cloneable{
	/**
	 * M：表示必选。 O：表示任意可选。 C：表示条件可选
	 */
	/**M
	 * 消息ID，建议采用UUID算法生成 
	 */
	public String messageId;
	
	/**C
	 * 消息列表。其携带的数据类型是由contentType, contentEncoding
	 * 和contentText组成的对象详情 请见消息内容结构体
	 */
	public List<MessageInfo> messageList;
	
	/**O
	 * 流量类型，可取值为
	 *  advertisement 
	 *  payment 
	 *  premium 
	 *  subscription 
	 *  Token（Token可用于类型扩展）
	 */
	public String trafficType;
	
	/**M
	 * 用户URI list，必须是tel格式 
	 */
	public List<String> destinationAddress;
	
	/**O
	 * 是否转短信。  false:不转，true:转，缺省false 
	 */
	public Object smsSupported;
	
	/**O
	 * 是否离线存储。 false:不存也不重试，true:存，缺省true 
	 */
	public Object storeSupported;
	
	/**M
	 * 发送方地址From，群发时填写Chatbot的URI， 广播时填写Chatbot的URI（暂不提供） 
	 */
	public String senderAddress;
	
	/**
	 * 业务能力，Chatbot版本号。消息结构参考表17“机器人业务标志
	 */
	public List<ServiceCapability> serviceCapability;
	
	/**C
	 * smsSupported为true时，本字段有效。为消息回 落时，终端展示的码号
	 */
	public String smsNumber;
	
	/**C
	 * smsSupported为true时，本字段有效。
	 * 如果 smsSupported为true且消息为纯文本、纯媒体 时，未设置本字段内容，则消息通过自动回落。
	 *  当消息内容为富媒体卡片,或带建议操作建议 回复的卡片消息时，则必须设置此参数
	 */
	public String smsContent;
	
	/**M
	 * 唯一标识主被叫用户间的一个聊天对话，建议 采用UUID
	 */
	public String conversationId;
	
	/**M
	 * 唯一标识一个会话中的一条消息，建议采用 UUID
	 */
	public String contributionId;

	/**O
	 * IM消息格式，可选值包括IM、LargerMode、 LargerMode， 
	 * 默认值为IM 
	 */
	public String imFormat;
	
	/**O
	 * 该标识是对另一条消息的回复，值是一条上行
	 *	消息的contributionId
	 */
	public String inReplyTo;
	
	/**O
	 * 状态事件报告列表，每个状态事件的可选值为:
	 *	sent：消息已发送
	 *	fail：消息发送失败
	 *	delivered：消息已送达
	 *	displayed：消息已阅读
	 *	deliveredToNetwork：已转短信发送
	 */
	public List<String> reportRequest;
	
	/**
	 * 值RevokeRequested，请求撤回消息 
	 */
	public String status; 
	
	/**M(MaaP平台收到消息主要分为普通上行消息、举报消息)
	 * 消息时间戳， YYYY-MM-DDTHH:MM:SS.FFF+08:00 
	 * 例如：2020-01-17T14:42:20.840+08:00 
	 */
	public String dateTime;
	
	/**O
	 * 消息优先级 
	 */
	public String priority; 
	
	/**O
	 * 原始消息发送方 
	 */
	public String origUser;
	
	/**O
	 * 返回的消息状态报告  
	 */
	public List<DeliveryInfo> deliveryInfoList;
	
	/**M(MaaP 平台发起能力查询 )
	 * 要发现手机用户能力时，填写被查询 的用户联系人ID列表 
	 * 例: " userContactList ": [  
	 * 				 "tel:+8617928222350",  
	 *  			 "tel:+8615067451862", 
	 *  		     "tel:+8615067451863"  
	 *  ] 
	 */
	public List<String> userContactList;
	
	
	public String type;
	public String result;
	public String time;
	public String description;
	public String remark;
	
  @Override
	public Object clone() throws CloneNotSupportedException {

        return super.clone();

    }
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public List<MessageInfo> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageInfo> messageList) {
		this.messageList = messageList;
	}

	public String getTrafficType() {
		return trafficType;
	}

	public void setTrafficType(String trafficType) {
		this.trafficType = trafficType;
	}

	public List<String> getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(List<String> destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public Object isSmsSupported() {
		return smsSupported;
	}

	public void setSmsSupported(boolean smsSupported) {
		this.smsSupported = smsSupported;
	}

	public Object isStoreSupported() {
		return storeSupported;
	}

	public void setStoreSupported(boolean storeSupported) {
		this.storeSupported = storeSupported;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public List<ServiceCapability> getServiceCapability() {
		return serviceCapability;
	}

	public void setServiceCapability(List<ServiceCapability> serviceCapability) {
		this.serviceCapability = serviceCapability;
	}

	public String getSmsNumber() {
		return smsNumber;
	}

	public void setSmsNumber(String smsNumber) {
		this.smsNumber = smsNumber;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getContributionId() {
		return contributionId;
	}

	public void setContributionId(String contributionId) {
		this.contributionId = contributionId;
	}

	public String getImFormat() {
		return imFormat;
	}

	public void setImFormat(String imFormat) {
		this.imFormat = imFormat;
	}

	public String getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(String inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public List<String> getReportRequest() {
		return reportRequest;
	}

	public void setReportRequest(List<String> reportRequest) {
		this.reportRequest = reportRequest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getOrigUser() {
		return origUser;
	}

	public void setOrigUser(String origUser) {
		this.origUser = origUser;
	}

	public List<DeliveryInfo> getDeliveryInfoList() {
		return deliveryInfoList;
	}

	public void setDeliveryInfoList(List<DeliveryInfo> deliveryInfoList) {
		this.deliveryInfoList = deliveryInfoList;
	}

	public List<String> getUserContactList() {
		return userContactList;
	}

	public void setUserContactList(List<String> userContactList) {
		this.userContactList = userContactList;
	}
	
}
