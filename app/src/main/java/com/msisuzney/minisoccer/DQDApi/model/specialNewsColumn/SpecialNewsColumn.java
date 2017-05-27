
package com.msisuzney.minisoccer.DQDApi.model.specialNewsColumn;

import java.util.List;

public class SpecialNewsColumn {

    private String id;
    private Integer total;
    private Integer per_page;
    private Integer current_page;
    private Integer last_page;
    private String banner;
    private String avatar;
    private String title;
    private String description;
    private Boolean is_follow;
    private List<SpecialNewsColumnArticle> data = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public Integer getLast_page() {
        return last_page;
    }

    public void setLast_page(Integer last_page) {
        this.last_page = last_page;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(Boolean is_follow) {
        this.is_follow = is_follow;
    }

    public List<SpecialNewsColumnArticle> getData() {
        return data;
    }

    public void setData(List<SpecialNewsColumnArticle> data) {
        this.data = data;
    }

}
