package com.example.papb_uas;

public class ToDoList {
    private String what, time;
    private int id;

    public ToDoList(String what, String time, int id){
        this.what = what;
        this.time = time;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getWhat() {
        return what;
    }
}
