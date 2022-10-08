package com.maazouz.servicegestioncourrier.EndPoint;
import com.maazouz.servicegestioncourrier.Model.DocumentFile;
import com.maazouz.servicegestioncourrier.Service.DocStorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@AllArgsConstructor
@Transactional
@CrossOrigin
public class DocStorageController {

    private final DocStorageService docStorageService;

    @GetMapping("/")
    public String get(Model model) {
        List<DocumentFile> docs = docStorageService.getFiles();
        model.addAttribute("docs", docs);
        return "doc";
    }

    @PostMapping("/uploadFiles")
    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file: files) {
            docStorageService.saveFile(file);
        }
        return "redirect:/";
    }


    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId){
        DocumentFile doc = docStorageService.getFile(fileId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
                .body(new ByteArrayResource(doc.getData()));
    }
}





