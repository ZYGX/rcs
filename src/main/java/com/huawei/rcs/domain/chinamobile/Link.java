package com.huawei.rcs.domain.chinamobile;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class Link {
	
	@XStreamAsAttribute
	private String rel;
	@XStreamAsAttribute
	private String href;
	
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	
}
