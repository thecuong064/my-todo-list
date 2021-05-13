package asb.mytodolist.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "to_do_item")
public class TodoItem {

    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "is_finished")
    private boolean isFinished;

    public TodoItem(String id, String title, boolean isFinished) {
        this.id = id;
        this.title = title;
        this.isFinished = isFinished;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
