package kit.org.app.dto.url;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class ShowCheck {
    private Long id;
    private String statusCode;
    private String title;
    private String h1;
    private String description;
    private Date createdAt;
}
