package kit.org.app.service;

import kit.org.app.dto.url.*;
import kit.org.app.model.Url;
import kit.org.app.repository.UrlRepository;
import kit.org.app.dto.url.CreateUrl;
import kit.org.app.dto.url.ShowUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository repository;

    public List<ShowIndexUrl> getAll() {
        List<Url> urlsFromDb = repository.findAll();
        int countUrl = urlsFromDb.size();

        List<ShowIndexUrl> urlsShow = new ArrayList<>(countUrl);

        for (int i = 0; i < countUrl; i++) {
            Url url = urlsFromDb.get(i);
            ShowIndexUrl showIndexUrl = new ShowIndexUrl();

            Long id = url.getId();
            String name = url.getName();
            Date lastCheck = url.getLastCheck();
            String status = url.getStatus();

            showIndexUrl.setId(id);
            showIndexUrl.setName(name);
            showIndexUrl.setLastCheck(lastCheck);
            showIndexUrl.setStatus(status);

            urlsShow.add(showIndexUrl);
        }

        return urlsShow;
    }

    public ShowUrl getUrlById(Long id) {
        Url someURl = repository.findById(id).orElseThrow(RuntimeException::new);
        ShowUrl someShowUrl = new ShowUrl();

        someShowUrl.setId(someURl.getId());
        someShowUrl.setCreatedAt(someURl.getCreatedAt());
        someShowUrl.setName(someURl.getName());

        return someShowUrl;
    }

    public Url save(CreateUrl data) {
        Url url = new Url();
        url.setName(data.getUrl());

        return repository.save(url);
    }

    public Url update(Long id, Url data) {
        Url someUrl = repository.findById(id).orElseThrow(RuntimeException::new);
        someUrl.setName(data.getName());

        return repository.save(someUrl);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
