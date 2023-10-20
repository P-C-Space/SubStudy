package com.nhnacademy.exam;

import java.util.ArrayList;
import java.util.List;

public class User {
    String id;
    String nickname;
    List<Equipment> equipmentList;

    MatchHistory matchHistory;

    LogData logData;

    public User(String id, String nickname) {
        this.equipmentList = new ArrayList<>();
        this.logData = new LogData();
        this.id = id;
        this.nickname = nickname;
        this.matchHistory = new MatchHistory();
        logData.addHistory("기본 생성");
    }

    public User(String id, String nickname, LogData logData, MatchHistory matchHistory,8 ) {
        this.equipmentList;
        this.id = id;
        this.nickname = nickname;
        this.matchHistory = matchHistory;
        this.logData = logData;
    }

    public String getId() {
        return this.id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public MatchHistory getMatchHistory() {
        return matchHistory;
    }

    public LogData getLogData() {
        return logData;
    }

    public void setNickname(String nickname) {
        logData.addHistory("nickName 변경 - " + this.nickname + " -> " + nickname);
        this.nickname = nickname;
    }

    public void setId(String id) {
        logData.addHistory("id 변경 - " + this.id + " -> " + id);
        this.id = id;
    }

    public void addEquipment(Equipment equipment) {
        logData.addHistory("장비 추가 - " + equipment);
        this.equipmentList.add(equipment);
    }

    public void removeEquipment(String name) {
        for (Equipment equipment : this.equipmentList) {
            if (equipment.getModelName().equals(name)) {
                logData.addHistory("장비 삭제 - " + name);
                this.equipmentList.remove(name);
                return;
            }
        }
        System.out.println("존재 하지 않는 장비");

    }

    public void win() {
        logData.addHistory("유저 승리");
        this.matchHistory.win();
    }

    public void lose() {
        logData.addHistory("유저 패도");
        this.matchHistory.lose();
    }
}
