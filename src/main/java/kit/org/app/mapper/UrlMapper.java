package kit.org.app.mapper;

import kit.org.app.dto.url.CreateUrl;
import kit.org.app.dto.url.ShowIndexUrl;
import kit.org.app.dto.url.ShowUrl;
import kit.org.app.model.Url;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UrlMapper {
    @Mapping(target = "name", source = "url")
    Url toUrl(CreateUrl dto);
    ShowUrl toShowUrl(Url model);
    ShowIndexUrl toShowIndexUrl(Url model);
    List<ShowIndexUrl> toShowIndexUrl(List<Url> entity);
}
