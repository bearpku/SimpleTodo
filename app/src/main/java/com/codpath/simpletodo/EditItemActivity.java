package com.codpath.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText etTitle = (EditText) findViewById(R.id.etTitle);
        Intent intent = getIntent();
        etTitle.setText(intent.getStringExtra("title"));
    }

    public void onSaveClicked(View v) {
        EditText etTitle = (EditText) findViewById(R.id.etTitle);
        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);
        Item item = DBHelper.getItem(id);
        if (item != null) {
            item.title = etTitle.getText().toString();
            item.save();
        }
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
