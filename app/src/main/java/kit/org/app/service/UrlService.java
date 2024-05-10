package kit.org.app.service;

import kit.org.app.dto.url.CreateUrl;
import kit.org.app.model.Url;
import kit.org.app.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository repository;

    public List<Url> getAll() {
        return repository.findAll();
    }

    public Url getUrlById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Url save(CreateUrl data) {
        Url url = new Url();
        url.setName(data.getUrl());
        return repository.save(url);
    }

    public Url update(Long id, Url data) {
        Url someUrl = getUrlById(id);
        someUrl.setName(data.getName());
        return repository.save(someUrl);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
