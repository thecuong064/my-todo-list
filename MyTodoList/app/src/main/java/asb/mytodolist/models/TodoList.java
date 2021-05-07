package asb.mytodolist.models;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private int id;
    private String title;
    private List<TodoItem> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TodoItem> getItems() {
        if (items == null)
            items = new ArrayList<>();
        return items;
    }

    public void setItems(List<TodoItem> items) {
        this.items = items;
    }

    public void addItem(TodoItem item) {
        if (items == null)
            items = new ArrayList<>();
        items.add(item);
    }
}
