package org.example;

import org.json.JSONArray;

public class JsonArrayExample {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray(new int[]{1,2,3});
        System.out.println(jsonArray);
    }
}
