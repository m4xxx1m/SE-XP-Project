package com.se_project.server.repository;

import com.se_project.server.model.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {
    List<ListItem> findByListId(Long listId);
}