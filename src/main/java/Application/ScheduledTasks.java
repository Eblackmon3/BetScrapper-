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
        String placeBetCurrent=null;
        String placeBetFuture=null;

        placeBetCurrent=scrapeGames.scrapeCurrGames();
        placeBetFuture=scrapeGames.scrapeFutureGames();


        if(placeBetCurrent!=null&&!placeBetCheck.containsKey(placeBetCurrent)&&placeBetCheck.get(placeBetCurrent)!=null&&
                LocalDateTime.now().isAfter(placeBetCheck.get(placeBetCurrent))){
            Message message = Message.creator(new PhoneNumber("5713449998"),
                    new PhoneNumber("+12406247881"),
                    placeBetCurrent).create();


            Message message2 = Message.creator(new PhoneNumber("7037856222"),
                    new PhoneNumber("+12406247881"),
                    placeBetCurrent).create();



            placeBetCheck.put(placeBetCurrent,LocalDateTime.now().plusMinutes(10));

            System.out.println(message.getSid());

        }
        if(placeBetFuture!=null&&!placeBetCheck.containsKey(placeBetFuture)&&placeBetCheck.get(placeBetFuture)!=null &&
                LocalDateTime.now().isAfter(placeBetCheck.get(placeBetFuture))){
            Message message = Message.creator(new PhoneNumber("5713449998"),
                    new PhoneNumber("+12406247881"),
                    placeBetFuture).create();

            Message message2 = Message.creator(new PhoneNumber("7037856222"),
                    new PhoneNumber("+12406247881"),
                    placeBetFuture).create();


            placeBetCheck.put(placeBetFuture,LocalDateTime.now().plusMinutes(10));


            System.out.println(message.getSid());

        };



    }

    @Scheduled(cron = "0 1 * * * *")
    public void clearMap(){
        placeBetCheck.clear();
    }

}