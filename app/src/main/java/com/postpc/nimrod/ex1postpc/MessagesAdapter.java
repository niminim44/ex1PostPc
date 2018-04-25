package com.postpc.nimrod.ex1postpc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.postpc.nimrod.ex1postpc.events.MessageLongClickedEvent;

import org.greenrobot.eventbus.EventBus;

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
        holder.setData(messages.get(position), position);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(MessageModel messageModel) {
        messages.add(messageModel);
        notifyItemInserted(messages.size() - 1);
    }

    public void removeItem(int position) {
        messages.remove(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message_text_view)
        TextView messageTextView;

        @BindView(R.id.time_stamp_text_view)
        TextView timeStampTextView;

        private MessageModel messageModel;
        private int position;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    handleLongClickEvent();
                    return false;
                }
            });
        }

        private void handleLongClickEvent() {
            messageModel.setPosition(position);
            EventBus.getDefault().post(new MessageLongClickedEvent(messageModel));
        }

        void setData(MessageModel messageModel, int position) {
            this.messageModel = messageModel;
            this.position = position;
            messageTextView.setText(messageModel.getMessage());
            timeStampTextView.setText(messageModel.getTimeStamp());
        }
    }
}
