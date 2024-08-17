package flow.assignment.file.controller;

import flow.assignment.common.BaseResponse;
import flow.assignment.file.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "FileUpload", description = "FileUpload API")
public class FileController {
    private final FileService fileService;

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    @Operation(summary = "파일 업로드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
            content = {@Content(schema = @Schema(implementation = BaseResponse.class))})
    })
    public ResponseEntity<BaseResponse<String>> fileUpload(MultipartFile file) {
        return ResponseEntity.ok().body(fileService.uploadFile(file));
    }
}
