package com.github.frcsty.pouches.utils.parser;

import java.util.Arrays;
import java.util.regex.Pattern;

public class TitleParser {

    private static final Pattern PARAM_SPLITTER = Pattern.compile(" ");
    private static final Pattern DATA_SPLITTER = Pattern.compile(";");
    private static final Pattern TEXT_SPLITTER = Pattern.compile(":");

    private final int[] params;
    private final String[] text;

    public TitleParser(final String data) {
        final String[] parts = DATA_SPLITTER.split(data);

        this.params = Arrays.stream(PARAM_SPLITTER.split(parts[0]))
                .mapToInt(Integer::parseInt)
                .toArray()
        ;

        if (parts.length == 2) {
            text = TEXT_SPLITTER.split(parts[1]);
        } else {
            text = new String[]{};
        }
    }

    public int getFadeIn() {
        return params[0];
    }

    public int getStay() {
        return params[1];
    }

    public int getFadeOut() {
        return params[2];
    }

    public String getTitle() {
        return text.length > 0 ? text[0] : "";
    }

    public String getSubTitle() {
        if (text.length > 0) {
            if (text[1].contains("$empty")) {
                return "";
            }
            return text[1];
        }
        return "";
    }
}
