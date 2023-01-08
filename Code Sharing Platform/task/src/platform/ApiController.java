package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    CodeSharingService codeSharingService;

    @Autowired
    public ApiController(CodeSharingService codeSharingService) {
        this.codeSharingService = codeSharingService;
    }

    @GetMapping("/code/{UUID}")
    public Code getCode(@PathVariable String UUID) {
        return codeSharingService.getCode(UUID);
    }

    @GetMapping("/code/latest")
    public List<Code> getTenMostRecentCodes() {
        return codeSharingService.getTenMostRecentUnrestrictedCodes();
    }

    @PostMapping("/code/new")
    public Map<String, String> saveCode(@RequestBody Code code) {
        return codeSharingService.getSavedCodeUUID(codeSharingService.saveCode(code));
    }
}
