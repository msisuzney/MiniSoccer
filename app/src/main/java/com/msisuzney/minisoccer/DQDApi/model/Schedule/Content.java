
package com.msisuzney.minisoccer.DQDApi.model.Schedule;

import java.util.List;

public class Content {

    private List<Round> rounds = null;
    private List<Match> matches = null;

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

}
