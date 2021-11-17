
    import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

    import javax.xml.transform.Source;
    import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
    import java.util.stream.Stream;
    import java.util.stream.StreamSupport;

    public class LinkParse {
        public static List<String> rez= new ArrayList<>();
        public static void mainLink() throws IOException {

            String url="https://orico-russia.ru";

            Element elM=page(url).select("div [class=center]").first();
            Elements els=elM.select("a");

            List<String> href= new ArrayList<>();
            els.forEach(e->href.add(e.attr("href")));

           List<String> href1=href.stream().takeWhile(n->!n.startsWith("#")).filter(n->n.split("/").length<5).collect(Collectors.toList());

            List<String> hrefSr= new ArrayList<>();
           List<String> href2 = null;
            for(String url2:href1) {
                Element element = page(url2).select("div[class=text-center]").first();
                Elements els2 = element.select("a");
                 href2 = els2.stream().map(n -> n.attr("href")).distinct().collect(Collectors.toList());
                hrefSr.add(url2);
                hrefSr.addAll(href2);
                 //href2.forEach(System.out::println);
            }

            href1.clear();
            href2.clear();





            //hrefSr.forEach(System.out::println);

            int i;
            System.out.println("количество разделов = "+hrefSr.size());



            //сорт внутр.


            for(String url2:hrefSr) {
            //  String url2=hrefSr.get(3);
                //System.out.println(url2);
                try {

                    Element element = page(url2).select("div[class=container]").first();
                    Elements els2 = element.select("a");
                     href2 = els2.stream().map(n -> n.attr("href")).distinct().collect(Collectors.toList());
                    rez.addAll(href2);
                    //els2.forEach(e->href2.add(e.attr("href")));

                    // href2.forEach(System.out::println);
                    i=href2.size();
                    System.out.println("карточек в разделе "+i);
                }catch (NullPointerException e){System.out.println("раздел пуст");}

            }

            System.out.println(rez.size());
        }


        private static Document page(String url) throws IOException {
            Document document= Jsoup.parse(new URL(url), 5000);
            return document;
        }

    }
