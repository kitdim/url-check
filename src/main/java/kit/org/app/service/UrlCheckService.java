package kit.org.app.service;

import kit.org.app.dto.url.ShowCheck;
import kit.org.app.model.Url;
import kit.org.app.model.UrlCheck;
import kit.org.app.repository.UrlCheckRepository;
import kit.org.app.repository.UrlRepository;
import kit.org.app.dto.url.ShowCheck;
import kit.org.app.model.Url;
import kit.org.app.model.UrlCheck;
import kit.org.app.repository.UrlCheckRepository;
import kit.org.app.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import kong.unirest.*;

@Service
@RequiredArgsConstructor
public class UrlCheckService {
    private final UrlCheckRepository urlCheckRepository;
    private final UrlRepository urlRepository;

    public List<ShowCheck> getAllByUrlId(Long urlId) {
        List<UrlCheck> checks = urlCheckRepository.findUrlCheckByUrlId(urlId);
        List<ShowCheck> checksDto = new ArrayList<>();

        for (UrlCheck check : checks) {
            Long id = check.getId();
            String statusCode = check.getStatusCode();
            String title = check.getTitle();
            String h1 = check.getH1();
            String description = check.getDescription();
            Instant createdAt = check.getCreatedAt();

            ShowCheck showCheckDto = new ShowCheck();
            showCheckDto.setId(id);
            showCheckDto.setStatusCode(statusCode);
            showCheckDto.setTitle(title);
            showCheckDto.setH1(h1);
            showCheckDto.setDescription(description);
            showCheckDto.setCreatedAt(createdAt);

            checksDto.add(showCheckDto);
        }
        return checksDto;
    }

    public void save(Long id) {
        Url url = urlRepository.findById(id).orElseThrow(RuntimeException::new);
        HttpResponse<String> response = Unirest.get(url.getName()).asString();
        Document doc = Jsoup.parse(response.getBody());

        String statusCode = String.valueOf(response.getStatus());
        String title = doc.title();
        url.setStatus(statusCode);

        Element h1Temp = doc.selectFirst("h1");
        String h1 = h1Temp == null ? "" : h1Temp.text();

        Element descriptionTemp = doc.selectFirst ("meta[name=description]");
        String description = descriptionTemp == null ? "" : descriptionTemp.attr("content");

        UrlCheck urlCheck = new UrlCheck();
        urlCheck.setStatusCode(statusCode);
        urlCheck.setTitle(title);
        urlCheck.setH1(h1);
        urlCheck.setDescription(description);
        urlCheck.setUrl(url);

        urlRepository.save(url);
        urlCheckRepository.save(urlCheck);
    }
}
