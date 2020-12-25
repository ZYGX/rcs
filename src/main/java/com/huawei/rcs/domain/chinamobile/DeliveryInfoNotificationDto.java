package com.huawei.rcs.domain.chinamobile;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

public class DeliveryInfoNotificationDto implements Cloneable{
	private final static String headPrefix = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<msg:deliveryInfoNotification xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">"
			+ "";
	private final static String tailPrefix = "</msg:deliveryInfoNotification>";
	
	public static List<String> dstAddress = new ArrayList<String>();
    
	@XStreamAlias("deliveryInfo")
    private DeliveryInfo deliveryInfo;
	

	@XStreamImplicit(itemFieldName="link")
	private List<Link> link;

	public static String getHeadPrefix() {
		return headPrefix;
	}

	public static String getTailPrefix() {
		return tailPrefix;
	}

	public static List<String> getDstAddress() {
		return dstAddress;
	}

	public static void setDstAddress(List<String> dstAddress) {
		DeliveryInfoNotificationDto.dstAddress = dstAddress;
	}

	public DeliveryInfo getDeliveryInfo() {
		return deliveryInfo;
	}

	public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}

	public List<Link> getLink() {
		return link;
	}

	public void setLink(List<Link> link) {
		this.link = link;
	}
}
