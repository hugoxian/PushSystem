package com.xpush.android.xptp.dto;


/**
 * 
 * @author hugo
 * 
 */
public abstract class Packet {

	private String version;
	private String id;
	private String type;


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
