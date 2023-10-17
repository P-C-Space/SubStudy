package com.nhnacademy.exam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class Exam01 {

    public static void main(String[] args) {
        int count = 1;
        User user = new User("jeongwoo", "쏘씨오패쓰");
        user.addEquipment(new Equipment("수호천사", 40, 45));
        user.addEquipment(new Equipment("죽음의 무도", 45, 55));
        user.win();
        user.win();
        user.win();
        user.win();
        user.win();
        user.win();
        user.removeEquipment("죽음의 무도");


        JSONObject userInfo = new JSONObject();
        userInfo.put(("user" + count++),new JSONObject(user));
        System.out.println(userInfo.toString());

        try{
            FileWriter file = new FileWriter("./JSONTextFile.json");
            String storeData = userInfo.toString();

            BufferedWriter writer = new BufferedWriter(file);
            writer.write(storeData);
            writer.close();
            System.out.println("파일 입력 완료");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
