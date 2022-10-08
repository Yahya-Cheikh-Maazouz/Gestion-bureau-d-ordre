package com.maazouz.servicegestioncourrier.Service.Impl;

import com.maazouz.servicegestioncourrier.Model.DocumentFile;
import com.maazouz.servicegestioncourrier.Repository.DocRepository;
import com.maazouz.servicegestioncourrier.Service.DocStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class DocStorageServiceImpl implements DocStorageService {

    private final DocRepository docRepository;

    public DocumentFile saveFile(MultipartFile file) {
        String docname = file.getOriginalFilename();
        try {
            DocumentFile doc = new DocumentFile(null,docname,file.getContentType(),file.getBytes());
            return docRepository.save(doc);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Optional<DocumentFile> getFile(Long fileId) {
        return docRepository.findById(fileId);
    }

    public List<DocumentFile> getFiles(){
        return docRepository.findAll();
    }
}
