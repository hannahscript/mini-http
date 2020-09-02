package com.github.hannahscript.minihttp.protocols.dict;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines possible dictionary protocol command verbs
 */
public enum Verb {
    GET, SET;

    private static final Map<String, Verb> translationMap = new HashMap<>();
    static {
        translationMap.put("GET", Verb.GET);
        translationMap.put("SET", Verb.SET);
    }


    public static Verb fromString(String verb) {
        return translationMap.get(verb);
    }
}
