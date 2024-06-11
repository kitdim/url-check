package kit.org.app.dto.url;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ShowUrl {
    private Long id;
    private String name;
    private Instant createdAt;
}
