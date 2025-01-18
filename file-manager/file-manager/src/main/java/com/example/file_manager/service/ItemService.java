package com.example.file_manager.service;

import com.example.file_manager.entity.File;
import com.example.file_manager.entity.Item;
import com.example.file_manager.entity.PermissionGroup;
import com.example.file_manager.entity.Permission;
import com.example.file_manager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PermissionGroupRepository permissionGroupRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private FileRepository fileRepository;

    @Transactional
    public Item createSpace(String spaceName, String permissionGroupName) {
        // Check if permission group exists
        PermissionGroup permissionGroup = permissionGroupRepository.findByGroupName(permissionGroupName);
        if (permissionGroup == null) {
            throw new RuntimeException("Permission group not found");
        }

        Item space = new Item();
        space.setName(spaceName);
        space.setType("Space");
        space.setPermissionGroup(permissionGroup);
        return itemRepository.save(space);
    }

    @Transactional
    public Item createFolder(Long spaceId, String folderName, String userEmail) {
        // Check user permission for the space
        Item space = itemRepository.findById(spaceId).orElseThrow(() -> new RuntimeException("Space not found"));
        Permission permission = permissionRepository.findByUserEmailAndPermissionGroupId(userEmail, space.getPermissionGroup().getId());
        if (permission == null || !permission.getPermissionLevel().equals("Edit")) {
            throw new RuntimeException("User does not have Edit permission on this space");
        }

        // Create the folder (Item with type "Folder")
        Item folder = new Item();
        folder.setName(folderName);
        folder.setType("Folder");
        folder.setPermissionGroup(space.getPermissionGroup());
        return itemRepository.save(folder);
    }

    @Transactional
    public File createFile(Long folderId, String fileName, byte[] binaryData, String userEmail) {
        // Check user permission for the folder
        Item folder = itemRepository.findById(folderId).orElseThrow(() -> new RuntimeException("Folder not found"));
        Permission permission = permissionRepository.findByUserEmailAndPermissionGroupId(userEmail, folder.getPermissionGroup().getId());
        if (permission == null || !permission.getPermissionLevel().equals("Edit")) {
            throw new RuntimeException("User does not have Edit permission on this folder");
        }

        // Create the file (Item with type "File")
        Item fileItem = new Item();
        fileItem.setName(fileName);
        fileItem.setType("File");
        fileItem.setPermissionGroup(folder.getPermissionGroup());
        fileItem = itemRepository.save(fileItem);

        // Create file and save its binary data
        File file = new File();
        file.setFileBinary(binaryData);
        file.setItem(fileItem);
        return fileRepository.save(file);
    }

    public File getFile(Long fileId) {
        return fileRepository.findById(fileId).orElseThrow(() -> new RuntimeException("File not found"));
    }

}
