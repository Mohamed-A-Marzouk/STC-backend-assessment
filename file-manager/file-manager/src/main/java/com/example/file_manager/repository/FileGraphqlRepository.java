package com.example.file_manager.repository;

import com.example.file_manager.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.stereotype.Repository;

@GraphQlRepository
public interface FileGraphqlRepository extends JpaRepository<File, Long>, QueryByExampleExecutor<File> {

}