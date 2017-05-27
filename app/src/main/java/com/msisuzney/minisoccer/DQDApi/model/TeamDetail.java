package com.msisuzney.minisoccer.DQDApi.model;

import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 */
public class TeamDetail {
    public Base_info base_info;
    public List<Trophy_info> trophy_info;

    public Base_info getBase_info() {
        return base_info;
    }

    public void setBase_info(Base_info base_info) {
        this.base_info = base_info;
    }

    public List<Trophy_info> getTrophyInfo() {
        return trophy_info;
    }

    public void setTrophyInfo(List<Trophy_info> trophyInfo) {
        this.trophy_info = trophyInfo;
    }

    public static class Base_info{
        public String team_id;
        public String team_name;
        public String team_en_name;
        public String team_img;
        public String area_id;
        public String area_img;
        public String country_img;
        public String country;
        public String address;
        public String telephone;
        public String email;
        public String city;
        public String founded;
        public String venue_id;
        public String venue_name;
        public String venue_capacity;

        public String getTeam_id() {
            return team_id;
        }

        public void setTeam_id(String team_id) {
            this.team_id = team_id;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getTeam_en_name() {
            return team_en_name;
        }

        public void setTeam_en_name(String team_en_name) {
            this.team_en_name = team_en_name;
        }

        public String getTeam_img() {
            return team_img;
        }

        public void setTeam_img(String team_img) {
            this.team_img = team_img;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getArea_img() {
            return area_img;
        }

        public void setArea_img(String area_img) {
            this.area_img = area_img;
        }

        public String getCountry_img() {
            return country_img;
        }

        public void setCountry_img(String country_img) {
            this.country_img = country_img;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getFounded() {
            return founded;
        }

        public void setFounded(String founded) {
            this.founded = founded;
        }

        public String getVenue_id() {
            return venue_id;
        }

        public void setVenue_id(String venue_id) {
            this.venue_id = venue_id;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public String getVenue_capacity() {
            return venue_capacity;
        }

        public void setVenue_capacity(String venue_capacity) {
            this.venue_capacity = venue_capacity;
        }
    }

    public static class Trophy_info{
    public String competition_id;
    public String competition_name;
    public String times;
    public String trophy_img;
    public List<Year> lists;

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

    public List<Year> getLists() {
        return lists;
    }

    public void setLists(List<Year> lists) {
        this.lists = lists;
    }

    public static class Year{
        public String season_id;
        public String season_name;
        private String team_name;

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getSeason_id() {
            return season_id;
        }

        public void setSeason_id(String season_id) {
            this.season_id = season_id;
        }

        public String getSeason_name() {
            return season_name;
        }

        public void setSeason_name(String season_name) {
            this.season_name = season_name;
        }
    }
}
}
