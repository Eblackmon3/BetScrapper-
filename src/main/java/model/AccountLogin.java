package model;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class AccountLogin {

    public AccountLogin(){}

    public Document getPage(String loginUrl, String url){
        Document mlPage;

        try {

            //First login. Take the cookies

            Connection.Response loginForm = Jsoup.connect(loginUrl)
                    .validateTLSCertificates(false)
                    .method(Connection.Method.GET)
                    .execute();

            Connection.Response res = Jsoup
                    .connect(loginUrl)
                    .validateTLSCertificates(false)
                    .data("ctl00$ctl00$ContentPlaceHolder1$ContentPlaceHolder1$LoginControl$username", "ericblackmon38@gmail.com")
                    .data("ctl00$ctl00$ContentPlaceHolder1$ContentPlaceHolder1$LoginControl$password", "281330800fB")
                    .data("ctl00$ctl00$ContentPlaceHolder1$ContentPlaceHolder1$LoginControl$submit","login")
                    .referrer("https://login.sportsbookreview.com/user-login")
                    .followRedirects(true)
                    .userAgent(
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36")
                    .method(Method.POST)
                    .execute();
            Document loginDoc=res.parse();
            Map<String, String> loginCookies = res.cookies();

            //Now you can parse any page you want, as long as you pass the cookies
            mlPage = Jsoup
                    .connect(url)
                    .validateTLSCertificates(false)
                    .timeout(0)
                    .referrer("http://www.google.com")
                    .followRedirects(true)
                    .cookies(loginCookies)
                    .userAgent(
                            "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .get();



        } catch (Exception e) {
            e.printStackTrace();
            mlPage=null;
        }

        return mlPage;
    }


}
