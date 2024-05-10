package kit.org.app.dto.url;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class CreateUrl {
    @NotBlank(message = "url no be empty")
    @URL(message = "this is not url")
    private String url;
}
