package org.example;

import org.json.JSONObject;

public class JsonExample2 {
    public static void main(String[] args) {
        JSONObject object = new JSONObject("{\"이름\" : \"nhn\"}");
        System.out.println(object.toString());

        // 연습문제 2
        JSONObject jsonObject = new JSONObject("{\"city\":\"성남\",\"postNumber\":\"13487\"}");
        object.put("address",jsonObject);
        object.put("name","nhn");
        System.out.println(object.toString());

        JSONObject object1 = new JSONObject();
        object1.put("이름", "nhn");

        String name = object1.getString("이름");
        System.out.println(name);
    }
}
