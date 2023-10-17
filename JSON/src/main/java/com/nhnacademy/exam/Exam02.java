package com.nhnacademy.exam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;

public class Exam02 {
    public static void main(String[] args) {
        JSONObject readJSONObject = null;
        try {
            FileReader file = new FileReader("./JSONTextFile.json");
            BufferedReader bufferedReader = new BufferedReader(file);
            readJSONObject = new JSONObject(bufferedReader.readLine());


        } catch (FileNotFoundException e) {
            System.out.println("파일 못 찾겠음");
        } catch (IOException e){
            System.out.println("못 읽겠음");
        }

        if(readJSONObject != null) {
            for(String key : readJSONObject.keySet()){
                JSONObject user = new JSONObject(readJSONObject.get(key).toString());
                String id;
                String nickname;

                for(String userkey : user.keySet()){
                    if(userkey.equals("matchHistory")){

                    }
                    else if(userkey.equals("logData")){

                    }
                    else if(userkey.equals("nickname")){
                        nickname = user.get(userkey).toString();
                    }
                    else if(userkey.equals("id")){

                    }
                    else if(userkey.equals("equipmentList")){

                    }
                }
            }
        }
    }
}
