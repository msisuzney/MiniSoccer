
package com.msisuzney.minisoccer.DQDApi.model.coach;

import java.util.List;

public class Coach {

    private Base_info base_info;
    private List<Trophy_info> trophy_info = null;
    private Team_info team_info;
    private List<Career_info> career_info = null;

    public Base_info getBase_info() {
        return base_info;
    }

    public void setBase_info(Base_info base_info) {
        this.base_info = base_info;
    }

    public List<Trophy_info> getTrophy_info() {
        return trophy_info;
    }

    public void setTrophy_info(List<Trophy_info> trophy_info) {
        this.trophy_info = trophy_info;
    }

    public Team_info getTeam_info() {
        return team_info;
    }

    public void setTeam_info(Team_info team_info) {
        this.team_info = team_info;
    }

    public List<Career_info> getCareer_info() {
        return career_info;
    }

    public void setCareer_info(List<Career_info> career_info) {
        this.career_info = career_info;
    }

}
