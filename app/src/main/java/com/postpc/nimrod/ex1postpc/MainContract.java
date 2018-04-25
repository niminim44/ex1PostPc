package com.postpc.nimrod.ex1postpc;

public interface MainContract {

    interface View{

        void initRecyclerView();

        String getEditTextMessage();

        void addMessageToRecyclerView(MessageModel messageModel);

        void clearEditText();

        void scrollRecyclerViewToBottom();

        void deleteMessageFromAdapter(int position);

        void shareMessage(String message);
    }

    interface Presenter{

        void init();

        void handleSendClicked();

        void handleDeleteClicked(int position);

        void handleShareClicked(String message);
    }

}
