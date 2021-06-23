package com.smsoft;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Report {
    public static void main(String[] args) {
        String text = "\u0020Наша любимая Java!)";
        System.out.println(getReport(text));

    }
    public static String getReport(String text){
        var tokens = new StringTokenizer(text);
        var totalAmountOfWords=tokens.countTokens();
        var regex="[^a-zA-Zа-яА-Я ]";
        var pattern =Pattern.compile(regex);
        var commasAndSymbolsCount= pattern.matcher(text).results().count();
        regex="[ ]";
        pattern =Pattern.compile(regex);
        var spaceCount= pattern.matcher(text).results().count();
        var sb = new StringBuilder();
        int c=0;

        var block= """
                « Text analysis
                    Total number of words - %s,
                    Commas and symbols - %s,
                    Spaces and other indents - %s.
                    Letters' combinations occurrences:
                """.formatted(totalAmountOfWords,commasAndSymbolsCount,spaceCount);
        sb.append(block);
        var map = getMap(text);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (c==map.size()-1){
                block= """
                    %s\040\040\040\040%s -\040%s»
                    """.formatted(++c+".",entry.getKey(),entry.getValue());
            }
            else {
                block= """
                    %s\040\040\040\040%s -\040%s
                    """.formatted(++c+".",entry.getKey(),entry.getValue());
            }
            sb.append(block);
        }

        block=sb.toString().indent(-66);
        return block;
    }
    private static HashMap<String,Integer> getMap(String text){
        text=text.trim();
        var map = new HashMap<String,Integer>();

        String []str = text.replaceAll(
                "[^a-zA-Z0-9а-яА-Я ]", "").split(" ");
        for (int i = 0; i < str.length; i++) {
            var curWord = str[i];
            for (int j = 0; j < curWord.length(); j++) {
                for (int k = 1; k <= curWord.substring(j).length(); k++) {
                    var subStr = curWord.substring(j, j + k);
                    if (!map.containsKey(subStr)) {
                        map.put(subStr, 0);
                    }
                    else continue;
                    for (String s : str) {
                        var matchesInCurWord = StringUtils.countMatches(s, subStr);
                        map.replace(subStr, map.get(subStr) + matchesInCurWord);
                    }
                }
            }
        }
        map = sortByValue(map, false);
        return map;
    }

    private static HashMap<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

}
