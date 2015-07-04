package com.web.soupe.web;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class SimpleProperty implements Serializable {
	private static final long serialVersionUID = -3925929328684434068L;

    @Column(name = "is_deleted")
    public boolean deleted;

    @Column(name = "create_time")
    public Date createTime;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
