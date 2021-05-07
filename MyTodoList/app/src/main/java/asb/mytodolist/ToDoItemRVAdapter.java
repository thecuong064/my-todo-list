package asb.mytodolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import asb.mytodolist.models.TodoItem;
import asb.mytodolist.utils.DeviceUtils;

public abstract class ToDoItemRVAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {

    private int mLayoutID = -1;
    protected List<TodoItem> mListData = null;
    protected Context mContext;
    public boolean isInCheckingMode = false;

    public ToDoItemRVAdapter(int mLayoutID, Context mContext) {
        this.mLayoutID = mLayoutID;
        this.mContext = mContext;
    }

    public void setData(List<TodoItem> data) {
        this.mListData = data;
        notifyDataSetChanged();
    }

    public void addData(int position, TodoItem data) {
        mListData.add(position, data);
        notifyItemInserted(position);
    }

    public void addMore(List<TodoItem> data) {
        this.mListData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoItemViewHolder(LayoutInflater.from(mContext).
                inflate(mLayoutID, parent, false), mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoItemViewHolder holder, int position) {
        convert(holder, position);
    }

    protected void convert(TodoItemViewHolder holder, int position) {
        TodoItem item = mListData.get(position);

        holder.itemView.setSelected(item.isFinished());

        holder.checkBoxItem.setOnCheckedChangeListener((compoundButton, isChecked) -> {

            if (DeviceUtils.hideSoftKeyboard(mContext, holder.itemView))
            {
                holder.checkBoxItem.setChecked(item.isFinished());
                holder.checkBoxItem.jumpDrawablesToCurrentState();
                return;
            }

            item.setFinished(isChecked);
            mListData.set(position, item);
            holder.itemView.setSelected(item.isFinished());
            onItemCheckedChange(holder.itemView, position, isChecked);
        });

        holder.checkBoxItem.setChecked(item.isFinished());
        holder.checkBoxItem.setText(item.getTitle());

        holder.textViewItem.setText(item.getTitle());
        holder.textViewItem.setActivated(item.isFinished());

        holder.textViewItem.setOnClickListener(view -> {

            if (DeviceUtils.hideSoftKeyboard(mContext, holder.itemView))
                return;

            onItemClicked(holder.itemView, position);
        });

        holder.checkBoxItem.setVisibility(isInCheckingMode ? View.VISIBLE : View.GONE);
        holder.textViewItem.setVisibility(isInCheckingMode ? View.GONE : View.VISIBLE);
    }

    protected abstract void onItemCheckedChange(View view, int position, boolean isChecked);

    public abstract void onItemClicked(View view, int position);

    public void switchMode() {
        isInCheckingMode = !isInCheckingMode;
        notifyDataSetChanged();
    }

    public boolean isInCheckingMode() {
        return isInCheckingMode;
    }

    @Override
    public int getItemCount() {
        return (mListData == null || mListData.isEmpty()) ? 0 : mListData.size();
    }
}
