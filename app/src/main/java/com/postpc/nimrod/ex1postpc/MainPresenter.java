package com.postpc.nimrod.ex1postpc;

class MainPresenter implements MainContract.Presenter{

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
            view.addMessageToRecyclerView(new MessageModel(message));
        }
        view.clearEditText();
    }
}
