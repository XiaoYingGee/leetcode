package com.xiaoyingge.algorithm.part21;

/**
 * @author XiaoYingGee
 * @date 2022/4/18 23:16
 */
public class Question2000 {

    public String reversePrefix(String word, char ch) {
        if (word == null) {
            return null;
        }
        if (word.length() == 0) {
            return "";
        }
        char[] chars = word.toCharArray();
        int index = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ch) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return word;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = index; i >= 0; i--) {
            sb.append(chars[i]);
        }
        if (index < word.length() - 1) {
            for (int i = index + 1; i < word.length(); i++) {
                sb.append(chars[i]);
            }

        }
        return sb.toString();
    }
}
