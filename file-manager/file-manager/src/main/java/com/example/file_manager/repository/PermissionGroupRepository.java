package com.example.file_manager.repository;

import com.example.file_manager.entity.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
    PermissionGroup findByGroupName(String groupName);
}