package org.example;


import java.util.Map;
import org.json.JSONObject;

public class Main {
    /*
    JSONObject()
    이름/값이 설정되지 않은 기본 생성자

    protected JSONObject(int initialCapacity)
    주어진 크기의 이름/값을 저장할 내부 자원 확보 생성자

    JSONObject(JSONObject jo, String... names)
    다른 JSONObject에서 나열된 이름만 추측하여 설정하는 생성자

    JSONObject(JSONTokener x)
    JSONTokener로부터 이름/값을 받아 설정하는 생성자

    JSONObject(Map m)
    자료구조 Map을 받아 설정하는 생성자

    JSONObject(Object bean)
    자바 Object의 getter들을 이용해 설정하는 생성자
    POJO를 이용해 설정하는 생성자

    JSONObject(Object object, String... names)
    자바 Object에서 reflection을 이용해 특정 멤버만 찾아 설정하는 생성자.

    JSONObject(String source)
    JSON 텍스트에서 변환해 설정하는 생성자

    JSONObject(String baseName, Locale locale)
    ResourceBundle.* 에서 이름/값을 찾아 설정하는 생성자
     */
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("이름","nhn");

        System.out.println(object.toString());

        JSONObject objectAdderss = new JSONObject();
        objectAdderss.put("우편번호",13487);
        objectAdderss.put("도시","성남");

        object.put("주소",objectAdderss);
        System.out.println(object.toString());
    }
}