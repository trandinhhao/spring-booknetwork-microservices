package com.booknetwork.file.repository;

import com.booknetwork.file.entity.FileManagement;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FileManagementRepository extends MongoRepository<FileManagement, String> {
}
