package model;

import java.awt.print.Book;
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
        for(Booky currBooky: bookingAgencies){
            if(currBooky.getTeamOneOdds()<teamOneLowest){
                teamOneLowest=currBooky.getTeamOneOdds();
            }
            if(currBooky.getTeamOneOdds()>teamOneHighest){
                teamOneHighest=currBooky.getTeamOneOdds();
            }
            if(currBooky.getTeamTwoOdds()<teamTwoLowest){
                teamTwoLowest=currBooky.getTeamTwoOdds();
            }
            if(currBooky.getTeamTwoOdds()>teamTwoHighest){
                teamTwoHighest=currBooky.getTeamTwoOdds();
            }
        }
        this.teamOneLowest=teamOneLowest;
        this.teamOneHighest=teamOneHighest;
        this.teamTwoLowest= teamTwoLowest;
        this.teamTwoHighest=teamTwoHighest;
    }


    public boolean placeBet(){
        if(teamOneLowest<0){
            for(Booky game:bookingAgencies){
                if(game.getTeamTwoOdds()>Math.abs(teamOneLowest)){
                    return true;
                }

            }

        }else{
            for(Booky game:bookingAgencies){
                if(game.getTeamOneOdds()>Math.abs(teamTwoLowest)){
                    return true;
                }

            }

        }
        return false;
    }


    public String toString(){
        return teamOne+ " and "+ teamTwo;
    }

}
