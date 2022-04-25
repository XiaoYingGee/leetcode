package com.xiaoyingge.algorithm.part02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/4/21 0:31
 */
public class Question0151 {

    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        if (s.trim() == "") {
            return "";
        }
        String target = s.trim();

        int left = 0;
        int right = 0;
        List<Info> infos = new ArrayList<>();
        while (right < target.length()) {
            while (right < target.length() && target.charAt(right) != ' ') {
                right++;
            }
            infos.add(new Info(left, right));
            while (right < target.length() && target.charAt(right) == ' ') {
                right++;
            }
            left = right;
        }

        //infos not be empty
        StringBuilder sb = new StringBuilder();
        for (int i = infos.size() - 1; i >= 0; i--) {
            Info info = infos.get(i);
            sb.append(" ").append(target, info.left, info.right);
        }
        return sb.substring(1);
    }

    public class Info {

        int left;
        int right;

        Info(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        String result = new Question0151().reverseWords("the sky is blue");
        System.out.println(result);
    }
}
