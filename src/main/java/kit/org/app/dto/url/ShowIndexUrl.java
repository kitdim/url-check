package kit.org.app.dto.url;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShowIndexUrl {
    private Long id;
    private String name;
    private Date lastCheck;
    private String status;
}
