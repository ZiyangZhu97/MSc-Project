package com.tv.server.dto;

public class ChapterPageDto extends PageDto {
    private String programId;

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChapterPageDto{");
        sb.append("programId='").append(programId).append('\'');
        sb.append(", page=").append(page);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
