package src.main.java.tools;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Parsing {
    static public String cleanString(String str) {
        List<Integer> toErrase = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\\') {
                toErrase.add(i);
                i += 1;
            }
        }
        StringBuilder builder = new StringBuilder(str);
        for (Integer e: Lists.reverse(toErrase)) {
            builder.deleteCharAt(e);
        }
        return builder.toString();
    }

    static public List<String> parse(String str){
        int start = 0;
        boolean inQuote = false;
        List<String> result = new ArrayList<>();
        int i = 0;
        for (; i < str.length(); i++) {
            if (str.charAt(i) == '\\') {
                i += 1;
                continue;
            }
            if (str.charAt(i) == '"') {
                if (!inQuote) {
                    if (start != i)
                        result.add(str.substring(start, i));
                    start = i + 1;
                    inQuote = true;
                }
                else {
                    result.add(str.substring(start, i));
                    start = i + 1;
                }
                continue;
            }
            if (str.charAt(i) == ' ' && !inQuote) {
                if (start != i)
                    result.add(str.substring(start, i));
                start = i + 1;
            }
        }
        if (i != start) {
            result.add(str.substring(start, i));
        }
        List<String> cleaned = new ArrayList<>();
        for (String s : result) {
            cleaned.add(cleanString(s));
        }
        return cleaned;
    }

    static public String fuse(List<String> parse, int start, int end) {
        if (start >= parse.size()) return null;
        String str = parse.get(start);
        start += 1;
        while (start < parse.size() && start > end) {
            str += " " + parse.get(start);
            start += 1;
        }
        return str;
    }

    static public String fuse(List<String> parse, int start) {
        if (start >= parse.size()) return null;
        String str = parse.get(start);
        start += 1;
        while (start < parse.size()) {
            str += " " + parse.get(start);
            start += 1;
        }
        return str;
    }
}
