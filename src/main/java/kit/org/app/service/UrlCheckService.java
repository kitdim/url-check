package kit.org.app.service;

import kit.org.app.dto.url.ShowCheck;
import kit.org.app.mapper.UrlCheckMapper;
import kit.org.app.model.Url;
import kit.org.app.model.UrlCheck;
import kit.org.app.repository.UrlCheckRepository;
import kit.org.app.repository.UrlRepository;
import kit.org.app.utils.StatusCodeUtil;
import kit.org.app.utils.UrlCheckMapperUtil;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;
import kong.unirest.*;

@Service
@RequiredArgsConstructor
public class UrlCheckService {
    private final UrlCheckRepository urlCheckRepository;
    private final UrlRepository urlRepository;
    private final UrlCheckMapper urlCheckMapper;
    private final UrlCheckMapperUtil saveUrlCheckMap;

    public List<ShowCheck> getAllByUrlId(Long urlId) {
        List<UrlCheck> entity = urlCheckRepository.findUrlCheckByUrlId(urlId);
        return urlCheckMapper.toShowCheck(entity);
    }

    public void save(Long id) {
        Url url = urlRepository.findById(id).orElseThrow(RuntimeException::new);
        saveUrlCheckMap.toSaveUrlCheckInUrl(url);
    }
}
