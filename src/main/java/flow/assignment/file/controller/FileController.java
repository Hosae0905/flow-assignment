package flow.assignment.file.controller;

import flow.assignment.file.model.request.PostExtensionCheckedReq;
import flow.assignment.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(method = RequestMethod.POST, value = "/extension/checked")
    public ResponseEntity<Object> fileExtensionChecked(@RequestBody PostExtensionCheckedReq postExtensionCheckedReq) {
        log.info("extension = {}", postExtensionCheckedReq.getExtension());

        return ResponseEntity.ok().body(fileService.restrictExtension(postExtensionCheckedReq));
    }
}
