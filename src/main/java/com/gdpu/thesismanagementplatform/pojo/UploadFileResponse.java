package com.gdpu.thesismanagementplatform.pojo;

/**
 * @Author: CX
 * @Description:
 */
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
