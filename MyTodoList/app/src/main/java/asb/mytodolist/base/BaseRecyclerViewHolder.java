package asb.mytodolist.base;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    public View inflate;
    public Context mContext;

    public BaseRecyclerViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.inflate = itemView;
        this.mContext = context;
    }

    public void setText(int tvID, CharSequence text) {
        TextView tx = inflate.findViewById(tvID);
        if (tx != null)  {
            tx.setText(text);
        }
    }

}
