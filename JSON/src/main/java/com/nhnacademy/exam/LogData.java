package com.nhnacademy.exam;

import java.util.LinkedList;
import java.util.List;

public class LogData {
    List<String> history;
    int count;

    public LogData() {
        int count = 0;
        history = new LinkedList<>();
    }

    public List<String> getHistory() {
        return history;
    }

    public void addHistory(String data){
        history.add(count++ + "번째 수정 내용 : " + data);
    }
}
