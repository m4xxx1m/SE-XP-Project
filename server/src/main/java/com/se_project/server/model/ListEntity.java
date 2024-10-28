package com.se_project.server.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lists")
public class ListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ListItem> items;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ListEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<ListItem> getItems() {
        return items;
    }

    public void setItems(Set<ListItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}