package cn.jdbook.common;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHeading3;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.html.dom.HTMLAnchorElementImpl;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

    /**
     * 根据网络地址和编码回去网络内容
     * @param u
     * @param charSet
     * @return
     */
    public static HttpResult getHttpContext(String u, String charSet){
        HttpResult result = new HttpResult();
        if(StringUtils.isNotEmpty(u) && StringUtils.isNotEmpty(charSet)){
            try {
                URL url = new URL(u);
                HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
                httpUrlConn.setDoInput(true);
                InputStream input = httpUrlConn.getInputStream();
                InputStreamReader read = new InputStreamReader(input, charSet);
                BufferedReader br = new BufferedReader(read);
                StringBuffer sb = new StringBuffer();
                String data = br.readLine();
                sb.append(data);
                while(data!=null)  {
                    data=br.readLine();
                    sb.append(data);
                }
                br.close();
                read.close();
                input.close();
                httpUrlConn.disconnect();
                result.setCode(Integer.valueOf(ResultCode.SUCCESS.getValue()));
                result.setContext(sb.toString());
            } catch (Exception e) {
                result.setCode(Integer.valueOf(ResultCode.FAULT.getValue()));
            }
        }else {
            result.setCode(Integer.valueOf(ResultCode.FAULT.getValue()));
        }
        return result;
    }

    /**
     * 采用webClient收集网络数据
     * @param url
     * @return
     */
    public static HtmlElement getHtmlData(String url){
        try {
            WebClient webClient = new WebClient();
            webClient.addRequestHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            webClient.addRequestHeader("accept-encoding", "gzip, deflate, br");
            webClient.addRequestHeader("accept-language", "zh-CN,zh;q=0.9");
            webClient.addRequestHeader("cache-control", "max-age=0");
//            webClient.addRequestHeader("cookie", "TrackID=13Z3kuGg4EHGoFMxF7RaIt01096QJBR52IzmZXclj_OoaRTwUkVzcX-XS8j_aN8KbPCqUAjaTh-e9jgvz_61U7BgQPiTPTIVUoHAszcfHi5k; pinId=MPtXc6HVLa4ZKsuTis9xFg; unpl=V2_ZzNtbUFXShB8W0YEfB1dUGJRFg8SAxcRJw1HXX9JWlVuABcKclRCFXwUR1JnGl8UZgsZXUZcQhNFCHZXchBYAWcCGllyBBNNIEwHDCRSBUE3XHxcFVUWF3RaTwEoSVoAYwtBDkZUFBYhW0IAKElVVTUFR21yVEMldQl2VH8aXQZgABFfS1BCHHUMR1R%2bEF4FYjMiWnJncxZwCEZQeCldNWYzUAkeV0YSdw1CGXsdXwRkBBFeQF5EFHwIQlV7HFUHZwYiXHJU; __jdv=122270672|baidu-pinzhuan|t_288551095_baidupinzhuan|cpc|0f3d30c8dba7459bb52f2eb5eba8ac7d_0_21848b1a650d4c5caee5c4085a7a824f|1532222351978; PCSYCityID=1; shshshfp=5b24fa9df50cc84bb0432a035c13d003; shshshfpa=4581906d-e08f-4dad-3fda-1e3430ee676d-1532222369; shshshsID=184b65fe1cbaa9c3091a1808454b3d26_2_1532222369378; __jda=122270672.1520262238608150148314.1520262238.1528688626.1532222352.4; __jdb=122270672.4.1520262238608150148314|4.1532222352; __jdc=122270672; shshshfpb=193a05ad59e8e45d69851b82fe46e375524261ef0ac372aa05b0fe3e41; ipLocation=%u5317%u4EAC; areaId=1; ipLoc-djd=1-72-2799-0; SL_GWPT_Show_Hide_tmp=1; SL_wptGlobTipTmp=1; 3AB9D23F7A4B3C9B=YJEI7YSVFPJQZX5RP5Q3NYTTYL3P5OQQF3RKDMS4NYGPENOXRAOQLKHZJ3GS4ZHGDIFVWIHPZNJQUI6KTV675ZFJBI; __jdu=1520262238608150148314");
            webClient.addRequestHeader("if-modified-since", "Sun, 22 Jul 2018 01:15:25 GMT");
            webClient.addRequestHeader("referer", "https://book.jd.com/");
            webClient.addRequestHeader("upgrade-insecure-requests", "1");
            webClient.addRequestHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");

            HtmlPage page = (HtmlPage) webClient.getPage(url);
            HtmlElement element =  page.getHtmlElementById("name");
            element.getTextContent();
            DomNode skuNameNode = element.getFirstChild();
            String skuName = skuNameNode.getTextContent();
            System.out.println(skuName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取页面里面所有A标签的href属性
     * @param node
     * @param urlSet
     */
    public static void getAHREF(Node node, Set<String> urlSet){
        if(node instanceof HTMLAnchorElementImpl){
            String href = ((HTMLAnchorElementImpl)node).getAttribute("href");
            urlSet.add(href);
        }
        Node child = node.getFirstChild();
        while(child != null){
            getAHREF(child, urlSet);
            child = child.getNextSibling();
        }
    }

    /**
     * 通过H3标签以及H3标签里面的内容获取后面该内容的具体描述
     * @param node
     * @param description
     * @return
     */
    public static String getH3Text(Node node, String description){
        if(StringUtils.isNotEmpty(description)){
            if (node instanceof HtmlHeading3){
                String text = ((HtmlHeading3)node).getNodeValue();
                if (StringUtils.isNotEmpty(text) && description.equals(text)){
                    //TODO 获取后面属性值
                    return "";

                }
            }
        }else {
            return "";
        }
        Node child = node.getFirstChild();
        while(child != null){
            getH3Text(child, description);
            child = child.getNextSibling();
        }

        return "";
    }

    /**
     * 正则表达式匹配两个指定字符串中间的内容
     * @param soap
     * @return
     */
    public static List<String> getSubUtil(String soap, String rgex){
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }
    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     * @param soap
     * @param rgex
     * @return
     */
    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }

    public static String getBookDetailContent(String soap, String detailName){

        String rgex = "<h3>" + detailName + "</h3>(.*?)</p>";
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1).replace("\\n</div>\\n<divclass=\\\"item-mc\\\">\\n<divclass=\\\"book-detail-content\\\"><p>", "");
        }
        return "";
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        getHtmlData("https://item.jd.com/12368807.html");
//        HttpResult result = getHttpContext("https://item.jd.com/12368807.html", Constant.GBK);
//        if(result.getCode() == ResultCode.SUCCESS.getValue()){
//            String str = result.getContext();
//            System.out.println(getSubUtilSimple(str, Constant.SKUNAMEMACHER));
//            System.out.println(getSubUtilSimple(str, Constant.AUTHORMACHER));
//        }

//        HttpResult result1 = getHttpContext("https://dx.3.cn/desc/12368807?cdn=2&callback=showdesc", Constant.GBK);
//        if(result1.getCode() == ResultCode.SUCCESS.getValue()){
//            String str = result1.getContext();
//            str = str.replaceAll(" ", "");
//            System.out.println(getBookDetailContent(str, "编辑推荐"));
//            System.out.println(getBookDetailContent(str, "内容简介"));
//            System.out.println(getBookDetailContent(str, "精彩书评"));
//            System.out.println(getBookDetailContent(str, "精彩书摘"));
//            System.out.println(getBookDetailContent(str, "作者简介"));
//        }
    }
}
