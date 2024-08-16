package flow.assignment.file.controller;

import flow.assignment.common.BaseResponse;
import flow.assignment.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 관련 Controller
 * 파일 업로드
 */
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public ResponseEntity<BaseResponse<String>> fileUpload(MultipartFile file) {
        return ResponseEntity.ok().body(fileService.uploadFile(file));
    }
}
