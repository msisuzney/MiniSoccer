package com.msisuzney.minisoccer.DQDApi.model;

import java.util.List;

/**
 * Created by Administrator on 2016/5/11.
 */
public class TeamSchedule {


    private List<Item> list;

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    public static class Item {
        private String relate_type;
        private String relate_id;
        private String match_id;
        private String season_id;
        private String round_id;
        private String group_id;
        private String team_A_id;
        private String team_B_id;
        private String team_A_name;
        private String team_B_name;
        private String date_utc;
        private String time_utc;
        private String fs_A;
        private String fs_B;
        private String ps_A;
        private String ps_B;
        private String ets_A;
        private String ets_B;
        private String as_A;
        private String as_B;
        private String suretime;
        private String start_play;
        private String status;
        private String gameweek;
        private String playing_time;
        private String competition_id;
        private String competition_name;
        private String season_name;
        private String group_name;
        private String round_name;

        public String getRelate_type() {
            return relate_type;
        }

        public void setRelate_type(String relate_type) {
            this.relate_type = relate_type;
        }

        public String getRelate_id() {
            return relate_id;
        }

        public void setRelate_id(String relate_id) {
            this.relate_id = relate_id;
        }

        public String getMatch_id() {
            return match_id;
        }

        public void setMatch_id(String match_id) {
            this.match_id = match_id;
        }

        public String getSeason_id() {
            return season_id;
        }

        public void setSeason_id(String season_id) {
            this.season_id = season_id;
        }

        public String getRound_id() {
            return round_id;
        }

        public void setRound_id(String round_id) {
            this.round_id = round_id;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getTeam_A_id() {
            return team_A_id;
        }

        public void setTeam_A_id(String team_A_id) {
            this.team_A_id = team_A_id;
        }

        public String getTeam_B_id() {
            return team_B_id;
        }

        public void setTeam_B_id(String team_B_id) {
            this.team_B_id = team_B_id;
        }

        public String getTeam_A_name() {
            return team_A_name;
        }

        public void setTeam_A_name(String team_A_name) {
            this.team_A_name = team_A_name;
        }

        public String getTeam_B_name() {
            return team_B_name;
        }

        public void setTeam_B_name(String team_B_name) {
            this.team_B_name = team_B_name;
        }

        public String getDate_utc() {
            return date_utc;
        }

        public void setDate_utc(String date_utc) {
            this.date_utc = date_utc;
        }

        public String getTime_utc() {
            return time_utc;
        }

        public void setTime_utc(String time_utc) {
            this.time_utc = time_utc;
        }

        public String getFs_A() {
            return fs_A;
        }

        public void setFs_A(String fs_A) {
            this.fs_A = fs_A;
        }

        public String getFs_B() {
            return fs_B;
        }

        public void setFs_B(String fs_B) {
            this.fs_B = fs_B;
        }

        public String getPs_A() {
            return ps_A;
        }

        public void setPs_A(String ps_A) {
            this.ps_A = ps_A;
        }

        public String getPs_B() {
            return ps_B;
        }

        public void setPs_B(String ps_B) {
            this.ps_B = ps_B;
        }

        public String getEts_A() {
            return ets_A;
        }

        public void setEts_A(String ets_A) {
            this.ets_A = ets_A;
        }

        public String getEts_B() {
            return ets_B;
        }

        public void setEts_B(String ets_B) {
            this.ets_B = ets_B;
        }

        public String getAs_A() {
            return as_A;
        }

        public void setAs_A(String as_A) {
            this.as_A = as_A;
        }

        public String getAs_B() {
            return as_B;
        }

        public void setAs_B(String as_B) {
            this.as_B = as_B;
        }

        public String getSuretime() {
            return suretime;
        }

        public void setSuretime(String suretime) {
            this.suretime = suretime;
        }

        public String getStart_play() {
            return start_play;
        }

        public void setStart_play(String start_play) {
            this.start_play = start_play;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getGameweek() {
            return gameweek;
        }

        public void setGameweek(String gameweek) {
            this.gameweek = gameweek;
        }

        public String getPlaying_time() {
            return playing_time;
        }

        public void setPlaying_time(String playing_time) {
            this.playing_time = playing_time;
        }

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

        public String getSeason_name() {
            return season_name;
        }

        public void setSeason_name(String season_name) {
            this.season_name = season_name;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getRound_name() {
            return round_name;
        }

        public void setRound_name(String round_name) {
            this.round_name = round_name;
        }
    }
}
