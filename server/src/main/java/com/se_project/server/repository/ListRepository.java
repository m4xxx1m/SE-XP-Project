package com.se_project.server.repository;

import com.se_project.server.model.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListRepository extends JpaRepository<ListEntity, Long> {
    List<ListEntity> findByUserId(Long userId);
}