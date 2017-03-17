package com.codpath.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import static com.activeandroid.Cache.getContext;

/**
 * Created by richard_huang on 3/16/17.
 */

public class ItemListAdapter extends BaseAdapter {
    private List<Item> items;

    public ItemListAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void add(Item item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void remove(int i) {
        items.remove(i);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_list_row, parent, false);
        }

        Item item = (Item) getItem(position);
        if (item != null) {
            TextView tvTitle = (TextView) v.findViewById(R.id.title);
            TextView tvNotes = (TextView) v.findViewById(R.id.notes);
            TextView tvDueAt = (TextView) v.findViewById(R.id.dueAt);

            tvTitle.setText(item.title);
            tvNotes.setText(item.notes == null ? "N/A" : item.notes);
            tvDueAt.setText(item.dueAt == null ? "N/A" : item.dueAt.toString());
        }

        return v;
    }
}
