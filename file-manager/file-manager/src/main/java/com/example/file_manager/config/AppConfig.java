package com.example.file_manager.config;

import com.example.file_manager.entity.Permission;
import com.example.file_manager.entity.PermissionGroup;
import com.example.file_manager.repository.FileRepository;
import com.example.file_manager.repository.ItemRepository;
import com.example.file_manager.repository.PermissionGroupRepository;
import com.example.file_manager.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppConfig implements CommandLineRunner {

        @Autowired
        private PermissionGroupRepository permissionGroupRepository;
        @Autowired
        private PermissionRepository permissionRepository;
        @Autowired
        private FileRepository fileRepository;
        @Autowired
        private ItemRepository itemRepository;

        @Override
        public void run(String... args) throws Exception {
                permissionRepository.deleteAll();
                fileRepository.deleteAll();
                itemRepository.deleteAll();
                permissionGroupRepository.deleteAll();

                PermissionGroup adminGroup = new PermissionGroup();
                adminGroup.setGroupName("admin");
                permissionGroupRepository.save(adminGroup);

                PermissionGroup userGroup = new PermissionGroup();
                userGroup.setGroupName("user");
                permissionGroupRepository.save(userGroup);

                Permission editPermission = new Permission();
                editPermission.setUserEmail("admin@example.com");
                editPermission.setPermissionGroup(adminGroup);
                editPermission.setPermissionLevel("Edit");
                permissionRepository.save(editPermission);

                Permission viewPermission = new Permission();
                editPermission.setUserEmail("editor@example.com");
                editPermission.setPermissionGroup(userGroup);
                editPermission.setPermissionLevel("View");
                permissionRepository.save(viewPermission);
        }

}
