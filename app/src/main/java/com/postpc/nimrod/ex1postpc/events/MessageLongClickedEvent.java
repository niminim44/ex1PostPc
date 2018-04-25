package com.postpc.nimrod.ex1postpc.events;

import com.postpc.nimrod.ex1postpc.MessageModel;

public class MessageLongClickedEvent {


    private final MessageModel messageModel;

    public MessageLongClickedEvent(MessageModel messageModel) {
        this.messageModel = messageModel;
    }

    public MessageModel getMessageModel(){
        return messageModel;
    }
}
