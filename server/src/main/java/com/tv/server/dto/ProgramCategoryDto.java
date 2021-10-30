package com.tv.server.dto;


public class ProgramCategoryDto {

    /**
     * ID
     */
    private String id;

    /**
     * Program|program.id
     */
    private String programId;

    /**
     * Category|program.id
     */
    private String categoryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", programId=").append(programId);
        sb.append(", categoryId=").append(categoryId);
        sb.append("]");
        return sb.toString();
    }

}