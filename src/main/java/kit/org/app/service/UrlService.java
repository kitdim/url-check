package kit.org.app.service;

import kit.org.app.dto.url.*;
import kit.org.app.mapper.UrlMapper;
import kit.org.app.model.Url;
import kit.org.app.repository.UrlRepository;
import kit.org.app.dto.url.CreateUrl;
import kit.org.app.dto.url.ShowUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;

    public List<ShowIndexUrl> getAll() {
        List<Url> entity = urlRepository.findAll();
        return urlMapper.toShowIndexUrl(entity);
    }

    public ShowUrl getUrlById(Long id) {
        Url model = urlRepository.findById(id).orElseThrow(RuntimeException::new);
        return urlMapper.toShowUrl(model);
    }

    public Url save(CreateUrl dto) {
        Url model = urlMapper.toUrl(dto);
        return urlRepository.save(model);
    }

    public Url update(Long id, Url data) {
        Url model = urlRepository.findById(id).orElseThrow(RuntimeException::new);
        model.setName(data.getName());

        return urlRepository.save(model);
    }

    public void delete(Long id) {
        urlRepository.deleteById(id);
    }
}
