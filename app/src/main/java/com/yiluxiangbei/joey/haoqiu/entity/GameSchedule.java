package com.yiluxiangbei.joey.haoqiu.entity;

/**
 * Created by admin on 2016/2/23.
 */
public class GameSchedule {

    private int id;
    private String status;
    private String date;
    private String time;
    private String result;
    private String team1;
    private String team1Info;
    private String team2;
    private String team2Info;
    private String video;
    private String videoLink;
    private String report;
    private String reportLink;

    public GameSchedule() {
    }

    public GameSchedule(int id, String status, String date, String time, String result, String team1, String team1Info, String team2, String team2Info, String video, String videoLink, String report, String reportLink) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.time = time;
        this.result = result;
        this.team1 = team1;
        this.team1Info = team1Info;
        this.team2 = team2;
        this.team2Info = team2Info;
        this.video = video;
        this.videoLink = videoLink;
        this.report = report;
        this.reportLink = reportLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam1Info() {
        return team1Info;
    }

    public void setTeam1Info(String team1Info) {
        this.team1Info = team1Info;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam2Info() {
        return team2Info;
    }

    public void setTeam2Info(String team2Info) {
        this.team2Info = team2Info;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReportLink() {
        return reportLink;
    }

    public void setReportLink(String reportLink) {
        this.reportLink = reportLink;
    }

    @Override
    public String toString() {
        return "GameSchedule{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", result='" + result + '\'' +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", video='" + video + '\'' +
                ", report='" + report + '\'' +
                '}';
    }
}
