package portfolio.api.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.api.model.Stack;

import java.util.List;

@Configuration
public interface StackRepository extends JpaRepository<Stack, Integer> {

	List<Stack> findAllByPortfolioId(Integer portfolioId);

	void deleteAllByPortfolioId(Integer portfolioId);

}
