package kit.org.app.repository;

import kit.org.app.model.UrlCheck;
import kit.org.app.model.UrlCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlCheckRepository extends JpaRepository<UrlCheck, Long> {
    List<UrlCheck> findUrlCheckByUrlId(Long id);
}
