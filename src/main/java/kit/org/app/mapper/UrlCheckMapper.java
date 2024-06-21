package kit.org.app.mapper;

import kit.org.app.dto.url.ShowCheck;
import kit.org.app.model.Url;
import kit.org.app.model.UrlCheck;
import kit.org.app.utils.UrlCheckMapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UrlCheckMapperUtil.class})
public interface UrlCheckMapper {
    ShowCheck toShowCheck(UrlCheck model);
    List<ShowCheck> toShowCheck(List<UrlCheck> entity);
    // TODO замапить метод save
//    @Mapping(target = "", qualifiedByName = {"UserMapperUtil", "toSaveUrlCheckInUrl"})
//    void makeSave(Url model);
}
