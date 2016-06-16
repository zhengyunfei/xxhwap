package com.xxhwap.bo;

/**
 * Created by Administrator on 2016/6/16.
 */
public class PictureEntity {
    private String pid;
    private String id;
    private String name;
    private String url;
    private String attachmentUrl;
    private String tum;
    private String remark;

    public PictureEntity() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public String getAttachmentUrl() {
        return this.attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getTum() {
        return this.tum;
    }

    public void setTum(String tum) {
        this.tum = tum;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }
}
