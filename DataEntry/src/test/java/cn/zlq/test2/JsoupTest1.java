package cn.zlq.test2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupTest1 {

    public static void main(String[] args) throws IOException {

        String url = String.valueOf("https://item.jd.com/12280850.html");
        Document document = Jsoup.connect(url).get();
        //获得名称
        String title = document.title();
        System.out.println(title);

        //获得a标签里面的href属性
        Elements links = document.select("a[href]");
        if(null != links){
            for (Element element : links){
                String linkHref = element.attr("href");
                String linkTest = element.text();
                System.out.println(linkHref + " " + linkTest);
            }
        }

        //获取class=‘sku-name’的text
        Elements elementsByClass = document.getElementsByClass("sku-name");
        if(null != elementsByClass){
            System.out.println("********************************");
            for (Element element : elementsByClass){
                System.out.println(element.text());
            }
        }
        Element element = document.select(".sku-name").first();
        if(element != null){
            System.out.println("********************************");
            System.out.println(element.text());
        }

        //通过ID获取element
        Element element1 = document.select("#comment-count").first();
        if(element != null){
            System.out.println("********************************");
            System.out.println(element1.attr("clstag"));
        }
    }
}
