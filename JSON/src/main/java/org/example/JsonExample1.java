package org.example;

import org.json.JSONObject;

public class JsonExample1 {
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("이름","nhn");
        System.out.println(object);
        object.put("이름","academy");
        System.out.println(object);
    }
}
