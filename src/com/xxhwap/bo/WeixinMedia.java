package com.xxhwap.bo;

/**
 * Created by Administrator on 2016/6/2.
 */
public class WeixinMedia implements java.io.Serializable{
    private String type;
    private String mediaId;
    private int createdAt;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }
}
