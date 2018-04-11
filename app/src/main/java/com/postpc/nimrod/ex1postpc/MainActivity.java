package com.postpc.nimrod.ex1postpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.postpc.nimrod.ex1postpc.MainPresenter.EMPTY_STRING;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    @BindView(R.id.edit_text)
    EditText editText;

    @BindView(R.id.send_button)
    ImageView sendButton;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MainContract.Presenter presenter;
    private MessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        presenter.init();
    }

    @OnClick(R.id.send_button)
    public void onSendClicked(){
        presenter.handleSendClicked();
    }

    @Override
    public void initRecyclerView() {
        adapter = new MessagesAdapter(new ArrayList<MessageModel>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public String getEditTextMessage() {
        Editable editable = editText.getText();
        if(editable != null){
            return editable.toString();
        }
        return EMPTY_STRING;
    }

    @Override
    public void addMessageToRecyclerView(MessageModel messageModel) {
        adapter.addMessage(messageModel);
    }

    @Override
    public void clearEditText() {
        editText.setText(EMPTY_STRING);
    }
}
