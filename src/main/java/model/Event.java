package model;

import java.awt.print.Book;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Event {
    private String teamOne;
    private String teamTwo;
    private ArrayList<Booky> bookingAgencies= new ArrayList<>();
    private Booky opener;
    private int teamOneLowest;
    private int teamOneHighest;
    private int teamTwoHighest;
    private int teamTwoLowest;


    public Event(String teamOne, String teamTwo){
        this.teamOne=teamOne;
        this.teamTwo=teamTwo;
        this.opener=opener;
    }

    public String getTeamOne() {
        return teamOne;
    }


    public String getTeamTwo() {
        return teamTwo;
    }

    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }

    public Booky getOpener() {
        return opener;
    }

    public void setOpener(Booky opener) {
        this.opener = opener;
    }

    public void setBookingAgencies(ArrayList<Booky> bookingAgencies) {
        this.bookingAgencies = bookingAgencies;
    }

    public void addBookingAgencies(Booky booky){
        this.bookingAgencies.add(booky);
    }

    public ArrayList<Booky> getBookingAgencies() {
        return bookingAgencies;
    }

    public int getTeamOneHighest() {
        return teamOneHighest;
    }

    public int getTeamOneLowest() {
        return teamOneLowest;
    }

    public int getTeamTwoHighest() {
        return teamTwoHighest;
    }

    public int getTeamTwoLowest() {
        return teamTwoLowest;
    }

    public void calcLowHigh(){
        int teamOneLowest=Integer.MAX_VALUE;
        int teamOneHighest=Integer.MIN_VALUE;
        int teamTwoLowest=Integer.MAX_VALUE;
        int teamTwoHighest=Integer.MIN_VALUE;
        int i=0;
        for(Booky currBooky: bookingAgencies){
            if(currBooky.getTeamOneOdds()<teamOneLowest){
                if(i!=0) {
                    teamOneLowest = currBooky.getTeamOneOdds();
                }
            }
            if(currBooky.getTeamOneOdds()>teamOneHighest){
                if(i!=0) {
                    teamOneHighest = currBooky.getTeamOneOdds();
                }
            }
            if(currBooky.getTeamTwoOdds()<teamTwoLowest){
                if(i!=0) {
                    teamTwoLowest = currBooky.getTeamTwoOdds();
                }
            }
            if(currBooky.getTeamTwoOdds()>teamTwoHighest){
                if(i!=0) {
                    teamTwoHighest = currBooky.getTeamTwoOdds();
                }
            }
            i++;
        }
        this.teamOneLowest=teamOneLowest;
        this.teamOneHighest=teamOneHighest;
        this.teamTwoLowest= teamTwoLowest;
        this.teamTwoHighest=teamTwoHighest;
    }


    public ArrayList<String> placeBet(){
        int [] teamOneOdds= new  int [bookingAgencies.size()];
        int []  teamTwoOdds=  new  int [bookingAgencies.size()];
        int i=0;
        ArrayList<String> bets= new ArrayList<>();
        bookingAgencies.remove(0);
        for(Booky game:bookingAgencies){
            teamOneOdds[i]=bookingAgencies.get(i).getTeamOneOdds();
            teamTwoOdds[i]=bookingAgencies.get(i).getTeamTwoOdds();
            i++;
        }

        if(teamOneLowest<0){
            for(int j=0; j<teamOneOdds.length;j++){
                for(int k=j;k<teamTwoOdds.length;k++){
                    if(((Math.abs(teamOneOdds[j])<teamTwoOdds[k])||teamOneOdds[j]>0)&&teamOneOdds[j]!=0){
                        if((j!=0&&j!=2&&j!=9)&&(k!=0&&k!=2&&k!=9)&&(teamTwoOdds[k])>0) {

                            bets.add(" Team one odds:"+teamOneOdds[j]+" Team two odds:"+teamTwoOdds[k]);
                        }
                    }
                }
            }

        }else{
            for(int j=0; j<teamTwoOdds.length;j++){
                for(int k=j;k<teamOneOdds.length;k++){
                    if((Math.abs(teamTwoOdds[j])<teamOneOdds[k]||teamTwoOdds[j]>0)&& teamTwoOdds[j]!=0){
                        if((j!=0&&j!=2&&j!=9)&&(k!=0&&k!=2&&k!=9)&&(teamOneOdds[k])>0) {
                            bets.add(" Team one odds:"+teamTwoOdds[j]+" Team two odds:"+teamTwoOdds[k]);
                        }
                    }
                }
            }

        }
        return bets;
    }


    public String toString(){
        return teamOne+ " and "+ teamTwo;
    }

}
