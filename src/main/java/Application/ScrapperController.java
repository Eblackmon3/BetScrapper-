package Application;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ScrapperController {

    @RequestMapping("/")
    public String index() {
        return "Finding Bets Now";
    }

}