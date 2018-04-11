package com.postpc.nimrod.ex1postpc;

public interface MainContract {

    interface View{

        void initRecyclerView();

        String getEditTextMessage();

        void addMessageToRecyclerView(MessageModel messageModel);

        void clearEditText();

        void scrollRecyclerViewToBottom();

    }

    interface Presenter{

        void init();

        void handleSendClicked();
    }

}
