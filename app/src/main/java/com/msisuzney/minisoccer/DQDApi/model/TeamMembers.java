package com.msisuzney.minisoccer.DQDApi.model;
import java.util.List;

/**
 * Created by Administrator on 2016/5/11.
 */
public class TeamMembers {
    private List<CoachItem> coach;
    private List<PlayerItem> goalkeeper;
    private List<PlayerItem> defender;
    private List<PlayerItem> midfielder;
    private List<PlayerItem> attacker;

    public List<CoachItem> getCoach() {
        return coach;
    }

    public void setCoach(List<CoachItem> coach) {
        this.coach = coach;
    }

    public List<PlayerItem> getGoalkeeper() {
        return goalkeeper;
    }

    public void setGoalkeeper(List<PlayerItem> goalkeeper) {
        this.goalkeeper = goalkeeper;
    }

    public List<PlayerItem> getDefender() {
        return defender;
    }

    public void setDefender(List<PlayerItem> defender) {
        this.defender = defender;
    }

    public List<PlayerItem> getMidfielder() {
        return midfielder;
    }

    public void setMidfielder(List<PlayerItem> midfielder) {
        this.midfielder = midfielder;
    }

    public List<PlayerItem> getAttacker() {
        return attacker;
    }

    public void setAttacker(List<PlayerItem> attacker) {
        this.attacker = attacker;
    }

    public static class CoachItem{
        private String name;
        private String person_id;
        private String type;
        private String t;
        private String person_img;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPerson_di() {
            return person_id;
        }
        public void setPerson_di(String person_di) {
            this.person_id = person_di;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }

        public String getPerson_img() {
            return person_img;
        }

        public void setPerson_img(String person_img) {
            this.person_img = person_img;
        }
    }
    public static class PlayerItem{
        private String name;
        private String person_id;
        private String person_img;
        private String shirtnumber;
        private String goals;
        private String red_cards;
        private String yellow_cards;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPerson_id() {
            return person_id;
        }

        public void setPerson_id(String person_id) {
            this.person_id = person_id;
        }

        public String getPerson_img() {
            return person_img;
        }

        public void setPerson_img(String person_img) {
            this.person_img = person_img;
        }

        public String getShirtnumber() {
            return shirtnumber;
        }

        public void setShirtnumber(String shirtnumber) {
            this.shirtnumber = shirtnumber;
        }

        public String getGoals() {
            return goals;
        }

        public void setGoals(String goals) {
            this.goals = goals;
        }

        public String getRed_cards() {
            return red_cards;
        }

        public void setRed_cards(String red_cards) {
            this.red_cards = red_cards;
        }

        public String getYellow_cards() {
            return yellow_cards;
        }

        public void setYellow_cards(String yellow_cards) {
            this.yellow_cards = yellow_cards;
        }
    }


}
