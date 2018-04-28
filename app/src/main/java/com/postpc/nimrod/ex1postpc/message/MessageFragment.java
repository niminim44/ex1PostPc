package com.postpc.nimrod.ex1postpc.message;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.postpc.nimrod.ex1postpc.MessageModel;
import com.postpc.nimrod.ex1postpc.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment implements MessageContract.View{

    @BindView(R.id.message_text_view)
    TextView messageTextView;

    @BindView(R.id.author_text_view)
    TextView authorTextView;

    @BindView(R.id.timestamp_text_view)
    TextView timestampTextView;

    private static final String MESSAGE_POSITION = "message_position";
    private static final String MESSAGE_CONTENT = "message_content";
    private static final String MESSAGE_AUTHOR = "message_author";
    private static final String MESSAGE_TIMESTAMP = "message_timestamp";
    private MessageContract.Presenter presenter;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance(MessageModel messageModel){
        MessageFragment messageFragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putString(MESSAGE_CONTENT, messageModel.getMessage());
        args.putString(MESSAGE_AUTHOR, messageModel.getAuthor());
        args.putString(MESSAGE_TIMESTAMP, messageModel.getTimeStamp());
        args.putInt(MESSAGE_POSITION, messageModel.getPosition());
        messageFragment.setArguments(args);
        return messageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        MessageModel messageModel = null;
        if(getArguments() != null){
            messageModel = new MessageModel(getArguments().getString(MESSAGE_CONTENT),
                    getArguments().getString(MESSAGE_TIMESTAMP), getArguments().getString(MESSAGE_AUTHOR));
            messageModel.setPosition(getArguments().getInt(MESSAGE_POSITION));
        }
        presenter = new MessagePresenter(this, messageModel);
        presenter.init();
        return view;
    }

    @Override
    public void setMessageContent(String message) {
        messageTextView.setText(message);
    }

    @Override
    public void setAuthor(String author) {
        String authorString = "Author: " + author;
        authorTextView.setText(authorString);
    }

    @Override
    public void setTimestamp(String timeStamp) {
        String timestampString = "At: " + timeStamp;
        timestampTextView.setText(timestampString);
    }



    @OnClick(R.id.delete_button)
    public void onDeleteClicked(){
        presenter.onDeleteClicked();
    }

    @OnClick(R.id.share_button)
    public void onShareClicked(){
        presenter.onShareClicked();
    }

    @OnClick(R.id.back_button)
    public void onBackButtonClicked(){
        getActivity().onBackPressed();
    }
}
