
package com.msisuzney.minisoccer.DQDApi.model.search;


public class Author {

    private String username;
    private String avatar;
    private String id;
    private String medal_id;
    private Object medal_desc;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedal_id() {
        return medal_id;
    }

    public void setMedal_id(String medal_id) {
        this.medal_id = medal_id;
    }

    public Object getMedal_desc() {
        return medal_desc;
    }

    public void setMedal_desc(Object medal_desc) {
        this.medal_desc = medal_desc;
    }

}
