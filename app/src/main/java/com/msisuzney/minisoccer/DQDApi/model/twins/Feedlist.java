
package com.msisuzney.minisoccer.DQDApi.model.twins;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Feedlist {
    @Id(autoincrement = true)
    private Long _id;
    private String id;
    private String account;
    private String relate_type;
    private String relate_ico;
    private String avatar;
    private String note;
    private String original_text;
    private String translation_text;
    private Integer comments_total;
    private String scheme;
    private String user_id;
    private String published_at;
    private String channel;
    private String pic_url;
    private Integer pic_width;
    private Integer pic_height;
    private String kind;
    @Transient
    private Album album;

    @Generated(hash = 1337160815)
    public Feedlist(Long _id, String id, String account, String relate_type,
            String relate_ico, String avatar, String note, String original_text,
            String translation_text, Integer comments_total, String scheme,
            String user_id, String published_at, String channel, String pic_url,
            Integer pic_width, Integer pic_height, String kind) {
        this._id = _id;
        this.id = id;
        this.account = account;
        this.relate_type = relate_type;
        this.relate_ico = relate_ico;
        this.avatar = avatar;
        this.note = note;
        this.original_text = original_text;
        this.translation_text = translation_text;
        this.comments_total = comments_total;
        this.scheme = scheme;
        this.user_id = user_id;
        this.published_at = published_at;
        this.channel = channel;
        this.pic_url = pic_url;
        this.pic_width = pic_width;
        this.pic_height = pic_height;
        this.kind = kind;
    }

    @Generated(hash = 492356827)
    public Feedlist() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRelate_type() {
        return relate_type;
    }

    public void setRelate_type(String relate_type) {
        this.relate_type = relate_type;
    }

    public String getRelate_ico() {
        return relate_ico;
    }

    public void setRelate_ico(String relate_ico) {
        this.relate_ico = relate_ico;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOriginal_text() {
        return original_text;
    }

    public void setOriginal_text(String original_text) {
        this.original_text = original_text;
    }

    public String getTranslation_text() {
        return translation_text;
    }

    public void setTranslation_text(String translation_text) {
        this.translation_text = translation_text;
    }

    public Integer getComments_total() {
        return comments_total;
    }

    public void setComments_total(Integer comments_total) {
        this.comments_total = comments_total;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getPic_url() {
        return this.pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public Integer getPic_width() {
        return this.pic_width;
    }

    public void setPic_width(Integer pic_width) {
        this.pic_width = pic_width;
    }

    public Integer getPic_height() {
        return this.pic_height;
    }

    public void setPic_height(Integer pic_height) {
        this.pic_height = pic_height;
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
