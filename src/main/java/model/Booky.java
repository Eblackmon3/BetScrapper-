package model;

public class Booky {
   private int teamOneOdds;
   private int teamTwoOdds;
   private String teamOneName;
   private String teamTwoName;
   private String bookyName;

    public Booky(int teamOneOdds, int teamTwoOdds, String teamOneName, String teamTwoName){
        this.teamOneOdds=teamOneOdds;
        this.teamTwoOdds=teamTwoOdds;
        this.teamOneName= teamOneName;
        this.teamTwoName= teamTwoName;
    }

    public int getTeamOneOdds() {
        return teamOneOdds;
    }

    public int getTeamTwoOdds() {
        return teamTwoOdds;
    }

    public void setTeamOneOdds(int teamOneOdds) {
        this.teamOneOdds = teamOneOdds;
    }

    public void setTeamTwoOdds(int teamTwoOdds) {
        this.teamTwoOdds = teamTwoOdds;
    }

    public String getBookyName() {
        return bookyName;
    }

    public void setBookyName(String bookyName) {
        this.bookyName = bookyName;
    }

    public String StringbetterOdds(){
        if(teamOneOdds>teamTwoOdds){
            return teamOneName;
        }else{
            return teamTwoName;
        }
    }

    public String toString(){
        return "Team One:"+teamOneName+" Team two:"+teamTwoName+ " Line One:" +teamOneOdds
                +" Line two:"+teamTwoOdds+" Booky Name: "+bookyName;
    }

}
