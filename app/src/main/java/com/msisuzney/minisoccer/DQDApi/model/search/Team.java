
package com.msisuzney.minisoccer.DQDApi.model.search;


public class Team {

    private String team_id;
    private String team_name;
    private String team_en_name;
    private String team_img;
    private String country;
    private String venue_name;
    private String venue_capacity;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
