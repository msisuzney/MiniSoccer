
package com.msisuzney.minisoccer.DQDApi.model.search;


public class Player {

    private String person_id;
    private String person_name;
    private String person_en_name;
    private String person_img;
    private String date_of_birth;
    private String age;
    private String type;
    private String position;
    private String team;
    private String nationality;

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_en_name() {
        return person_en_name;
    }

    public void setPerson_en_name(String person_en_name) {
        this.person_en_name = person_en_name;
    }

    public String getPerson_img() {
        return person_img;
    }

    public void setPerson_img(String person_img) {
        this.person_img = person_img;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
