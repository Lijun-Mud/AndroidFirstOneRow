package com.example.hb49417.myapplication;

/**
 * Created by LIJUN on 7/14/2017.
 */

public class Message {
    public static final int TYPE_RECEIVED=0;
    public static final int TYPE_SENT=1;

    public Message(String content, int type) {
        this.content = content;
        this.type = type;
    }

    private String content;
    private int type;

    public String getContent() {
        return content;
    }


    public int getType() {
        return type;
    }
}

