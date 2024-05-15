package com.gdpu.thesismanagementplatform.Controller;

/**
 * @Author: CX
 * @Description:
 */
import com.gdpu.thesismanagementplatform.Service.FileStorageService;
import com.gdpu.thesismanagementplatform.pojo.Thesis;
import com.gdpu.thesismanagementplatform.pojo.UploadFileResponse;
import com.gdpu.thesismanagementplatform.repository.TeacherRepository;
import com.gdpu.thesismanagementplatform.repository.ThesisRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/thesis")
public class ThesisController {
    private final FileStorageService fileStorageService;
    @Autowired
    private ThesisRepository thesisRepository;
    @Autowired
    public ThesisController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/thesis/download/")
                .path(fileName)
                .toUriString();

        return ResponseEntity.ok(new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()));
    }

    @GetMapping("/preview/{fileName:.+}")
    public ResponseEntity<String> previewFile(@PathVariable String fileName) {
        String htmlContent = fileStorageService.loadFileAsHtml(fileName);

        return ResponseEntity.ok(htmlContent);
    }

    @GetMapping("/api/thesis/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        String uploadDir = "./src/main/resources/uploads";
        // 加载文件路径
        Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
        Resource resource = new FileSystemResource(filePath);

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
//    @GetMapping("/getTeacherId")
//    public int getTeacherId(HttpSession session) {
//        return (int) session.getAttribute("teacherId");
//    }
    @GetMapping("/getThesis")
    public List<Thesis> getThesis() {
        List<Thesis> all = thesisRepository.findAll();
//        List<Thesis> filtered = new ArrayList<>();
//        for (Thesis thesis : all) {
//            if(teacherId.equals(thesis.getTeacher().getTeacherId())){
//                filtered.add(thesis);
//            }
//        }
//        return filtered;
        return all;
    }

}