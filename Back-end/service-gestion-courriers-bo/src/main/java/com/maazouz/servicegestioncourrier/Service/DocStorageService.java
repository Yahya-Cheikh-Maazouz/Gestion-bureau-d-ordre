package com.maazouz.servicegestioncourrier.Service;


import com.maazouz.servicegestioncourrier.Model.DocumentFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DocStorageService {
    DocumentFile saveFile(MultipartFile file) ;
    Optional<DocumentFile> getFile(Long fileId);
    List<DocumentFile> getFiles();
}
