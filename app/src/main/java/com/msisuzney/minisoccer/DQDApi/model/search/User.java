
package com.msisuzney.minisoccer.DQDApi.model.search;


public class User {

    private String id;
    private String username;
    private String avatar;
    private Object region;
    private String medal_id;
    private String gender;
    private Integer weight;
    private Object medal_url;
    private String up_total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Object getRegion() {
        return region;
    }

    public void setRegion(Object region) {
        this.region = region;
    }

    public String getMedal_id() {
        return medal_id;
    }

    public void setMedal_id(String medal_id) {
        this.medal_id = medal_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Object getMedal_url() {
        return medal_url;
    }

    public void setMedal_url(Object medal_url) {
        this.medal_url = medal_url;
    }

    public String getUp_total() {
        return up_total;
    }

    public void setUp_total(String up_total) {
        this.up_total = up_total;
    }

}
