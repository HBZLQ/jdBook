package cn.zlq.test1;

public class StringTest {

    public static void main(String[] args) {
        String a1 = "abc";
        String a2 = "abc";
        String a3 = new String("abc");
        String a4 = a3.intern();

        System.out.println("a1==a2?" + (a1 == a2));
        System.out.println("a1==a3?" + (a1 == a3));
        System.out.println("a1==a4?" + (a1 == a4));
        System.out.println("a3==a4?" + (a3 == a4));

        changeString(a1, a2, a3);
        System.out.println("1a1=" + a1 + "\na2=" + a2 + "\na3=" + a3 + "\na4=" + a4);
    }

    public static void changeString(String a1, String a2, String a3){
        a1 = "bca";
        a2 = "bca";
        a3 = new String("bca");
        System.out.println("a1=" + a1 + "\na2=" + a2 + "\na3=" + a3);
    }
}