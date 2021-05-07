package asb.mytodolist.models;

public class TodoItem {
    private String id;
    private String title;
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
