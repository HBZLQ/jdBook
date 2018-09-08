package cn.zlq.test3;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MaxLengthStr {

    public static void main(String[] args) {
        Character[] x = {'a', 'b', 'c', 'b', 'd', 'a', 'b'};
        Character[] y = {'b', 'd', 'c', 'a', 'b', 'a'};

        int[][] num = new int[x.length + 1][y.length + 1];
        for(int i = 1; i <= x.length; i ++){
            for (int j = 1; j <= y.length; j ++){
                if(x[i - 1] == y[j - 1]){
                    num[i][j] = 1 + num[i - 1][j - 1];
                }else {
                    num[i][j] = Math.max(num[i - 1][j], num[i][j - 1]);
                }
                System.out.print(num[i][j] + " ");
            }
            System.out.println("");
        }
        int xPosition = x.length, yPositIon = y.length;
        List<Character> result = new LinkedList<Character>();

        while(xPosition != 0 && yPositIon != 0){
            if(x[xPosition - 1].equals(y[yPositIon - 1])){
                result.add(x[xPosition - 1]);
                xPosition --;
                yPositIon --;
            }else if(num[xPosition][yPositIon - 1] >= num[xPosition - 1][yPositIon]){
                yPositIon --;
            }else {
                xPosition --;
            }
        }
        Collections.reverse(result);
        System.out.println(result);
    }
}
