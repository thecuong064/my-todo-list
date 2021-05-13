package asb.mytodolist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import asb.mytodolist.dao.TodoItemDao;
import asb.mytodolist.models.TodoItem;

@Database(entities = {TodoItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoItemDao itemDao();
}
