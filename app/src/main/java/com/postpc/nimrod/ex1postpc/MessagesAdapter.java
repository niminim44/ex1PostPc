package com.postpc.nimrod.ex1postpc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private final List<MessageModel> messages;

    MessagesAdapter(List<MessageModel> messages) {
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MessageModel message = messages.get(position);
        holder.setData(message.getMessage(), message.getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(MessageModel messageModel) {
        messages.add(messageModel);
        notifyItemInserted(messages.size() - 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message_text_view)
        TextView messageTextView;

        @BindView(R.id.time_stamp_text_view)
        TextView timeStampTextView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData(String message, String timeStamp) {
            messageTextView.setText(message);
            timeStampTextView.setText(timeStamp);
        }
    }
}
