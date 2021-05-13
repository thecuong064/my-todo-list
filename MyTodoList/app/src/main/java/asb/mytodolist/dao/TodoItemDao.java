package asb.mytodolist.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import asb.mytodolist.models.TodoItem;

@Dao
public interface TodoItemDao {
    @Query("SELECT * FROM to_do_item")
    List<TodoItem> getAll();

    @Query("SELECT * FROM to_do_item WHERE id IN (:itemIds)")
    List<TodoItem> loadAllByIds(int[] itemIds);

    @Query("SELECT * FROM to_do_item WHERE id LIKE :id")
    TodoItem findById(String id);

    @Insert
    void insertAll(TodoItem... items);

    @Insert
    void insert(TodoItem item);

    @Delete
    void delete(TodoItem item);
}
