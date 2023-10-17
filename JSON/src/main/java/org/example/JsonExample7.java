package org.example;

import java.util.Iterator;
import org.json.JSONObject;

public class JsonExample7 {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject("{\"모델\":\"K2\",\"공격력\" : 10, \"무한궤도형\" : true }");

        Iterator<String> iter = jsonObject.keys();

        while(iter.hasNext()){
            String key = iter.next();
            System.out.println(key + " : " + jsonObject.get(key));
        }
    }
}
