package kit.org.app.utils;

import kit.org.app.mapper.UrlCheckMapper;
import kit.org.app.model.Url;
import kit.org.app.model.UrlCheck;
import kit.org.app.repository.UrlCheckRepository;
import kit.org.app.repository.UrlRepository;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
@Named("UrlCheckMapperUtil")
@RequiredArgsConstructor
public class UrlCheckMapperUtil {
    private final UrlCheckRepository urlCheckRepository;
    private final UrlRepository urlRepository;

    @Named("toSaveUrlCheckInUrl")
    public void toSaveUrlCheckInUrl(Url model) {
        UrlCheck urlCheck = new UrlCheck();
        HttpResponse<String> response;
        Document doc;
        String statusCode;
        String title;

        try {
            response = Unirest.get(model.getName()).asString();
            doc = Jsoup.parse(response.getBody());

            statusCode = String.valueOf(response.getStatus());
            title = doc.title();
            model.setStatus(statusCode);

            Element h1Temp = doc.selectFirst("h1");
            String h1 = h1Temp == null ? "" : h1Temp.text();

            Element descriptionTemp = doc.selectFirst("meta[name=description]");
            String description = descriptionTemp == null ? "" : descriptionTemp.attr("content");

            urlCheck.setStatusCode(statusCode);
            urlCheck.setTitle(title);
            urlCheck.setH1(h1);
            urlCheck.setDescription(description);
            urlCheck.setUrl(model);
        }
        catch (Exception e) {
            statusCode = StatusCodeUtil.getStatusCodeByErrorMessage(e);
            title = e.getLocalizedMessage();

            model.setStatus(statusCode);
            urlCheck.setStatusCode(statusCode);
            urlCheck.setTitle(title);
            urlCheck.setUrl(model);
        } finally {
            urlCheckRepository.save(urlCheck);
            model.setLastCheck(urlCheck.getCreatedAt());
            urlRepository.save(model);
        }
    }
}
