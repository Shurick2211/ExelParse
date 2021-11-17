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


    public static String orikoImLink(String url) throws IOException {
        //String urlKr="https://orico-russia.ru/orico-crs12-or?search=ORICO%20CRS12";
        Document pageKr=getPage(url);
        Elements krTablo=pageKr.select("div [class=thumbnail]");
        Element element=krTablo.select("a").first();
        String text1;
        try {
            text1=element.attr("href");
        }catch (Exception e){System.out.println("oro"); text1="fff";}


        if(text1.equals(null))text1="";




        return text1;


    }


    public static String [] orikoImLinkImg(String url) throws IOException {
        //String urlKr="https://orico-russia.ru/orico-crs12-or?search=ORICO%20CRS12";
        Document pageKr=getPage(url);
        Elements krTablo=pageKr.select("div [class=thumbnail]");
        Element element=krTablo.select("a").first();
        String text1;
        try {
             text1=element.attr("href");
        }catch (Exception e){System.out.println("oro"); text1="fff";}


        if(text1.equals(null))text1="";

        Elements krTablo1=pageKr.select("div [id=tab-description]");
        String text2;
        try {
            text2=krTablo1.text();
        }catch (Exception e){System.out.println("oro"); text2="fff";}
        if(text2.equals(null))text2="";


        String[] text=new String[2];
        text[0]=text1;
        text[1]=text2;

        return text;


    }
    public static String orikoText(String url) throws IOException {
       // String urlKr="https://orico-russia.ru/storage/korpusa-dlja-vneshnix-diskov/orico-msa-u3-sv";
        Document pageKr=getPage(url);
        Elements krTablo=pageKr.select("div [id=tab-description]");
        String text2;
        try {
        text2=krTablo.text();
        }catch (Exception e){System.out.println("oro"); text2="fff";}
        if(text2.equals(null))text2="";
        return text2;
    }
}
