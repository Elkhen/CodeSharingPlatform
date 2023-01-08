package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class WebController {

    private final CodeSharingService codeSharingService;

    @Autowired
    public WebController(CodeSharingService codeSharingService) {
        this.codeSharingService = codeSharingService;
    }

    @GetMapping(value = "/code/{UUID}", produces = "text/html")
    public String getCode(Model model, @PathVariable String UUID) {
        Code code = codeSharingService.getCode(UUID);
        model
                .addAttribute("code", code);
        return "code";
    }

    @GetMapping("/code/latest")
    public String getTenMostRecentCodes(Model model) {
        List<Code> codes = codeSharingService.getTenMostRecentUnrestrictedCodes();
        model.addAttribute("codes", codes);
        return "latestCodes";
    }

    @GetMapping("/code/new")
    public String createCode(Model model) {
        return "createCode";
    }
}
