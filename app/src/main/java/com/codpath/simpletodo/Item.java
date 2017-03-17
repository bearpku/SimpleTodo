package com.codpath.simpletodo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by richard_huang on 3/16/17.
 */

@Table(name = "Items")
public class Item extends Model {
    @Column(name = "title")
    public String title;

    @Column(name = "notes")
    public String notes;

    @Column(name = "due_at")
    public Date dueAt;

    @Column(name = "priority_type")
    public PriorityType priorityType;

    @Column(name = "status")
    public Status status;

    @Column(name = "created_at")
    public Date createdAt;

    @Column(name = "updated_at")
    public Date updatedAt;

    public Item() {
        super();
    }

    public Item(String title) {
        super();
        this.title = title;
    }

}
