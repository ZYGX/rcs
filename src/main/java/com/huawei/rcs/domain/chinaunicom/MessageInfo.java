/*
 * MmsMessage.java
 *
 * Created on 14 mai 2002, 10:33
 */

package com.huawei.rcs.domain.chinaunicom;

/**
 * Encapsulates a MMS message
 *
 * @author  Nextenso R&D
 */
public class MessageInfo {

	/**
	 * text/plain：文本消息/地理位置回落消息 
	 * application/vnd.gsma.rcs-ft-http：文件消息 
	 * application/vnd.gsma.botmessage.v1.0+json：富 媒体卡片消息 
	 * application/vnd.gsma.botsuggestion.v1.0+json： 带建议回复和建议操作列表的消息 
	 */
	public String contentType;
	
	/**
	 * 消息内容(contentText)编码方式。 
	 * 倘若不指定，默认为utf8字符编码
	 */
	public String contentEncoding;
	
	public String contentTransferEncoding;
	
	/**
	 * contentType为”text/plain“ 内容为字符串 其余的类型 内容为json对象。
	 * 如果编码方式 为base64,则内容为base64转码之后的字符
	 */
	public Object contentText;
	
	public String trafficType; 
	
	/**
	 * 对于多消息体（如文本、文件、RichCard等） 
	 * 混合编码，各消息体都需要携带本参数，
	 * 用于 指示终端的展示方式，本参数在单条消息里面 唯一，
	 * RCS业务中应设置为”inline”，并携带” filename”参数，
	 * 如： filename=message  
	 * filename=”picture.gif”  
	 * filename=”Chiplist.lst” 
	 */
	public String contentDisposition;
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentTransferEncoding() {
		return contentTransferEncoding;
	}

	public void setContentTransferEncoding(String contentTransferEncoding) {
		this.contentTransferEncoding = contentTransferEncoding;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public String getContentEncoding() {
		return contentEncoding;
	}

	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}

	public Object getContentText() {
		return contentText;
	}

	public void setContentText(Object contentText) {
		this.contentText = contentText;
	}

	public String getTrafficType() {
		return trafficType;
	}

	public void setTrafficType(String trafficType) {
		this.trafficType = trafficType;
	}

	@Override
	public String toString() {
		return "MessageInfo [contentType=" + contentType + ", contentEncoding=" + contentEncoding
				+ ", contentTransferEncoding=" + contentTransferEncoding + ", contentText=" + contentText
				+ ", trafficType=" + trafficType + ", contentDisposition=" + contentDisposition + "]";
	}
}
