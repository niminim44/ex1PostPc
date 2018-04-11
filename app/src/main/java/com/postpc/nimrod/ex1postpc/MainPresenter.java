package com.postpc.nimrod.ex1postpc;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

class MainPresenter implements MainContract.Presenter{

    private static final String TIME_STAMP_FORMAT = "HH:mm";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(TIME_STAMP_FORMAT);
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
