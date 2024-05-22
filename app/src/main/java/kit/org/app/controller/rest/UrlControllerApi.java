package kit.org.app.controller.rest;

import jakarta.validation.Valid;
import kit.org.app.dto.url.CreateUrl;
import kit.org.app.model.Url;
import kit.org.app.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UrlControllerApi {
    private final UrlService urlService;

    @GetMapping(value = "/urls")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Url>> index() {
        List<Url> urls = urlService.getAll();
        return ResponseEntity
                .ok()
                .header("X-Total-Count", String.valueOf(urls.size()))
                .body(urls);
    }

    @GetMapping(value = "/urls/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Url show(@PathVariable Long id) {
        return urlService.getUrlById(id);
    }

    @PostMapping(value = "/urls")
    @ResponseStatus(HttpStatus.CREATED)
    public Url create(@Valid @RequestBody CreateUrl data) {
        return urlService.save(data);
    }

    @PutMapping(value = "/urls/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Url update(@PathVariable Long id, @Valid @RequestBody Url data) {
        return urlService.update(id, data);
    }

    @DeleteMapping(value = "/urls/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        urlService.delete(id);
    }
}
