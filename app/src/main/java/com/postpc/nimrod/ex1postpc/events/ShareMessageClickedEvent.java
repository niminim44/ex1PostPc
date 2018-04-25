package com.postpc.nimrod.ex1postpc.events;

import com.postpc.nimrod.ex1postpc.MessageModel;

public class ShareMessageClickedEvent {
    private final String message;

    public ShareMessageClickedEvent(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
