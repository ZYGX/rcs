package com.huawei.rcs.domain.chinamobile;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

public class InboundMessage {
	private String destinationAddress;
	private String senderAddress;
	private String origUser;
	private String dateTime;
	private String resourceURL;
	@XStreamImplicit(itemFieldName="link")
	private List<Link> link;
	private String messageId;
	private String priority;
	private String imFormat;
	
	private String contentType;
	private String contentEncoding;
	private ServiceCapability serviceCapability;

	private String bodyText;
	
	private String conversationID;
	private String contributionID;
	private String inReplyToContributionID;
	@XStreamImplicit(itemFieldName="reportRequest")
	private List<String> reportRequest;
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
	
	public String getOrigUser() {
		return origUser;
	}
	public void setOrigUser(String origUser) {
		this.origUser = origUser;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getResourceURL() {
		return resourceURL;
	}
	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}
	public List<Link> getLink() {
		return link;
	}
	public void setLink(List<Link> link) {
		this.link = link;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getImFormat() {
		return imFormat;
	}
	public void setImFormat(String imFormat) {
		this.imFormat = imFormat;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContentEncoding() {
		return contentEncoding;
	}
	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}
	public ServiceCapability getServiceCapability() {
		return serviceCapability;
	}
	public void setServiceCapability(ServiceCapability serviceCapability) {
		this.serviceCapability = serviceCapability;
	}
	public String getBodyText() {
		return bodyText;
	}
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	public String getConversationID() {
		return conversationID;
	}
	public void setConversationID(String conversationID) {
		this.conversationID = conversationID;
	}
	public String getContributionID() {
		return contributionID;
	}
	public void setContributionID(String contributionID) {
		this.contributionID = contributionID;
	}
	public String getInReplyToContributionID() {
		return inReplyToContributionID;
	}
	public void setInReplyToContributionID(String inReplyToContributionID) {
		this.inReplyToContributionID = inReplyToContributionID;
	}
	public List<String> getReportRequest() {
		return reportRequest;
	}
	public void setReportRequest(List<String> reportRequest) {
		this.reportRequest = reportRequest;
	}
	
	
}
