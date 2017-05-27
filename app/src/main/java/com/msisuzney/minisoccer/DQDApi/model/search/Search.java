
package com.msisuzney.minisoccer.DQDApi.model.search;

import java.util.List;

public class Search {

    private List<Player> players = null;
    private List<Team> teams = null;
    private List<User> users = null;
    private List<News> news = null;
    private List<Topic> topics = null;
    private List<Product> products = null;
    private List<Object> sports = null;
    private List<String> word = null;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Object> getSports() {
        return sports;
    }

    public void setSports(List<Object> sports) {
        this.sports = sports;
    }

    public List<String> getWord() {
        return word;
    }

    public void setWord(List<String> word) {
        this.word = word;
    }

}
