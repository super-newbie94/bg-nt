package com.bgnt.spring;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * User: GaoYuan
 * Date: 17/11/21
 * Time: 11:15
 */
public class BaseBo implements Serializable {
    private static final long serialVersionUID = 1048732770455109215L;
    private String created;

    private Timestamp updatedTime;

    private Timestamp createdTime;

    private String updated;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
