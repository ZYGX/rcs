package com.huawei.rcs.domain.chinamobile;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;


public class OutboundIMMessage {
	private String contentType;
    private String contentEncoding;
    private String conversationID;
    private String contributionID;
    private String inReplyToContributionID;

	@XStreamImplicit(itemFieldName = "serviceCapability")
    private List<ServiceCapability> serviceCapability;

    private String fallbackContentType;
    private String fallbackContentEncoding;
    private String rcsBodyText;
    private String messageId;
    
    @XStreamImplicit(itemFieldName="reportRequest")
    private List<String> reportRequest;
	
	@XStreamAlias("shortMessageSupported")
    private Boolean shortMessageSupported;
	
	@XStreamAlias("storeSupported")
    private Boolean storeSupported;
	
	@XStreamAlias("fallbackSupported")
    private Boolean fallbackSupported;
	
	@XStreamAlias("trafficType")
    private String trafficType;
	
	@XStreamAlias("smsBodyText")
    private String smsBodyText;
	
	
    private String bodyText;

	public List<String> getReportRequest() {
		return reportRequest;
	}

	public void setReportRequest(List<String> reportRequest) {
		this.reportRequest = reportRequest;
	}

	public Boolean getShortMessageSupported() {
		return shortMessageSupported;
	}

	public void setShortMessageSupported(Boolean shortMessageSupported) {
		this.shortMessageSupported = shortMessageSupported;
	}

	public Boolean getStoreSupported() {
		return storeSupported;
	}

	public void setStoreSupported(Boolean storeSupported) {
		this.storeSupported = storeSupported;
	}

	public Boolean getFallbackSupported() {
		return fallbackSupported;
	}

	public void setFallbackSupported(Boolean fallbackSupported) {
		this.fallbackSupported = fallbackSupported;
	}

	public String getTrafficType() {
		return trafficType;
	}

	public void setTrafficType(String trafficType) {
		this.trafficType = trafficType;
	}

	public String getSmsBodyText() {
		return smsBodyText;
	}

	public void setSmsBodyText(String smsBodyText) {
		this.smsBodyText = smsBodyText;
	}
    public String getContentEncoding() {
		return contentEncoding;
	}

	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}

	public String getFallbackContentType() {
		return fallbackContentType;
	}

	public void setFallbackContentType(String fallbackContentType) {
		this.fallbackContentType = fallbackContentType;
	}

	public String getFallbackContentEncoding() {
		return fallbackContentEncoding;
	}

	public void setFallbackContentEncoding(String fallbackContentEncoding) {
		this.fallbackContentEncoding = fallbackContentEncoding;
	}

	public String getRcsBodyText() {
		return rcsBodyText;
	}

	public void setRcsBodyText(String rcsBodyText) {
		this.rcsBodyText = rcsBodyText;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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

	public List<ServiceCapability> getServiceCapability() {
		return serviceCapability;
	}

	public void setServiceCapability(List<ServiceCapability> serviceCapability) {
		this.serviceCapability = serviceCapability;
	}

	public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

	public String getInReplyToContributionID() {
		return inReplyToContributionID;
	}

	public void setInReplyToContributionID(String inReplyToContributionID) {
		this.inReplyToContributionID = inReplyToContributionID;
	}
}
