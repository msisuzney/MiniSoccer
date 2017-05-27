package com.msisuzney.minisoccer.DQDApi.model;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class PlayerDetail {

    private List<Trophy_info> trophy_info;
    private List<Transfer_info> transfer_info;
    private List<Injury_info> injury_info;

    public List<Trophy_info> getTrophy_info() {
        return trophy_info;
    }

    public void setTrophy_info(List<Trophy_info> trophy_info) {
        this.trophy_info = trophy_info;
    }

    public List<Transfer_info> getTransfer_info() {
        return transfer_info;
    }

    public void setTransfer_info(List<Transfer_info> transfer_info) {
        this.transfer_info = transfer_info;
    }

    public List<Injury_info> getInjury_info() {
        return injury_info;
    }

    public void setInjury_info(List<Injury_info> injury_info) {
        this.injury_info = injury_info;
    }

    public static class Trophy_info{
        private String competition_name;
        private String times;
        private String trophy_img;
        private List<TeamDetail.Trophy_info.Year>lists;

        public List<TeamDetail.Trophy_info.Year> getLists() {
            return lists;
        }

        public void setLists(List<TeamDetail.Trophy_info.Year> lists) {
            this.lists = lists;
        }

        public String getCompetition_name() {
            return competition_name;
        }

        public static class Year{
            private String season_id;
            private String season_name;
            private String team_id;
            private String team_name;

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
    }
    public static class Transfer_info{
        private String type;
        private String money;
        private String announced_date;
        private String from_club_name;
        private String to_club_name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getAnnounced_date() {
            return announced_date;
        }

        public void setAnnounced_date(String announced_date) {
            this.announced_date = announced_date;
        }

        public String getFrom_club_name() {
            return from_club_name;
        }

        public void setFrom_club_name(String from_club_name) {
            this.from_club_name = from_club_name;
        }

        public String getTo_club_name() {
            return to_club_name;
        }

        public void setTo_club_name(String to_club_name) {
            this.to_club_name = to_club_name;
        }
    }
    public static class Injury_info{
        private String team_name;
        private String type;
        private String start_date;
        private String end_date;

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }
    }
}
