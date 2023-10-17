package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonExample8 {
    public static void main(String[] args) throws JSONException {
        String [] cities = new String[]{"서울","부산","광주"};
        JSONArray jsonArray = new JSONArray();

        for(String city : cities){
            jsonArray.put(city);
        }

        JSONObject object = new JSONObject();
        object.put("도시", jsonArray);

        System.out.println(object);

    }
}
