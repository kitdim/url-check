package kit.org.app.dto.url;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ShowCheck {
    private Long id;
    private String statusCode;
    private String title;
    private String h1;
    private String description;
    private Instant createdAt;
}
