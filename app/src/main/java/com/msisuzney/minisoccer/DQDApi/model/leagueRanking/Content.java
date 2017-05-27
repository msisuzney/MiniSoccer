
package com.msisuzney.minisoccer.DQDApi.model.leagueRanking;

import java.util.List;

public class Content {

    private List<Round> rounds = null;
    private String description;

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
