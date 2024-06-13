package kit.org.app.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kit.org.app.dto.url.CreateUrl;
import kit.org.app.dto.FlashOnPage;
import kit.org.app.dto.url.ShowCheck;
import kit.org.app.dto.url.ShowUrl;
import kit.org.app.model.Url;
import kit.org.app.service.UrlCheckService;
import kit.org.app.service.UrlService;
import kit.org.app.dto.url.CreateUrl;
import kit.org.app.dto.FlashOnPage;
import kit.org.app.dto.url.ShowCheck;
import kit.org.app.dto.url.ShowUrl;
import kit.org.app.model.Url;
import kit.org.app.service.UrlCheckService;
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
    private final UrlCheckService urlCheckService;

    @GetMapping(path = "/urls")
    public String index(Model page, HttpSession session) {
        List<Url> urls = urlService.getAll();
        Object messageOnPage = session.getAttribute("flash");

        page.addAttribute("urls", urls);
        page.addAttribute("flash", messageOnPage);
        session.invalidate();

        return "urls.html";
    }

    @GetMapping(path = "/urls/{id}")
    public String show(@PathVariable Long id, Model page, HttpSession session) {
        ShowUrl showUrl = urlService.getUrlById(id);
        List<ShowCheck> showChecks = urlCheckService.getAllByUrlId(id);
        Object messageOnPage = session.getAttribute("flash");

        page.addAttribute("url", showUrl);
        page.addAttribute("checks", showChecks);
        page.addAttribute("flash", messageOnPage);
        session.invalidate();

        return "show.html";
    }

    @PostMapping(path = "/urls/{id}")
    public String check(@PathVariable Long id, HttpSession session) {
        FlashOnPage messageOnPage = new FlashOnPage();
        String type = "rounded-0 m-0 alert alert-dismissible fade show alert-success";
        String text = "Провека успешно проведена.";
        try {
            urlCheckService.save(id);
        } catch (Exception e) {
            type = "rounded-0 m-0 alert alert-dismissible fade show alert-danger";
            text = e.getLocalizedMessage();
        }
        messageOnPage.setTypeMessage(type);
        messageOnPage.setTextOfMessage(text);
        session.setAttribute("flash", messageOnPage);
        return "redirect:/urls/" + id;
    }

    @PostMapping(path = "/urls")
    public String create(@Valid CreateUrl createDto, BindingResult bindingResult,
                         Model page, HttpSession session) {
        FlashOnPage messageOnPage = new FlashOnPage();
        if (bindingResult.hasErrors() || createDto.getUrl().isEmpty()){
            String type = "rounded-0 m-0 alert alert-dismissible fade show alert-danger";
            String text = bindingResult.getFieldError().getDefaultMessage();
            messageOnPage.setTypeMessage(type);
            messageOnPage.setTextOfMessage(text);
            page.addAttribute("flash", messageOnPage);

            return "index.html";
        }
        try {
            urlService.save(createDto);
        } catch (Exception exception) {
            String type = "rounded-0 m-0 alert alert-dismissible fade show alert-primary";
            String text = "Уже было добавлено.";
            messageOnPage.setTypeMessage(type);
            messageOnPage.setTextOfMessage(text);
            page.addAttribute("flash", messageOnPage);

            return "index.html";
        }
        String type = "rounded-0 m-0 alert alert-dismissible fade show alert-success";
        String text = "Успешно добавлено.";
        messageOnPage.setTypeMessage(type);
        messageOnPage.setTextOfMessage(text);
        session.setAttribute("flash", messageOnPage);

        return "redirect:/urls";
    }
}
