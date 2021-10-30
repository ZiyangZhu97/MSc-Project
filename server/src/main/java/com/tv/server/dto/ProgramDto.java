package com.tv.server.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProgramDto {
    private List<CategoryDto> categorys;

    /**
     * ID
     */
    private String id;

    /**
     * Title
     */
    private String title;

    /**
     * Summary
     */
    private String summary;

    /**
     * Length (Seconds)
     */
    private Integer length;

    /**
     * Price (GBP)
     */
    private BigDecimal price;

    /**
     * Cover
     */
    private String image;

    /**
     * Type|枚举[ProgramTypeEnum]：MOVIE("M", "Movie"), SERIES("S", "Series")
     */
    private String type;

    /**
     * Charge|枚举[ProgramChargeEnum]：CHARGE("C", "Charge"), FREE("F", "FREE")
     */
    private String charge;

    /**
     * Status|枚举[ProgramStatusEnum]：PUBLISH("P", "Publish"), DRAFT("D", "DRAFT")
     */
    private String status;

    /**
     * Number of Favorites
     */
    private Integer favorites;

    /**
     * Order
     */
    private Integer sort;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getFavorites() {
        return favorites;
    }

    public void setFavorites(Integer favorites) {
        this.favorites = favorites;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public List<CategoryDto> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategoryDto> categorys) {
        this.categorys = categorys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", summary=").append(summary);
        sb.append(", length=").append(length);
        sb.append(", price=").append(price);
        sb.append(", image=").append(image);
        sb.append(", type=").append(type);
        sb.append(", charge=").append(charge);
        sb.append(", status=").append(status);
        sb.append(", favorites=").append(favorites);
        sb.append(", sort=").append(sort);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }

}