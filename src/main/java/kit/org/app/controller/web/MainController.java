package kit.org.app.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(path = "/")
    public String index() {
        return "index.html";
    }
}
