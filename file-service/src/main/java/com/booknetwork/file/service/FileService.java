package com.booknetwork.file.service;

import com.booknetwork.file.dto.response.FileData;
import com.booknetwork.file.dto.response.FileResponse;
import com.booknetwork.file.exception.AppException;
import com.booknetwork.file.exception.ErrorCode;
import com.booknetwork.file.mapper.FileManagementMapper;
import com.booknetwork.file.repository.FileManagementRepository;
import com.booknetwork.file.repository.FileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FileService {

    FileRepository fileRepository; // disk

    FileManagementRepository fileManagementRepository; // mongoDB
    FileManagementMapper fileManagementMapper;

    public FileResponse uploadFile(MultipartFile file) throws IOException {
        // store file
        var fileInfo = fileRepository.store(file);

        // create file management info
        var fileManagement = fileManagementMapper.toFileManagement(fileInfo);
        String userid = SecurityContextHolder.getContext().getAuthentication().getName();
        fileManagement.setOwnerId(userid);

        fileManagementRepository.save(fileManagement);

        return FileResponse.builder()
                .originalFileName(file.getOriginalFilename())
                .url(fileInfo.getUrl())
                .build();
    }

    public FileData download(String fileName) throws IOException {
        var fileManagement = fileManagementRepository.findById(fileName)
                .orElseThrow(() -> new AppException(ErrorCode.FILE_NOT_FOUND));

        var resource = fileRepository.read(fileManagement);

        return new FileData(fileManagement.getContentType(), resource);
    }

}
