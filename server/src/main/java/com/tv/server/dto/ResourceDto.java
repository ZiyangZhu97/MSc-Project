package com.tv.server.dto;


public class ResourceDto {

    /**
     * ID
     */
    private String id;

    /**
     * Name|菜单、按钮等
     */
    private String name;

    /**
     * Page|路由
     */
    private String page;

    /**
     * Request|接口
     */
    private String request;

    /**
     * Parent ID
     */
    private String parent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", page=").append(page);
        sb.append(", request=").append(request);
        sb.append(", parent=").append(parent);
        sb.append("]");
        return sb.toString();
    }

}