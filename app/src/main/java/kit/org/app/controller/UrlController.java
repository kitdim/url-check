package kit.org.app.controller;

import jakarta.validation.Valid;
import kit.org.app.dto.url.CreateUrl;
import kit.org.app.dto.url.ShowUrl;
import kit.org.app.model.Url;
import kit.org.app.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @GetMapping(path = "/urls")
    public String index(Model page) {
        List<Url> urls = urlService.getAll();
        page.addAttribute("urls", urls);

        return "urls.html";
    }

    @GetMapping(path = "/urls/{id}")
    public String show(@PathVariable Long id, Model page) {
        ShowUrl showUrl = urlService.getUrlById(id);
        page.addAttribute("url", showUrl);

        return "show.html";
    }

    @PostMapping(path = "/urls")
    public String create(@Valid CreateUrl createDto, BindingResult bindingResult, Model page) {
        if (bindingResult.hasErrors()){
            String error = bindingResult.getFieldError().getDefaultMessage();
            page.addAttribute("error", error);
            return "redirect:/";
        }
        try {
            urlService.save(createDto);
        } catch (Exception exception) {
            return "redirect:/";
        }
        return "redirect:/urls";
    }
}
