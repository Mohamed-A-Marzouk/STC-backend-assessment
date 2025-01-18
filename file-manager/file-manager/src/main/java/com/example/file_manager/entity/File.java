package com.example.file_manager.entity;

import jakarta.persistence.*;

import java.util.Base64;

@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private byte[] fileBinary;
    
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFileBinary() {
        return fileBinary;
    }

    public void setFileBinary(byte[] fileBinary) {
        this.fileBinary = fileBinary;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getFileBinaryAsBase64() {
        return fileBinary != null ? Base64.getEncoder().encodeToString(fileBinary) : null;
    }
}