package asb.mytodolist.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {

    public T binding;
    protected abstract T getViewBinding();

    void initBinding() {
        binding = getViewBinding();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setContentView(binding.getRoot());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        afterCreate();
    }

    protected void afterCreate() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

