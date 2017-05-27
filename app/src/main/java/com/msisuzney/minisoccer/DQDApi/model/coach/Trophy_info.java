
package com.msisuzney.minisoccer.DQDApi.model.coach;


import java.util.List;

public class Trophy_info {

    private String competition_id;
    private String competition_name;
    private String times;
    private String trophy_img;
    private java.util.List<TrophyItemTime> lists = null;

    public String getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(String competition_id) {
        this.competition_id = competition_id;
    }

    public String getCompetition_name() {
        return competition_name;
    }

    public void setCompetition_name(String competition_name) {
        this.competition_name = competition_name;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTrophy_img() {
        return trophy_img;
    }

    public void setTrophy_img(String trophy_img) {
        this.trophy_img = trophy_img;
    }

    public List<TrophyItemTime> getLists() {
        return lists;
    }

    public void setLists(List<TrophyItemTime> lists) {
        this.lists = lists;
    }
}
