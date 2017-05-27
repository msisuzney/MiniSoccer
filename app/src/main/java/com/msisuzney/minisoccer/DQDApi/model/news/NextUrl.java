package com.msisuzney.minisoccer.DQDApi.model.news;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by chenxin.
 * Date: 2017/5/15.
 * Time: 23:06.
 */

@Entity
public class NextUrl {
    @Id
    private Long newsId;
    private String nextUrl;
    @Generated(hash = 1763290707)
    public NextUrl(Long newsId, String nextUrl) {
        this.newsId = newsId;
        this.nextUrl = nextUrl;
    }
    @Generated(hash = 1019769964)
    public NextUrl() {
    }
    public Long getNewsId() {
        return this.newsId;
    }
    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }
    public String getNextUrl() {
        return this.nextUrl;
    }
    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

}
