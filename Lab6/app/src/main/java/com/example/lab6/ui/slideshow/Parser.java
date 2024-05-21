package com.example.lab6.ui.slideshow;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static List<String> parseRateValues(String xmlData) {
        List<String> values = new ArrayList<>();

        Pattern patternRate = Pattern.compile("<VunitRate>(.*?)</VunitRate>");
        Matcher matcherRate = patternRate.matcher(xmlData);

        // Поиск и извлечение значений из тегов <VALUE>
        while (matcherRate.find()) {
            values.add(matcherRate.group(1)); // Добавляем значение в список
        }

        return values;
    }

    public static List<String> parseNameValues(String xmlData) {
        List<String> values = new ArrayList<>();

        Pattern patternName = Pattern.compile("<Name>(.*?)</Name>");
        Matcher matcherName = patternName.matcher(xmlData);

        // Поиск и извлечение значений из тегов <VALUE>
        while (matcherName.find()) {
            values.add(matcherName.group(1)); // Добавляем значение в список
        }

        return values;
    }
}
