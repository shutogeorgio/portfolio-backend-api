package portfolio.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

}
