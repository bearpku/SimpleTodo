package com.codpath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import static com.codpath.simpletodo.DBHelper.getAllItems;

public class MainActivity extends AppCompatActivity {
    ItemListAdapter itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lvItems);

        List<Item> items = getAllItems();
        itemsAdapter = new ItemListAdapter(items);
        lvItems.setAdapter(itemsAdapter);

        setupListViewListener();
    }

    public void setupListViewListener() {
        lvItems.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapter,
                                        View view,
                                        int pos,
                                        long id) {
                    Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                    Item item = (Item) itemsAdapter.getItem(pos);
                    intent.putExtra("title", item.title);
                    intent.putExtra("id", item.getId());
                    startActivity(intent);
                }
            }
        );
        lvItems.setOnItemLongClickListener(
            new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapter,
                                               View view,
                                               int pos,
                                               long id) {
                    Item item = (Item) itemsAdapter.getItem(pos);
                    itemsAdapter.remove(pos);
                    DBHelper.deleteItem(item.getId());
                    return true;
                }
            }
        );
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        etNewItem.setText("");
        Item item = DBHelper.createItem(itemText);
        itemsAdapter.add(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        itemsAdapter.notifyDataSetChanged();
    }
}
