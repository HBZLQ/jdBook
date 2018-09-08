package cn.zlq.test1;

import org.apache.html.dom.HTMLAnchorElementImpl;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Node;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = String.valueOf("http://www.tlsk.com.cn");
        DOMParser parser = new DOMParser();
        parser.parse(url);
        print(parser.getDocument(), "");
        Set<String> set = new HashSet<String>();
        extract(parser.getDocument(), set);
        System.out.println("该页面一共有" + set.size() + "条连接");
    }

    public static void print(Node node, String indent){
        if(node.getNodeValue() != null){
            if(!"".equals(node.getNodeName().trim())){
                System.out.println(indent);
                System.out.println(node.getNodeValue());
            }
        }

        Node child = node.getFirstChild();
        while(child != null){
            print(child, indent + "");
            child = child.getNextSibling();
        }
    }

    public static void extract(Node node, Set<String> urlSet){
        if(node instanceof HTMLAnchorElementImpl){
            String href = ((HTMLAnchorElementImpl)node).getAttribute("href");
            urlSet.add(href);
        }
        Node child = node.getFirstChild();
        while(child != null){
            extract(child, urlSet);
            child = child.getNextSibling();
        }
    }
}
