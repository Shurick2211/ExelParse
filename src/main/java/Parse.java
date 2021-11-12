import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;


public class Parse {
    private static Document getPage(String url) throws IOException {
        Document page;
        page = Jsoup.parse(new URL(url), 5000);
        return page;
    }


    public static void getLink(String url) throws IOException {
        String urlKr = url;


        try {
            Document pageKr = getPage(urlKr);
            Elements krTablo=pageKr.select("div [class=thumbnail]");

            Element els=krTablo.select("a").first();

            System.out.println(els.text("href"));
            }catch (SocketTimeoutException e){System.out.println("Инет тормозит!");}

    }
    public static String orikoImLink(String url) throws IOException {
        //String urlKr="https://orico-russia.ru/orico-crs12-or?search=ORICO%20CRS12";
        Document pageKr=getPage(url);
        Elements krTablo=pageKr.select("div [class=thumbnail]");
        Element element=krTablo.select("a").first();
        String text;
        try {
             text=element.attr("href");
        }catch (Exception e){System.out.println("oro"); text="fff";}


        if(text.equals(null))text="";
        return text;

    }
    public static String orikoText(String url) throws IOException {
       // String urlKr="https://orico-russia.ru/storage/korpusa-dlja-vneshnix-diskov/orico-msa-u3-sv";
        Document pageKr=getPage(url);
        Elements krTablo=pageKr.select("div [id=tab-description]");
        String text;
        try {
        text=krTablo.text();
        }catch (Exception e){System.out.println("oro"); text="fff";}
        if(text.equals(null))text="";
        return text;
    }
}
