package com.huawei.rcs.domain.chinamobile;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.ArrayList;
import java.util.List;


@XStreamAlias("inboundMessageNotification")
public class InboundMessageNotification{
	private final static String headPrefix = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<msg:inboundMessageNotification xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">"
			+ "";
	private final static String tailPrefix = "</msg:inboundMessageNotification>";
	
	
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
	public static List<String> dstAddress = new ArrayList<String>();
    
	@XStreamAlias("inboundMessage")
    private InboundMessage inboundMessage;

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

	public static List<String> getDstAddress() {
		return dstAddress;
	}

	public static void setDstAddress(List<String> dstAddress) {
		InboundMessageNotification.dstAddress = dstAddress;
	}

	public InboundMessage getInboundMessage() {
		return inboundMessage;
	}

	public void setInboundMessage(InboundMessage inboundMessage) {
		this.inboundMessage = inboundMessage;
	}
}
