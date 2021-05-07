package asb.mytodolist;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import asb.mytodolist.base.BaseRecyclerViewHolder;

public class TodoItemViewHolder extends BaseRecyclerViewHolder {

    public CheckBox checkBoxItem;
    public TextView textViewItem;

    public TodoItemViewHolder(@NonNull View itemView, Context context) {
        super(itemView, context);
        checkBoxItem = itemView.findViewById(R.id.cb_item);
        textViewItem = itemView.findViewById(R.id.tv_item);
    }
}
