package com.postpc.nimrod.ex1postpc;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.postpc.nimrod.ex1postpc.events.DeleteMessageClickedEvent;
import com.postpc.nimrod.ex1postpc.events.MessageLongClickedEvent;
import com.postpc.nimrod.ex1postpc.events.ShareMessageClickedEvent;
import com.postpc.nimrod.ex1postpc.message.MessageFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    private MainContract.Presenter presenter;
    private MessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        presenter.init();
    }

    @Override
    protected void onDestroy() {
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
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
        return (editable != null) ? editable.toString() : EMPTY_STRING;
    }

    @Override
    public void addMessageToRecyclerView(MessageModel messageModel) {
        adapter.addMessage(messageModel);
    }

    @Override
    public void clearEditText() {
        editText.setText(EMPTY_STRING);
    }

    @Override
    public void scrollRecyclerViewToBottom() {
        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void deleteMessageFromAdapter(int position) {
        adapter.removeItem(position);
    }

    @Override
    public void shareMessage(String message) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Subscribe
    public void onMessageLongClicked(MessageLongClickedEvent messageLongClickedEvent){
        fragmentContainer.setVisibility(View.VISIBLE);
        MessageFragment messageFragment = MessageFragment.newInstance(messageLongClickedEvent.getMessageModel());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, messageFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Subscribe
    public void onMessageDeleteClickedEvent(DeleteMessageClickedEvent event){
        presenter.handleDeleteClicked(event.getPosition());
        onBackPressed();
    }

    @Subscribe
    public void onShareMessaageClicked(ShareMessageClickedEvent event){
        presenter.handleShareClicked(event.getMessage());
        onBackPressed();
    }
}
