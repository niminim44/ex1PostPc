package com.postpc.nimrod.ex1postpc.message;

public interface MessageContract {

    interface  View{

        void setMessageContent(String message);

        void setAuthor(String author);

        void setTimestamp(String timeStamp);
    }

    interface Presenter{

        void init();

        void onDeleteClicked();

        void onShareClicked();
    }

}
