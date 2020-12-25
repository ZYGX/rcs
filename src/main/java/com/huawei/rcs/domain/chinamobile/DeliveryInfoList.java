package com.huawei.rcs.domain.chinamobile;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

public class DeliveryInfoList {
	private String resourceURL;
	
	@XStreamImplicit(itemFieldName="deliveryInfo")
	private List<DeliveryInfo> deliveryInfo;

	public String getResourceURL() {
		return resourceURL;
	}

	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

	public List<DeliveryInfo> getDeliveryInfo() {
		return deliveryInfo;
	}

	public void setDeliveryInfo(List<DeliveryInfo> deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}
	
	
	
}
