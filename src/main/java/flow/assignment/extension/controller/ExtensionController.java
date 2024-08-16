package flow.assignment.extension.controller;

import flow.assignment.extension.service.ExtensionService;
import flow.assignment.extension.model.request.DeleteCustomExtensionReq;
import flow.assignment.extension.model.request.PostAddExtensionReq;
import flow.assignment.extension.model.request.PostExtensionCheckedReq;
import flow.assignment.extension.model.request.PostExtensionUnCheckedReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/extension")
@RequiredArgsConstructor
public class ExtensionController {

    private final ExtensionService extensionService;

    @RequestMapping(method = RequestMethod.POST, value = "/checked")
    public ResponseEntity<Object> fileExtensionChecked(@RequestBody PostExtensionCheckedReq postExtensionCheckedReq) {
        return ResponseEntity.ok().body(extensionService.restrictExtension(postExtensionCheckedReq));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/unchecked")
    public ResponseEntity<Object> fileExtensionUnChecked(@RequestBody PostExtensionUnCheckedReq postExtensionUnCheckedReq) {
        return ResponseEntity.ok().body(extensionService.openExtension(postExtensionUnCheckedReq));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<Object> getExtensionList(String option) {
        return ResponseEntity.ok().body(extensionService.getDefaultExtensionList(option));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/custom/add")
    public ResponseEntity<Object> addFileExtension(@RequestBody PostAddExtensionReq postAddExtensionReq) {
        return ResponseEntity.ok().body(extensionService.addCustomExtension(postAddExtensionReq));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/custom/remove")
    public ResponseEntity<Object> removeCustomExtension(@RequestBody DeleteCustomExtensionReq deleteCustomExtensionReq) {
        return ResponseEntity.ok().body(extensionService.removeCustomExtension(deleteCustomExtensionReq));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/custom/list")
    public ResponseEntity<Object> getCustomExtensionList(String option) {
        return ResponseEntity.ok().body(extensionService.getCustomExtensionList(option));
    }
}
