package com.huawei.rcs.domain.chinamobile;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;


public class MessageStatusNotification {
	private final static String headPrefix = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<msg:messageStatusReport xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">"
			+ "";
	private final static String tailPrefix = "</msg:messageStatusReport>";
	
	
	public String messageId;

	public static List<Link> link = new ArrayList<>();
		
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

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public static List<Link> getLink() {
		return link;
	}

	public static void setLink(List<Link> link) {
		MessageStatusNotification.link = link;
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
