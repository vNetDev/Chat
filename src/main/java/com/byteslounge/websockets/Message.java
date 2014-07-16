package com.byteslounge.websockets;

import java.util.Date;

public class Message {

	private String nickname;
	private String text;
    private long time;

    public Message(){
        this("", "");
    }

    public Message(String author, String text){
        this.nickname = author;
        this.text = text;
        this.time = new Date().getTime();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
