package com.tv.server.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class FileDto {

    /**
     * ID
     */
    private String id;

    /**
     * Relative Path
     */
    private String path;

    /**
     * File Name
     */
    private String name;

    /**
     * File Suffix
     */
    private String suffix;

    /**
     * File Size (Byte)
     */
    private Integer size;

    /**
     * Created Time
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdAt;

    /**
     * Updated Time
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatedAt;

    private Integer shardIndex;

    private Integer shardSize;

    private Integer shardTotal;

    private String key;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getShardIndex() {
        return shardIndex;
    }

    public void setShardIndex(Integer shardIndex) {
        this.shardIndex = shardIndex;
    }

    public Integer getShardSize() {
        return shardSize;
    }

    public void setShardSize(Integer shardSize) {
        this.shardSize = shardSize;
    }

    public Integer getShardTotal() {
        return shardTotal;
    }

    public void setShardTotal(Integer shardTotal) {
        this.shardTotal = shardTotal;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", path=").append(path);
        sb.append(", name=").append(name);
        sb.append(", suffix=").append(suffix);
        sb.append(", size=").append(size);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }

}