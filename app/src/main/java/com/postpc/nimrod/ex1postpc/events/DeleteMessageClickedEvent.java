package com.postpc.nimrod.ex1postpc.events;

public class DeleteMessageClickedEvent {
    private final int position;

    public DeleteMessageClickedEvent(int position) {
        this.position = position;
    }

    public int getPosition(){
        return position;
    }
}
