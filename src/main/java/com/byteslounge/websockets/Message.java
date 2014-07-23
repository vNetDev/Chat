package com.byteslounge.websockets;

import java.util.Date;

public class Message {

	private String nickname;
	private String text;
    private long time;

    public Message(){
        this("", "", 1);
    }

    public Message(String nickname, String text, long time){
        this.nickname = nickname;
        this.text = text;
        this.time = time;
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
        return new Date().getTime();
    }

    public void setTime(long time) {
        this.time = time;
    }
}
