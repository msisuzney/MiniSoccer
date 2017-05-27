package com.msisuzney.minisoccer.DQDApi.model.specialNews;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ArticleSpecial {
    @Id(autoincrement = true)
    private Long _id;
    private String id;
    private String title;
    private String description;
    private String thumb;
    private String published_at;
    private String channel;
    private String api;
    @Generated(hash = 1772080238)
    public ArticleSpecial(Long _id, String id, String title, String description,
            String thumb, String published_at, String channel, String api) {
        this._id = _id;
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumb = thumb;
        this.published_at = published_at;
        this.channel = channel;
        this.api = api;
    }
    @Generated(hash = 1148692526)
    public ArticleSpecial() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getThumb() {
        return this.thumb;
    }
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
    public String getPublished_at() {
        return this.published_at;
    }
    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }
    public String getChannel() {
        return this.channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getApi() {
        return this.api;
    }
    public void setApi(String api) {
        this.api = api;
    }


}
