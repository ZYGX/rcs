package com.huawei.rcs.domain.chinamobile;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.ArrayList;
import java.util.List;


@XStreamAlias("messageStatusReport")
public class MessageStatusReport {
	private final static String headPrefix = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<msg:messageStatusReport xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">"
			+ "";
	private final static String tailPrefix = "</msg:messageStatusReport>";
	
	
	//消息类型标识
	@XStreamOmitField
	public int type = 0;
	@XStreamOmitField
	public String chatbotId;
	@XStreamOmitField
	public String apiVersion;
	@XStreamOmitField
	public String serverRoot;
	@XStreamOmitField
	public String destUrl;
	@XStreamOmitField
	public String messageId;
	@XStreamOmitField
	public static List<String> dstAddress = new ArrayList<String>();
		
	@XStreamAlias("address")
	private String address;
	
	@XStreamAlias("status")
    private String status;

	public static String getHeadPrefix() {
		return headPrefix;
	}

	public static String getTailPrefix() {
		return tailPrefix;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getChatbotId() {
		return chatbotId;
	}

	public void setChatbotId(String chatbotId) {
		this.chatbotId = chatbotId;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getServerRoot() {
		return serverRoot;
	}

	public void setServerRoot(String serverRoot) {
		this.serverRoot = serverRoot;
	}

	public String getDestUrl() {
		return destUrl;
	}

	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public static List<String> getDstAddress() {
		return dstAddress;
	}

	public static void setDstAddress(List<String> dstAddress) {
		MessageStatusReport.dstAddress = dstAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
