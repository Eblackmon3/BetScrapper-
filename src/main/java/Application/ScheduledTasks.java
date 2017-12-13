package Application;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import model.WebScraper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Component
public class ScheduledTasks {
    public static final String ACCOUNT_SID = "AC3a4179ac45dda17fde99e4f7ae51e851";
    public static final String AUTH_TOKEN = "d94587b0233e48b8dc3f1d1a3e748c9e";
    public Map<String,LocalDateTime> placeBetCheck= new HashMap<>();

    @Scheduled(fixedDelay=5000)
    public void scrapeBets(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        WebScraper scrapeGames = new WebScraper();
        ArrayList<String> placeBetCurrent=null;
        ArrayList<String> placeBetFuture=null;

        placeBetCurrent=scrapeGames.scrapeCurrGames();
        placeBetFuture=scrapeGames.scrapeFutureGames();


        if(placeBetCurrent.size()>0&&!placeBetCheck.containsKey(placeBetCurrent.toString())&&(placeBetCheck.get(placeBetCurrent.toString())==null||
                LocalDateTime.now().isAfter(placeBetCheck.get(placeBetCurrent.toString())))){
            Message message = Message.creator(new PhoneNumber("5713449998"),
                    new PhoneNumber("+12406247881"),
                    placeBetCurrent.toString()).create();

/*

            Message message2 = Message.creator(new PhoneNumber("7037856222"),
                    new PhoneNumber("+12406247881"),
                    placeBetCurrent.toString()).create();

*/

            placeBetCheck.put(placeBetCurrent.toString(),LocalDateTime.now().plusMinutes(10));

            System.out.println(message.getSid());

        }
        if(placeBetFuture.size()>0&&!placeBetCheck.containsKey(placeBetFuture.toString())&&(placeBetCheck.get(placeBetFuture.toString())==null ||
                LocalDateTime.now().isAfter(placeBetCheck.get(placeBetFuture.toString())))){
            Message message = Message.creator(new PhoneNumber("5713449998"),
                    new PhoneNumber("+12406247881"),
                    placeBetFuture.toString()).create();
/*
            Message message2 = Message.creator(new PhoneNumber("7037856222"),
                    new PhoneNumber("+12406247881"),
                    placeBetFuture.toString()).create();
                    */


            placeBetCheck.put(placeBetFuture.toString(),LocalDateTime.now().plusMinutes(10));


            System.out.println(message.getSid());

        };



    }

    @Scheduled(cron = "0 1 * * * *")
    public void clearMap(){
        placeBetCheck.clear();
    }


}