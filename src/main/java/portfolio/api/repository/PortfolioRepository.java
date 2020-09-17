package portfolio.api.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.api.model.Portfolio;

@Configuration
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

}
