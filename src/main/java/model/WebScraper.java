package model;

import model.Event;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class WebScraper {
    Elements currNFL= new Elements();
    Elements currNBA= new Elements();
    Elements currHockey= new Elements();
    Elements currColBall=new Elements();
    Elements upcomingNFL= new Elements();
    Elements upcomingNBA= new Elements();
    Elements upcomingHockey= new Elements();
    Elements upcomingColBall= new Elements();


    String url="";
    Document doc;
    Element footballEvents;
    Element basketballEvents;
    Element hockeyEvents;
    Element colBasketballEvents;

    public WebScraper(){

        AccountLogin login= new AccountLogin();
       Document tempDoc= login.getPage("https://login.sportsbookreview.com/user-login",
                "https://www.sportsbookreview.com/betting-odds/money-line");

        try {
            url = "https://www.sportsbookreview.com/betting-odds/money-line/?sID=qczgbhkmzgivdcy5kinzfagk&sID=nsdnmystjojqh5tb25ftdbbb";
            //Get the associated sporting events
            //doc = Jsoup.connect(url).timeout(10000).validateTLSCertificates(false).get();
            doc=tempDoc;
            footballEvents = doc.getElementById("OddsGridModule_16");
            basketballEvents = doc.getElementById("OddsGridModule_5");
            hockeyEvents = doc.getElementById("OddsGridModule_7");
            colBasketballEvents = doc.getElementById("OddsGridModule_14");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

/*
 Get the games that are currrently happening
 */
    public ArrayList<String> scrapeCurrGames(){
        String teamOne="";
        String teamTwo="";
        String teamOneOdds="";
        String teamTwoOdds="";
        Booky odds= null;
        Event nbaEvent=null;
        Event nflEvent=null;
        Event hockeyEvent=null;
        Event colBballEvent=null;
        ArrayList<String>bets= new ArrayList<String>();


        ArrayList<Event>nflList= new ArrayList<>();
        ArrayList<Event>nbaList= new ArrayList<>();
        ArrayList<Event>hockeyList= new ArrayList<>();
        ArrayList<Event>collegeList= new ArrayList<>();
        //Just checking to make sure that we are grabbing all of the games
        System.out.println("Current");
        try{
            if(footballEvents!=null&&footballEvents.getElementsByClass("content-in-progress ")!=null){
                Elements currNFL=footballEvents.getElementsByClass("content-in-progress ").get(0).
                        getElementsByClass("event-holder holder-in-progress");
                System.out.println(currNFL.size());
                for(Element game:currNFL){
                    //Grab the team names
                    teamOne=game.getElementsByClass("el-div eventLine-team").
                            get(0).getElementsByClass("team-name").get(0).text();
                    teamTwo=game.getElementsByClass("el-div eventLine-team").
                            get(0).getElementsByClass("team-name").get(1).text();
                    nflEvent=new Event(teamOne,teamTwo);
                    //Go through and get the odds
                    if(game.getElementsByClass("el-div eventLine-opener")!=null){
                        teamOneOdds=game.getElementsByClass("el-div eventLine-opener")
                                .get(0).getElementsByClass("eventLine-book-value").get(0).text();
                        teamTwoOdds=game.getElementsByClass("el-div eventLine-opener")
                                .get(0).getElementsByClass("eventLine-book-value").get(1).text();
                        if(teamOneOdds.equals("")){
                            teamOneOdds="0";
                        }
                        if(teamTwoOdds.equals("")){
                            teamTwoOdds="0";
                        }
                        odds= new Booky(Integer.parseInt(teamOneOdds),
                                Integer.parseInt(teamTwoOdds),
                                teamOne,
                                teamTwo);
                        odds.setBookyName("Opener");
                        nflEvent.addBookingAgencies(odds);
                        nflList.add(nflEvent);
                        System.out.println(odds);
                    }

                    for(Element books:game.getElementsByClass("el-div eventLine-book")){
                        teamOneOdds=books.getElementsByClass("eventLine-book-value").get(0).text();
                        teamTwoOdds=books.getElementsByClass("eventLine-book-value").get(1).text();
                        if(teamOneOdds.equals("")){
                            teamOneOdds="0";
                        }
                        if(teamTwoOdds.equals("")){
                            teamTwoOdds="0";
                        }


                        odds=new Booky(Integer.parseInt(teamOneOdds),
                                Integer.parseInt(teamTwoOdds),
                                teamOne,
                                teamTwo);
                        nflEvent.addBookingAgencies(odds);
                        System.out.println(odds);

                    }


                }

            }
            if(basketballEvents!=null&&basketballEvents.getElementsByClass("content-in-progress ")!=null){
                Elements currNBA=basketballEvents.getElementsByClass("content-in-progress ").get(0).
                        getElementsByClass("event-holder holder-in-progress");
                System.out.println(currNBA.size());
                for(Element game:currNBA){
                    //Grab the team names
                    teamOne=game.getElementsByClass("el-div eventLine-team").
                            get(0).getElementsByClass("team-name").get(0).text();
                    teamTwo=game.getElementsByClass("el-div eventLine-team").
                            get(0).getElementsByClass("team-name").get(1).text();
                    nbaEvent=new Event(teamOne,teamTwo);
                    //Go through and get the odds
                    if(game.getElementsByClass("el-div eventLine-opener")!=null){
                        teamOneOdds=game.getElementsByClass("el-div eventLine-opener")
                                .get(0).getElementsByClass("eventLine-book-value").get(0).text();
                        teamTwoOdds=game.getElementsByClass("el-div eventLine-opener")
                                .get(0).getElementsByClass("eventLine-book-value").get(1).text();
                        if(teamOneOdds.equals("")){
                            teamOneOdds="0";
                        }
                        if(teamTwoOdds.equals("")){
                            teamTwoOdds="0";
                        }
                        odds= new Booky(Integer.parseInt(teamOneOdds),
                                Integer.parseInt(teamTwoOdds),
                                teamOne,
                                teamTwo);
                        odds.setBookyName("Opener");
                        nbaEvent.addBookingAgencies(odds);
                        nbaList.add(nbaEvent);
                        System.out.println(odds);
                    }

                    for(Element books:game.getElementsByClass("el-div eventLine-book")){
                        teamOneOdds=books.getElementsByClass("eventLine-book-value").get(0).text();
                        teamTwoOdds=books.getElementsByClass("eventLine-book-value").get(1).text();
                        if(teamOneOdds.equals("")){
                            teamOneOdds="0";
                        }
                        if(teamTwoOdds.equals("")){
                            teamTwoOdds="0";
                        }


                        odds=new Booky(Integer.parseInt(teamOneOdds),
                                Integer.parseInt(teamTwoOdds),
                                teamOne,
                                teamTwo);
                        nbaEvent.addBookingAgencies(odds);
                        System.out.println(odds);

                    }


                }

            }if(hockeyEvents!=null&&hockeyEvents.getElementsByClass("content-in-progress ")!=null){
                Elements currHockey=hockeyEvents.getElementsByClass("content-in-progress ").get(0).
                        getElementsByClass("event-holder holder-in-progress");
                System.out.println(currHockey.size());
                for(Element game:currHockey){
                    //Grab the team names
                    teamOne=game.getElementsByClass("el-div eventLine-team").
                            get(0).getElementsByClass("team-name").get(0).text();
                    teamTwo=game.getElementsByClass("el-div eventLine-team").
                            get(0).getElementsByClass("team-name").get(1).text();
                    hockeyEvent=new Event(teamOne,teamTwo);
                    //Go through and get the odds
                    if(game.getElementsByClass("el-div eventLine-opener")!=null){
                        teamOneOdds=game.getElementsByClass("el-div eventLine-opener")
                                .get(0).getElementsByClass("eventLine-book-value").get(0).text();
                        teamTwoOdds=game.getElementsByClass("el-div eventLine-opener")
                                .get(0).getElementsByClass("eventLine-book-value").get(1).text();
                        if(teamOneOdds.equals("")){
                            teamOneOdds="0";
                        }
                        if(teamTwoOdds.equals("")){
                            teamTwoOdds="0";
                        }
                        odds= new Booky(Integer.parseInt(teamOneOdds),
                                Integer.parseInt(teamTwoOdds),
                                teamOne,
                                teamTwo);
                        odds.setBookyName("Opener");
                        hockeyEvent.addBookingAgencies(odds);
                        hockeyList.add(hockeyEvent);
                        System.out.println(odds);
                    }

                    for(Element books:game.getElementsByClass("el-div eventLine-book")){
                        teamOneOdds=books.getElementsByClass("eventLine-book-value").get(0).text();
                        teamTwoOdds=books.getElementsByClass("eventLine-book-value").get(1).text();
                        if(teamOneOdds.equals("")){
                            teamOneOdds="0";
                        }
                        if(teamTwoOdds.equals("")){
                            teamTwoOdds="0";
                        }


                        odds=new Booky(Integer.parseInt(teamOneOdds),
                                Integer.parseInt(teamTwoOdds),
                                teamOne,
                                teamTwo);
                        hockeyEvent.addBookingAgencies(odds);
                        System.out.println(odds);

                    }


                }

            }if(colBasketballEvents!=null&&colBasketballEvents.getElementsByClass("content-in-progress ")!=null){
                Elements currColBall=colBasketballEvents.getElementsByClass("content-in-progress ").get(0)
                        .getElementsByClass("event-holder holder-in-progress");
                System.out.println(currColBall.size());
                for(Element game:currColBall){
                    //Grab the team names
                    teamOne=game.getElementsByClass("el-div eventLine-team").
                            get(0).getElementsByClass("team-name").get(0).text();
                    teamTwo=game.getElementsByClass("el-div eventLine-team").
                            get(0).getElementsByClass("team-name").get(1).text();
                    colBballEvent=new Event(teamOne,teamTwo);
                    //Go through and get the odds
                    if(game.getElementsByClass("el-div eventLine-opener")!=null){
                        teamOneOdds=game.getElementsByClass("el-div eventLine-opener")
                                .get(0).getElementsByClass("eventLine-book-value").get(0).text();
                        teamTwoOdds=game.getElementsByClass("el-div eventLine-opener")
                                .get(0).getElementsByClass("eventLine-book-value").get(1).text();
                        if(teamOneOdds.equals("")){
                            teamOneOdds="0";
                        }
                        if(teamTwoOdds.equals("")){
                            teamTwoOdds="0";
                        }
                        odds= new Booky(Integer.parseInt(teamOneOdds),
                                Integer.parseInt(teamTwoOdds),
                                teamOne,
                                teamTwo);
                        odds.setBookyName("Opener");
                        colBballEvent.addBookingAgencies(odds);
                        collegeList.add(colBballEvent);
                        System.out.println(odds);
                    }

                    for(Element books:game.getElementsByClass("el-div eventLine-book")){
                        teamOneOdds=books.getElementsByClass("eventLine-book-value").get(0).text();
                        teamTwoOdds=books.getElementsByClass("eventLine-book-value").get(1).text();
                        if(teamOneOdds.equals("")){
                            teamOneOdds="0";
                        }
                        if(teamTwoOdds.equals("")){
                            teamTwoOdds="0";
                        }


                        odds=new Booky(Integer.parseInt(teamOneOdds),
                                Integer.parseInt(teamTwoOdds),
                                teamOne,
                                teamTwo);
                        colBballEvent.addBookingAgencies(odds);
                        System.out.println(odds);

                    }


                }

            }
            for(Event nbaGame: nbaList){
                nbaGame.calcLowHigh();
                if(nbaGame.placeBet().size()>0){
                    bets.add("Check NBA bets for " + nbaGame);
                }
            }
            for(Event nflGame: nflList){
                nflGame.calcLowHigh();
                if(nflGame.placeBet().size()>0){
                    bets.add("Check NFL bets for "+nflGame);
                }
            }
            for(Event hockeyGame: hockeyList){
                hockeyGame.calcLowHigh();
                if(hockeyGame.placeBet().size()>0){
                    bets.add("Checks NHL bets for "+hockeyGame);
                }
            }
            for(Event colGame: collegeList){
                colGame.calcLowHigh();
                if(colGame.placeBet().size()>0){
                    bets.add("Checks NCAA Basketball bets for "+ colGame);
                }
            }



        }catch(Exception e){
            e.printStackTrace();
            return null;

        }
        return bets;
    }

    public ArrayList<String> scrapeFutureGames(){
        String teamOne="";
        String teamTwo="";
        String teamOneOdds="";
        String teamTwoOdds="";
        Booky odds= null;
        Event nbaEvent=null;
        Event nflEvent=null;
        Event hockeyEvent=null;
        Event colBballEvent=null;
        ArrayList<String>bets= new ArrayList<String>();


        ArrayList<Event>nflList= new ArrayList<>();
        ArrayList<Event>nbaList= new ArrayList<>();
        ArrayList<Event>hockeyList= new ArrayList<>();
        ArrayList<Event>collegeList= new ArrayList<>();
        //Check the upcoming games
        System.out.println("Upcoming");
  try {
      if (footballEvents != null && footballEvents.getElementsByClass("content-scheduled content-pre-game ") != null) {
          upcomingNFL = footballEvents.getElementsByClass("content-scheduled content-pre-game ").get(0).
                  getElementsByClass("event-holder holder-scheduled");
          System.out.println(upcomingNFL.size());
          for(Element game:upcomingNFL){
              //Grab the team names
              teamOne=game.getElementsByClass("el-div eventLine-team").
                      get(0).getElementsByClass("team-name").get(0).text();
              teamTwo=game.getElementsByClass("el-div eventLine-team").
                      get(0).getElementsByClass("team-name").get(1).text();
              nflEvent=new Event(teamOne,teamTwo);
              //Go through and get the odds
              if(game.getElementsByClass("el-div eventLine-opener")!=null){
                  teamOneOdds=game.getElementsByClass("el-div eventLine-opener")
                          .get(0).getElementsByClass("eventLine-book-value").get(0).text();
                  teamTwoOdds=game.getElementsByClass("el-div eventLine-opener")
                          .get(0).getElementsByClass("eventLine-book-value").get(1).text();
                  if(teamOneOdds.equals("")){
                      teamOneOdds="0";
                  }
                  if(teamTwoOdds.equals("")){
                      teamTwoOdds="0";
                  }
                  odds= new Booky(Integer.parseInt(teamOneOdds),
                          Integer.parseInt(teamTwoOdds),
                          teamOne,
                          teamTwo);
                  odds.setBookyName("Opener");
                  nflEvent.addBookingAgencies(odds);
                  nflList.add(nflEvent);
                  System.out.println(odds);
              }

              for(Element books:game.getElementsByClass("el-div eventLine-book")){
                  teamOneOdds=books.getElementsByClass("eventLine-book-value").get(0).text();
                  teamTwoOdds=books.getElementsByClass("eventLine-book-value").get(1).text();
                  if(teamOneOdds.equals("")){
                      teamOneOdds="0";
                  }
                  if(teamTwoOdds.equals("")){
                      teamTwoOdds="0";
                  }


                  odds=new Booky(Integer.parseInt(teamOneOdds),
                          Integer.parseInt(teamTwoOdds),
                          teamOne,
                          teamTwo);
                  nflEvent.addBookingAgencies(odds);
                  System.out.println(odds);

              }


          }

      }
      if (basketballEvents != null && basketballEvents.getElementsByClass("content-scheduled content-pre-game ") != null) {
          upcomingNBA = basketballEvents.getElementsByClass("content-scheduled content-pre-game ").get(0)
                  .getElementsByClass("event-holder holder-scheduled");
          System.out.println(upcomingNBA.size());
          for(Element game:upcomingNBA){
              //Grab the team names
              teamOne=game.getElementsByClass("el-div eventLine-team").
                      get(0).getElementsByClass("team-name").get(0).text();
              teamTwo=game.getElementsByClass("el-div eventLine-team").
                      get(0).getElementsByClass("team-name").get(1).text();
              nbaEvent=new Event(teamOne,teamTwo);
              //Go through and get the odds
              if(game.getElementsByClass("el-div eventLine-opener")!=null){
                  teamOneOdds=game.getElementsByClass("el-div eventLine-opener")
                          .get(0).getElementsByClass("eventLine-book-value").get(0).text();
                  teamTwoOdds=game.getElementsByClass("el-div eventLine-opener")
                          .get(0).getElementsByClass("eventLine-book-value").get(1).text();
                  if(teamOneOdds.equals("")){
                      teamOneOdds="0";
                  }
                  if(teamTwoOdds.equals("")){
                      teamTwoOdds="0";
                  }
                  odds= new Booky(Integer.parseInt(teamOneOdds),
                          Integer.parseInt(teamTwoOdds),
                          teamOne,
                          teamTwo);
                  odds.setBookyName("Opener");
                  nbaEvent.addBookingAgencies(odds);
                  nbaList.add(nbaEvent);
                  System.out.println(odds);
              }

              for(Element books:game.getElementsByClass("el-div eventLine-book")){
                  teamOneOdds=books.getElementsByClass("eventLine-book-value").get(0).text();
                  teamTwoOdds=books.getElementsByClass("eventLine-book-value").get(1).text();
                  if(teamOneOdds.equals("")){
                      teamOneOdds="0";
                  }
                  if(teamTwoOdds.equals("")){
                      teamTwoOdds="0";
                  }


                  odds=new Booky(Integer.parseInt(teamOneOdds),
                          Integer.parseInt(teamTwoOdds),
                          teamOne,
                          teamTwo);
                  nbaEvent.addBookingAgencies(odds);
                  System.out.println(odds);

              }


          }

      }
      if (hockeyEvents != null && hockeyEvents.getElementsByClass("content-scheduled content-pre-game ") != null) {
          upcomingHockey = hockeyEvents.getElementsByClass("content-scheduled content-pre-game ").get(0)
                  .getElementsByClass("event-holder holder-scheduled");
          System.out.println(upcomingHockey.size());
          for(Element game:upcomingHockey){
              //Grab the team names
              teamOne=game.getElementsByClass("el-div eventLine-team").
                      get(0).getElementsByClass("team-name").get(0).text();
              teamTwo=game.getElementsByClass("el-div eventLine-team").
                      get(0).getElementsByClass("team-name").get(1).text();
              hockeyEvent=new Event(teamOne,teamTwo);
              //Go through and get the odds
              if(game.getElementsByClass("el-div eventLine-opener")!=null){
                  teamOneOdds=game.getElementsByClass("el-div eventLine-opener")
                          .get(0).getElementsByClass("eventLine-book-value").get(0).text();
                  teamTwoOdds=game.getElementsByClass("el-div eventLine-opener")
                          .get(0).getElementsByClass("eventLine-book-value").get(1).text();
                  if(teamOneOdds.equals("")){
                      teamOneOdds="0";
                  }
                  if(teamTwoOdds.equals("")){
                      teamTwoOdds="0";
                  }
                  odds= new Booky(Integer.parseInt(teamOneOdds),
                          Integer.parseInt(teamTwoOdds),
                          teamOne,
                          teamTwo);
                  odds.setBookyName("Opener");
                  hockeyEvent.addBookingAgencies(odds);
                  hockeyList.add(hockeyEvent);
                  System.out.println(odds);
              }

              for(Element books:game.getElementsByClass("el-div eventLine-book")){
                  teamOneOdds=books.getElementsByClass("eventLine-book-value").get(0).text();
                  teamTwoOdds=books.getElementsByClass("eventLine-book-value").get(1).text();
                  if(teamOneOdds.equals("")){
                      teamOneOdds="0";
                  }
                  if(teamTwoOdds.equals("")){
                      teamTwoOdds="0";
                  }


                  odds=new Booky(Integer.parseInt(teamOneOdds),
                          Integer.parseInt(teamTwoOdds),
                          teamOne,
                          teamTwo);
                  hockeyEvent.addBookingAgencies(odds);
                  System.out.println(odds);

              }


          }

      }
      if (colBasketballEvents != null && colBasketballEvents.getElementsByClass("content-scheduled content-pre-game ") != null) {
          upcomingColBall = colBasketballEvents.getElementsByClass("content-scheduled content-pre-game ")
                  .get(0).getElementsByClass("event-holder holder-scheduled");
          System.out.println(upcomingColBall.size());
          for(Element game:upcomingColBall){
              //Grab the team names
              teamOne=game.getElementsByClass("el-div eventLine-team").
                      get(0).getElementsByClass("team-name").get(0).text();
              teamTwo=game.getElementsByClass("el-div eventLine-team").
                      get(0).getElementsByClass("team-name").get(1).text();
              colBballEvent=new Event(teamOne,teamTwo);
              //Go through and get the odds
              if(game.getElementsByClass("el-div eventLine-opener")!=null){
                  teamOneOdds=game.getElementsByClass("el-div eventLine-opener")
                          .get(0).getElementsByClass("eventLine-book-value").get(0).text();
                  teamTwoOdds=game.getElementsByClass("el-div eventLine-opener")
                          .get(0).getElementsByClass("eventLine-book-value").get(1).text();
                  if(teamOneOdds.equals("")){
                      teamOneOdds="0";
                  }
                  if(teamTwoOdds.equals("")){
                      teamTwoOdds="0";
                  }
                  odds= new Booky(Integer.parseInt(teamOneOdds),
                          Integer.parseInt(teamTwoOdds),
                          teamOne,
                          teamTwo);
                  odds.setBookyName("Opener");
                  colBballEvent.addBookingAgencies(odds);
                  collegeList.add(colBballEvent);
                  System.out.println(odds);
              }

              for(Element books:game.getElementsByClass("el-div eventLine-book")){
                  teamOneOdds=books.getElementsByClass("eventLine-book-value").get(0).text();
                  teamTwoOdds=books.getElementsByClass("eventLine-book-value").get(1).text();
                  if(teamOneOdds.equals("")){
                      teamOneOdds="0";
                  }
                  if(teamTwoOdds.equals("")){
                      teamTwoOdds="0";
                  }


                  odds=new Booky(Integer.parseInt(teamOneOdds),
                          Integer.parseInt(teamTwoOdds),
                          teamOne,
                          teamTwo);
                  colBballEvent.addBookingAgencies(odds);
                  System.out.println(odds);

              }


          }

      }
      for(Event nbaGame: nbaList){
          nbaGame.calcLowHigh();
          if(nbaGame.placeBet().size()>0){
              bets.add("Check NBA bets for " + nbaGame);
          }
      }
      for(Event nflGame: nflList){
          nflGame.calcLowHigh();
          if(nflGame.placeBet().size()>0){
              bets.add("Check NFL bets for "+nflGame);
          }
      }
      for(Event hockeyGame: hockeyList){
          hockeyGame.calcLowHigh();
          if(hockeyGame.placeBet().size()>0){
              bets.add("Check College Basketball bets for "+hockeyGame);
          }
      }
      for(Event colGame: collegeList){
          colGame.calcLowHigh();
          if(colGame.placeBet().size()>0){
              bets.add("Check NHL bets for "+ colGame);
          }
      }



  }catch (Exception e){
      e.printStackTrace();;
      return null;
  }


        return bets;
    }
}
