package com.postpc.nimrod.ex1postpc;

import android.annotation.SuppressLint;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class MainPresenter implements MainContract.Presenter{

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");


    static final String EMPTY_STRING = "";
    private final MainContract.View view;

    MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        view.initRecyclerView();
    }

    @Override
    public void handleSendClicked() {
        String message = view.getEditTextMessage();
        if((message != null) && (!message.equals(EMPTY_STRING))){
            view.addMessageToRecyclerView(new MessageModel(message, getCurrentTimeInString()));
        }
        view.clearEditText();
        view.scrollRecyclerViewToBottom();
    }

    private String getCurrentTimeInString() {
         return sdf.format((new Timestamp(System.currentTimeMillis())));
    }
}
