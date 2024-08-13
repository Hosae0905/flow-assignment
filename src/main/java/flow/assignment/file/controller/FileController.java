package flow.assignment.file.controller;

import flow.assignment.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class FileController {
    private final FileService fileService;

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public ResponseEntity<Object> fileUpload(MultipartFile file) {

        log.info("file = {}", file.getOriginalFilename());

        return ResponseEntity.ok().body(fileService.uploadFile(file));
    }
}
