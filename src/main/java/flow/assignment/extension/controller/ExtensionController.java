package flow.assignment.extension.controller;

import flow.assignment.common.BaseResponse;
import flow.assignment.extension.service.ExtensionService;
import flow.assignment.extension.model.request.DeleteCustomExtensionReq;
import flow.assignment.extension.model.request.PostAddExtensionReq;
import flow.assignment.extension.model.request.PostExtensionCheckedReq;
import flow.assignment.extension.model.request.PostExtensionUnCheckedReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 확장자 관련 Controller
 * 고정 확장자 차단, 차단 해제, 목록 조회
 * 커스텀 확장자 추가, 삭제, 목록 조회
 */

@RestController
@RequestMapping("/extension")
@RequiredArgsConstructor
@Tag(name = "Extension", description = "Extension API")
public class ExtensionController {

    private final ExtensionService extensionService;

    @RequestMapping(method = RequestMethod.POST, value = "/checked")
    @Operation(summary = "파일 확장자 차단")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공",
            content = {@Content(schema = @Schema(implementation = BaseResponse.class))}),
        @ApiResponse(responseCode = "400", description = "확장자 옵션이 잘못되었습니다."),
        @ApiResponse(responseCode = "404", description = "확장자를 찾을 수 없습니다.")
    })
    public ResponseEntity<Object> fileExtensionChecked(@RequestBody PostExtensionCheckedReq postExtensionCheckedReq) {
        return ResponseEntity.ok().body(extensionService.restrictExtension(postExtensionCheckedReq));
    }

    @Operation(summary = "파일 확장자 차단 해제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "400", description = "확장자 옵션이 잘못되었습니다."),
            @ApiResponse(responseCode = "404", description = "확장자를 찾을 수 없습니다.")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/unchecked")
    public ResponseEntity<Object> fileExtensionUnChecked(@RequestBody PostExtensionUnCheckedReq postExtensionUnCheckedReq) {
        return ResponseEntity.ok().body(extensionService.openExtension(postExtensionUnCheckedReq));
    }

    @Operation(summary = "고정 확장자 목록 불러오기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "400", description = "확장자 옵션이 잘못되었습니다.")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<Object> getExtensionList(@Parameter(description = "확장자 옵션", example = "default") String option) {
        return ResponseEntity.ok().body(extensionService.getDefaultExtensionList(option));
    }

    @Operation(summary = "커스텀 확장자 추가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "400", description = "확장자 옵션이 잘못되었습니다.")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/custom/add")
    public ResponseEntity<Object> addFileExtension(@RequestBody PostAddExtensionReq postAddExtensionReq) {
        return ResponseEntity.ok().body(extensionService.addCustomExtension(postAddExtensionReq));
    }

    @Operation(summary = "커스텀 확장자 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "400", description = "확장자 옵션이 잘못되었습니다."),
            @ApiResponse(responseCode = "404", description = "확장자를 찾을 수 없습니다.")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/custom/remove")
    public ResponseEntity<Object> removeCustomExtension(@RequestBody DeleteCustomExtensionReq deleteCustomExtensionReq) {
        return ResponseEntity.ok().body(extensionService.removeCustomExtension(deleteCustomExtensionReq));
    }

    @Operation(summary = "커스텀 확장자 목록 불러오기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "400", description = "확장자 옵션이 잘못되었습니다.")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/custom/list")
    public ResponseEntity<Object> getCustomExtensionList(@Parameter(description = "확장자 옵션", example = "custom")String option) {
        return ResponseEntity.ok().body(extensionService.getCustomExtensionList(option));
    }
}
