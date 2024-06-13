package kit.org.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "urls")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Url {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "url no be empty")
    @URL(message = "this is not url")
    @ToString.Include
    private String name;

    private String status;

    @CreatedDate
    @Column(name = "created_date")
    @Temporal(TIMESTAMP)
    private Date createdAt;

    @Column(name = "last_check_date")
    @Temporal(TIMESTAMP)
    private Date lastCheck;

    @OneToMany(mappedBy = "url", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<UrlCheck> urlChecks;
}
