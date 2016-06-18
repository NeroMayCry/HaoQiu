package com.yiluxiangbei.joey.haoqiu.entity;

/**
 * Created by admin on 2016/2/23.
 */
public class Scorer {

    private String playerRank;
    private String playerName;
    private String playerClub;
    private String playerGoal;
    private String playerInfo;

    public Scorer() {
    }

    public Scorer(String playerRank, String playerName, String playerClub, String playerGoal, String playerInfo) {
        this.playerRank = playerRank;
        this.playerName = playerName;
        this.playerClub = playerClub;
        this.playerGoal = playerGoal;
        this.playerInfo = playerInfo;
    }

    public String getPlayerRank() {
        return playerRank;
    }

    public void setPlayerRank(String playerRank) {
        this.playerRank = playerRank;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerClub() {
        return playerClub;
    }

    public void setPlayerClub(String playerClub) {
        this.playerClub = playerClub;
    }

    public String getPlayerGoal() {
        return playerGoal;
    }

    public void setPlayerGoal(String playerGoal) {
        this.playerGoal = playerGoal;
    }

    public String getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(String playerInfo) {
        this.playerInfo = playerInfo;
    }

    @Override
    public String toString() {
        return "Scorer{" +
                "playerRank='" + playerRank + '\'' +
                ", playerName='" + playerName + '\'' +
                ", playerClub='" + playerClub + '\'' +
                ", playerGoal='" + playerGoal + '\'' +
                ", playerInfo='" + playerInfo + '\'' +
                '}';
    }
}
