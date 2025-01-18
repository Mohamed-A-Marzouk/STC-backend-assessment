package com.example.file_manager.repository;

import com.example.file_manager.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByUserEmailAndPermissionGroupId(String userEmail, Long groupId);
}