package kit.org.app.dto.url;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShowUrl {
    private Long id;
    private String name;
    private Date createdAt;
}
