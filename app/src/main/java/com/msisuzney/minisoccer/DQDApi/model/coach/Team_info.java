
package com.msisuzney.minisoccer.DQDApi.model.coach;


public class Team_info {

    private Integer team_id;
    private String team_name;
    private String team_img;
    private String type;

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_img() {
        return team_img;
    }

    public void setTeam_img(String team_img) {
        this.team_img = team_img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
