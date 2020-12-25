package com.huawei.rcs.domain.chinamobile;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;


@XStreamAlias("outboundMessageRequest")
public class OutboundMessageRequest{
	private final static String headPrefix = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">"
			+ "";
	private final static String tailPrefix = "</msg:outboundMessageRequest>";
	
	@XStreamAlias("address")
	private String address;
	
	@XStreamImplicit(itemFieldName="destinationAddress")
    private List<String> destinationAddress;
	
	@XStreamAlias("senderAddress")
    private String senderAddress;
    
	@XStreamAlias("outboundIMMessage")
    private OutboundIMMessage outboundIMMessage;
	
	@XStreamAlias("imFormat")
    private String imFormat;
	
	@XStreamAlias("clientCorrelator")
    private String clientCorrelator;
	
	@XStreamAlias("resourceURL")
    private String resourceURL;
	
	@XStreamAlias("deliveryInfoList")
    private DeliveryInfoList deliveryInfoList;

	public static String getHeadPrefix() {
		return headPrefix;
	}

	public static String getTailPrefix() {
		return tailPrefix;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(List<String> destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public OutboundIMMessage getOutboundIMMessage() {
		return outboundIMMessage;
	}

	public void setOutboundIMMessage(OutboundIMMessage outboundIMMessage) {
		this.outboundIMMessage = outboundIMMessage;
	}

	public String getImFormat() {
		return imFormat;
	}

	public void setImFormat(String imFormat) {
		this.imFormat = imFormat;
	}

	public String getClientCorrelator() {
		return clientCorrelator;
	}

	public void setClientCorrelator(String clientCorrelator) {
		this.clientCorrelator = clientCorrelator;
	}

	public String getResourceURL() {
		return resourceURL;
	}

	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

	public DeliveryInfoList getDeliveryInfoList() {
		return deliveryInfoList;
	}

	public void setDeliveryInfoList(DeliveryInfoList deliveryInfoList) {
		this.deliveryInfoList = deliveryInfoList;
	}
}
