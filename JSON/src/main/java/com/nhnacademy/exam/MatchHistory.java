package com.nhnacademy.exam;

public class MatchHistory {
    int matchCount;
    int winCount;
    int loseCount;

    public MatchHistory() {
        this.matchCount = 0;
        this.winCount = 0;
        this.loseCount = 0;
    }

    public void win() {
        this.winCount++;
        this.matchCount++;
    }

    public void lose() {
        this.loseCount++;
        this.matchCount++;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
