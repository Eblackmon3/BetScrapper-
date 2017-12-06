package Application;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import model.WebScraper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {
    public static final String ACCOUNT_SID = "AC3a4179ac45dda17fde99e4f7ae51e851";
    public static final String AUTH_TOKEN = "d94587b0233e48b8dc3f1d1a3e748c9e";

    @Scheduled(fixedDelay=5000)
    public void scrapeBets(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        WebScraper scrapeGames = new WebScraper();
        String placeBetCurrent=null;
        String placeBetFuture=null;
        placeBetCurrent=scrapeGames.scrapeCurrGames();
        placeBetFuture=scrapeGames.scrapeFutureGames();


        if(placeBetCurrent!=null){
            Message message = Message.creator(new PhoneNumber("5713449998"),
                    new PhoneNumber("+12406247881"),
                    placeBetCurrent).create();
            Message message2=Message.creator(new PhoneNumber("7037856222"),
                    new PhoneNumber("+12406247881"),
                    placeBetCurrent).create();

            System.out.println(message.getSid());
            System.out.println(message2.getSid());

        }
        if(placeBetFuture!=null){
            Message message = Message.creator(new PhoneNumber("5713449998"),
                    new PhoneNumber("+12406247881"),
                    placeBetCurrent).create();
            Message message2=Message.creator(new PhoneNumber("7037856222"),
                    new PhoneNumber("+12406247881"),
                    placeBetCurrent).create();

            System.out.println(message.getSid());
            System.out.println(message2.getSid());

        };


    }

}