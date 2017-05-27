package com.msisuzney.minisoccer.DQDApi.model.specialNewsColumn;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SpecialNewsColumnArticle {
    @Id(autoincrement = true)
    private Long _id;
    private String title;
    private String description;
    private Integer comments_total;
    private String litpic;
    private String aid;
    private Boolean show_comments;
    private String scheme;
    private Integer currrentPage;
    private Integer lastPage;
    private String main_title;
    private String main_description;
    private Boolean isViewed;
    private String id;
    private String main_logo;

    @Generated(hash = 376436605)
    public SpecialNewsColumnArticle(Long _id, String title, String description,
            Integer comments_total, String litpic, String aid,
            Boolean show_comments, String scheme, Integer currrentPage,
            Integer lastPage, String main_title, String main_description,
            Boolean isViewed, String id, String main_logo) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.comments_total = comments_total;
        this.litpic = litpic;
        this.aid = aid;
        this.show_comments = show_comments;
        this.scheme = scheme;
        this.currrentPage = currrentPage;
        this.lastPage = lastPage;
        this.main_title = main_title;
        this.main_description = main_description;
        this.isViewed = isViewed;
        this.id = id;
        this.main_logo = main_logo;
    }

    @Generated(hash = 725138927)
    public SpecialNewsColumnArticle() {
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

    public Integer getComments_total() {
        return comments_total;
    }

    public void setComments_total(Integer comments_total) {
        this.comments_total = comments_total;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public Boolean getShow_comments() {
        return show_comments;
    }

    public void setShow_comments(Boolean show_comments) {
        this.show_comments = show_comments;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Integer getCurrrentPage() {
        return this.currrentPage;
    }

    public void setCurrrentPage(Integer currrentPage) {
        this.currrentPage = currrentPage;
    }

    public Integer getLastPage() {
        return this.lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public String getMain_title() {
        return this.main_title;
    }

    public void setMain_title(String main_title) {
        this.main_title = main_title;
    }

    public String getMain_description() {
        return this.main_description;
    }

    public void setMain_description(String main_description) {
        this.main_description = main_description;
    }

    public Boolean getIsViewed() {
        return this.isViewed;
    }

    public void setIsViewed(Boolean isViewed) {
        this.isViewed = isViewed;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain_logo() {
        return this.main_logo;
    }

    public void setMain_logo(String main_logo) {
        this.main_logo = main_logo;
    }

}
