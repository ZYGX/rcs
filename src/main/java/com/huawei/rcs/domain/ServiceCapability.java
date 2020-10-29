/*
 * MmsMessage.java
 *
 * Created on 14 mai 2002, 10:33
 */

package com.huawei.rcs.domain;

/**
 * Encapsulates a MMS message
 *
 * @author  Nextenso R&D
 */
public class ServiceCapability {

	/**
	 * 值为 ChatbotSA
	 */
	public String capabilityId;
	
	/**
	 * 版本号，如 +g.gsma.rcs.botversion=&quot;#=1&quot; 
	 */
	public String version;

	public String getCapabilityId() {
		return capabilityId;
	}

	public void setCapabilityId(String capabilityId) {
		this.capabilityId = capabilityId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
