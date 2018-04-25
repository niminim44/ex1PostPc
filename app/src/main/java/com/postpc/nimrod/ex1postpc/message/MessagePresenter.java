package com.postpc.nimrod.ex1postpc.message;

import com.postpc.nimrod.ex1postpc.MessageModel;
import com.postpc.nimrod.ex1postpc.events.DeleteMessageClickedEvent;
import com.postpc.nimrod.ex1postpc.events.ShareMessageClickedEvent;

import org.greenrobot.eventbus.EventBus;

class MessagePresenter implements MessageContract.Presenter{


    private final MessageContract.View view;
    private final MessageModel messageModel;

    MessagePresenter(MessageContract.View view, MessageModel messageModel) {
        this.view = view;
        this.messageModel = messageModel;
    }

    @Override
    public void init() {
        if(messageModel != null){
            view.setMessageContent(messageModel.getMessage());
            view.setAuthor(messageModel.getAuthor());
            view.setTimestamp(messageModel.getTimeStamp());
        }
    }

    @Override
    public void onDeleteClicked() {
        EventBus.getDefault().post(new DeleteMessageClickedEvent(messageModel.getPosition()));
    }

    @Override
    public void onShareClicked() {
        EventBus.getDefault().post(new ShareMessageClickedEvent(messageModel.getMessage()));
    }
}
