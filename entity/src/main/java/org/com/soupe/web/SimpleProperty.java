package org.com.soupe.web;

import java.io.Serializable;

public abstract class SimpleProperty implements Serializable {
	private static final long serialVersionUID = -3925929328684434068L;
	public boolean deleted;
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}



}
