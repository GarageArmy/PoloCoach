package com.example.adam.polocoach;

/**
 * Created by Adam on 2016. 11. 19..
 */
public class ActivityObject {


    private int id;
    private String team;
    private String player;
    private String activityName;
    private String activityText;

    public ActivityObject()
    {
    }

    public ActivityObject(int id, String team,String pname,String aname,String atext)
    {
        this.id = id;
        this.team=team;
        this.player=pname;
        this.activityName=aname;
        this.activityText=atext;
    }

    public ActivityObject(String team,String pname,String aname,String atext){
        this.team=team;
        this.player=pname;
        this.activityName=aname;
        this.activityText=atext;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public String getPlayer() {
        return player;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityText() {
        return activityText;
    }

    public void setTeam(String id) {
        this.team = team;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setActivityText(String activityText) {
        this.activityText = activityText;
    }


}
