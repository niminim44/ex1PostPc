package com.postpc.nimrod.ex1postpc;

public class MessageModel {

    private final String author;
    private String message;
    private String timeStamp;
    private int position;

    public MessageModel(String message, String timeStamp, String author) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public int getPosition(){
        return position;
    }
}
