package com.codpath.simpletodo;

import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by richard_huang on 3/16/17.
 */

public class DBHelper {

    public static List<Item> getAllItems() {
        return new Select()
                .from(Item.class)
                .orderBy("due_at asc")
                .execute();
    }

    public static void deleteItem(long id) {
        Item.delete(Item.class, id);
    }

    public static Item getItem(long id) {
        List<Item> items = new Select()
                .from(Item.class)
                .where("id = ?", id)
                .execute();

        if (items.size() == 0) {
            return null;
        }

        return items.get(0);
    }

    public static Item createItem(String title) {
        Item item = new Item(title);
        item.createdAt = new Date();
        item.updatedAt = new Date();
        item.save();

        return item;
    }
}
