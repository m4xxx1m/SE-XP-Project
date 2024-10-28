package com.se_project.server.model;

import javax.persistence.*;

@Entity
@Table(name = "list_items")
public class ListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private ListEntity list;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ListItem parentItem;

    public ListItem() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ListEntity getList() {
        return list;
    }

    public void setList(ListEntity list) {
        this.list = list;
    }

    public ListItem getParentItem() {
        return parentItem;
    }

    public void setParentItem(ListItem parentItem) {
        this.parentItem = parentItem;
    }
}