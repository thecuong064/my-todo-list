package asb.mytodolist.activities;

import android.content.Intent;
import android.os.Bundle;

import asb.mytodolist.base.BaseActivity;
import asb.mytodolist.constants.IntentExtras;
import asb.mytodolist.databinding.ActivityItemDetailsBinding;
import asb.mytodolist.databinding.ActivityMainBinding;

public class ItemDetailsActivity extends BaseActivity<ActivityItemDetailsBinding> {

    String itemId;

    @Override
    protected ActivityItemDetailsBinding getViewBinding() {
        return ActivityItemDetailsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void afterCreate() {
        super.afterCreate();
        initData();
        initView();
        initControlsAction();
        //setSupportActionBar(binding.myToolbar);
    }

    private void initData() {
        Intent intent = getIntent();
        String itemId = intent.getStringExtra(IntentExtras.EXTRA_ITEM_ID);
        binding.itemNameEditText.setText(itemId);
    }

    private void initView() {
    }

    private void initControlsAction() {

        binding.finishBtn.setOnClickListener(view -> {
            setResult(RESULT_OK);
            finish();
        });

        binding.cancelBtn.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

}