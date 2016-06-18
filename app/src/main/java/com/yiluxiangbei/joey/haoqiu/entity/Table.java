package com.yiluxiangbei.joey.haoqiu.entity;

/**
 * Created by admin on 2016/2/23.
 */
public class Table {

    private String rank;
    private String club;
    private String clubInfo;
    private String games;
    private String win;
    private String draw;
    private String lose;
    private String goalDifference;
    private String score;

    public Table() {
    }

    public Table(String rank, String club, String clubInfo, String games, String win, String draw, String lose, String goalDifference, String score) {
        this.rank = rank;
        this.club = club;
        this.clubInfo = clubInfo;
        this.games = games;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.goalDifference = goalDifference;
        this.score = score;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getClubInfo() {
        return clubInfo;
    }

    public void setClubInfo(String clubInfo) {
        this.clubInfo = clubInfo;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(String goalDifference) {
        this.goalDifference = goalDifference;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Table{" +
                "rank='" + rank + '\'' +
                ", club='" + club + '\'' +
                ", clubInfo='" + clubInfo + '\'' +
                ", games='" + games + '\'' +
                ", win='" + win + '\'' +
                ", draw='" + draw + '\'' +
                ", lose='" + lose + '\'' +
                ", goalDifference='" + goalDifference + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
