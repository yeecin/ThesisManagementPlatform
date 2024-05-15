package com.gdpu.thesismanagementplatform.Service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileStorageService {
    private final String uploadDir = "./src/main/resources/uploads";


    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path targetLocation = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public String loadFileAsHtml(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            if (!Files.exists(filePath)) {
                throw new RuntimeException("File not found " + fileName);
            }

            String htmlContent;
            if (fileName.endsWith(".docx")) {
                XWPFDocument document = new XWPFDocument(Files.newInputStream(filePath));
                XWPFWordExtractor extractor = new XWPFWordExtractor(document);
                htmlContent = extractor.getText();
                extractor.close();
            } else {
                throw new RuntimeException("Unsupported file format: " + fileName);
            }

            return htmlContent;
        } catch (IOException ex) {
            throw new RuntimeException("File not found " + fileName, ex);
        }
    }
}
