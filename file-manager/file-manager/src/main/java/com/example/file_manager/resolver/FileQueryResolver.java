//package com.example.file_manager.resolver;
//
//import com.example.file_manager.entity.File;
//import com.example.file_manager.service.ItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class FileQueryResolver {
//
//    @Autowired
//    private ItemService itemService;
//
//
//    @QueryMapping
//    public File getFile(@Argument Long fileId) {
//        return itemService.getFile(fileId);
//    }
//}