package kit.org.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "checks")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class UrlCheck {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String statusCode;

    private String title;

    private String h1;

    private String description;

    @CreatedDate
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private Url url;

}
