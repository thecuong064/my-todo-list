package asb.mytodolist.activities;

import android.content.Intent;
import android.graphics.Rect;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import asb.mytodolist.R;
import asb.mytodolist.ToDoItemRVAdapter;
import asb.mytodolist.TodoListApp;
import asb.mytodolist.base.BaseActivity;
import asb.mytodolist.constants.IntentExtras;
import asb.mytodolist.constants.RequestCode;
import asb.mytodolist.databinding.ActivityMainBinding;
import asb.mytodolist.models.TodoItem;
import asb.mytodolist.utils.DeviceUtils;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private ToDoItemRVAdapter adapter;
    private List<TodoItem> items = new ArrayList<>();
    private Menu mMenu;

    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        mMenu = menu;
        mMenu.findItem(R.id.action_delete).setVisible(adapter.isInCheckingMode());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (DeviceUtils.hideSoftKeyboard(this))
            return super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.action_lock:
                adapter.switchMode();
                item.setTitle(adapter.isInCheckingMode() ? "Lock" : "Unlock");
                mMenu.findItem(R.id.action_delete).setVisible(adapter.isInCheckingMode());
                return true;
            case R.id.action_delete:
                executeItemRemoval();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == RequestCode.EDIT_ITEM) {
                Toast.makeText(this, "Changes are made", Toast.LENGTH_SHORT).show();
                refreshStoredData();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void afterCreate() {
        super.afterCreate();
        setSupportActionBar(binding.myToolbar);
        getStoredData();
        initView();
        initControlsAction();
    }

    private void getStoredData() {
        items = TodoListApp.getAppDatabase().itemDao().getAll();
    }

    private void refreshStoredData() {
        TodoItem newItem = new TodoItem(UUID.randomUUID().toString(), "dummy", false);
        TodoListApp.getAppDatabase().itemDao().insert(newItem);
        getStoredData();
        adapter.setData(items);
    }

    private void initView() {
        binding.itemsRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.itemsRV.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 10;
            }
        });

        adapter = new ToDoItemRVAdapter(R.layout.item_todo, this) {
            @Override
            public void onItemCheckedChange(View view, int position, boolean isChecked) {
                TodoItem item = items.get(position);
                item.setFinished(isChecked);
                items.set(position, item);
            }

            @Override
            public void onItemClicked(View view, int position) {
                // TODO: open details screen
                openDetailsScreen(position);
            }
        };

        binding.itemsRV.setAdapter(adapter);
        adapter.setData(items);
    }

    private void initControlsAction() {
        binding.addBtn.setOnClickListener(view -> {
            CharSequence newListTitle = binding.newItemText.getText();
            if (newListTitle != null && newListTitle.length() > 0) {
                TodoItem newItem = new TodoItem(UUID.randomUUID().toString(), newListTitle.toString(), false);
                items.add(newItem);
                adapter.notifyDataSetChanged();
                TodoListApp.getAppDatabase().itemDao().insert(newItem);
                binding.itemsRV.smoothScrollToPosition(items.size());
                binding.newItemText.setText(null);
                Toast.makeText(this, "New item: " + newListTitle, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please enter valid content", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void executeItemRemoval() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure want to delete these items")
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    List<TodoItem> notFinishedItems = new ArrayList<>();
                    for (TodoItem item : items)
                        if (!item.isFinished()) {
                            notFinishedItems.add(item);
                        }
                    items = notFinishedItems;
                    adapter.setData(notFinishedItems);
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                    // do something if cancel tapped
                }).create().show();
    }

    private void openDetailsScreen(int position) {
        Intent intent = new Intent(this, ItemDetailsActivity.class);
        intent.putExtra(IntentExtras.EXTRA_ITEM_ID, items.get(position).getId());
        startActivityForResult(intent, RequestCode.EDIT_ITEM);
    }
}