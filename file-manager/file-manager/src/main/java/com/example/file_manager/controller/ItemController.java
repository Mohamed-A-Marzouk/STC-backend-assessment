package com.example.file_manager.controller;

import com.example.file_manager.entity.File;
import com.example.file_manager.entity.Item;
import com.example.file_manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Create a space (with permissions)
    @PostMapping("/spaces")
    public ResponseEntity<Item> createSpace(@RequestParam String spaceName, @RequestParam String permissionGroupName) {
        Item space = itemService.createSpace(spaceName, permissionGroupName);
        return ResponseEntity.ok(space);
    }

    // Create a folder under a space
    @PostMapping("/spaces/{spaceId}/folders")
    public ResponseEntity<Item> createFolder(@PathVariable Long spaceId, @RequestParam String folderName, @RequestParam String userEmail) {
        Item folder = itemService.createFolder(spaceId, folderName, userEmail);
        return ResponseEntity.ok(folder);
    }

    // Create a file under a folder
    @PostMapping("/folders/{folderId}/files")
    public ResponseEntity<File> createFile(@PathVariable Long folderId, @RequestParam String fileName, @RequestParam MultipartFile file, @RequestParam String userEmail) {
        try {
            byte[] binaryData = file.getBytes();
            File createdFile = itemService.createFile(folderId, fileName, binaryData, userEmail);
            return ResponseEntity.ok(createdFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // View file metadata
    @GetMapping("/files/{fileId}")
    public ResponseEntity<File> viewFileMetadata(@PathVariable Long fileId) {
        File file = itemService.getFile(fileId);
        return ResponseEntity.ok(file);
    }

    // Download a file as binary
    @GetMapping("/files/{fileId}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) {
        File file = itemService.getFile(fileId);
        return ResponseEntity.ok(file.getFileBinary());
    }
}