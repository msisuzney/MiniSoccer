package com.msisuzney.minisoccer.DQDApi.model.news;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Article {
    @Id(autoincrement = true)
    private Long _id; //用来记录插入表时的顺序
    private String id;//文章的url地址中的id
    private int newsId; //文章的类别
    private String title;
    private String share_title;
    private String comments_total;
    private String share;
    private String thumb;
    private String top;
    private String top_color;
    private String url;
    private String url1;
    private String scheme;
    private String is_video;
    private String add_to_tab;
    private String show_comments;
    private String published_at;
    private String sort_timestamp;
    private String channel;
    private String label;
    private String label_color;
    private String description;
    private boolean isViewed;

    @Generated(hash = 1308380266)
    public Article(Long _id, String id, int newsId, String title,
            String share_title, String comments_total, String share, String thumb,
            String top, String top_color, String url, String url1, String scheme,
            String is_video, String add_to_tab, String show_comments,
            String published_at, String sort_timestamp, String channel,
            String label, String label_color, String description,
            boolean isViewed) {
        this._id = _id;
        this.id = id;
        this.newsId = newsId;
        this.title = title;
        this.share_title = share_title;
        this.comments_total = comments_total;
        this.share = share;
        this.thumb = thumb;
        this.top = top;
        this.top_color = top_color;
        this.url = url;
        this.url1 = url1;
        this.scheme = scheme;
        this.is_video = is_video;
        this.add_to_tab = add_to_tab;
        this.show_comments = show_comments;
        this.published_at = published_at;
        this.sort_timestamp = sort_timestamp;
        this.channel = channel;
        this.label = label;
        this.label_color = label_color;
        this.description = description;
        this.isViewed = isViewed;
    }

    @Generated(hash = 742516792)
    public Article() {
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

    public String getShare_title() {
        return this.share_title;
    }

    public void setShare_title(String share_title) {
        this.share_title = share_title;
    }

    public String getComments_total() {
        return this.comments_total;
    }

    public void setComments_total(String comments_total) {
        this.comments_total = comments_total;
    }

    public String getShare() {
        return this.share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getThumb() {
        return this.thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTop() {
        return this.top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getTop_color() {
        return this.top_color;
    }

    public void setTop_color(String top_color) {
        this.top_color = top_color;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl1() {
        return this.url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getScheme() {
        return this.scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getIs_video() {
        return this.is_video;
    }

    public void setIs_video(String is_video) {
        this.is_video = is_video;
    }

    public String getAdd_to_tab() {
        return this.add_to_tab;
    }

    public void setAdd_to_tab(String add_to_tab) {
        this.add_to_tab = add_to_tab;
    }

    public String getShow_comments() {
        return this.show_comments;
    }

    public void setShow_comments(String show_comments) {
        this.show_comments = show_comments;
    }

    public String getPublished_at() {
        return this.published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getSort_timestamp() {
        return this.sort_timestamp;
    }

    public void setSort_timestamp(String sort_timestamp) {
        this.sort_timestamp = sort_timestamp;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel_color() {
        return this.label_color;
    }

    public void setLabel_color(String label_color) {
        this.label_color = label_color;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNewsId() {
        return this.newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public Boolean getIsViewed() {
        return this.isViewed;
    }

    public void setIsViewed(Boolean isViewed) {
        this.isViewed = isViewed;
    }

    public void setIsViewed(boolean isViewed) {
        this.isViewed = isViewed;
    }


}
