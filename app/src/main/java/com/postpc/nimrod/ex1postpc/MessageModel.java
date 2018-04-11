package com.postpc.nimrod.ex1postpc;

class MessageModel {

    private String message;
    private String timeStamp;

    MessageModel(String message, String timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
