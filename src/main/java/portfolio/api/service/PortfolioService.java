package portfolio.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.api.model.Portfolio;
import portfolio.api.repository.PortfolioRepository;
import portfolio.api.repository.StackRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PortfolioService {

	private final PortfolioRepository portfolioRepository;

	private final StackRepository stackRepository;

	@Autowired
	public PortfolioService(PortfolioRepository portfolioRepository,
			StackRepository stackRepository) {
		this.portfolioRepository = portfolioRepository;
		this.stackRepository = stackRepository;
	}

	public List<Portfolio> getAll() {
		return portfolioRepository.findAll();
	}

	public Portfolio getOne(Integer id) {
		return portfolioRepository.findById(id).orElse(null);
	}

	public Portfolio createOne(Portfolio req) {
		Portfolio newPortfolio = new Portfolio();
		newPortfolio.setTitle(req.getTitle());
		newPortfolio.setDescription(req.getDescription());
		newPortfolio.setUrl(req.getUrl());
		newPortfolio.setCreatedAt(LocalDateTime.now());
		newPortfolio.setUpdatedAt(LocalDateTime.now());
		return portfolioRepository.saveAndFlush(newPortfolio);
	}

	public Portfolio updateOne(Integer id, Portfolio req) {
		Portfolio previousOne = portfolioRepository.findById(id).orElse(null);
		if (previousOne == null) {
			return new Portfolio();
		}
		previousOne.setTitle(req.getTitle());
		previousOne.setDescription(req.getDescription());
		previousOne.setUrl(req.getUrl());
		previousOne.setUpdatedAt(LocalDateTime.now());
		return portfolioRepository.saveAndFlush(previousOne);
	}

	public String deleteOne(Integer id) {
		Portfolio previousOne = portfolioRepository.findById(id).orElse(null);
		if (previousOne == null) {
			return "Not found";
		}
		portfolioRepository.deleteById(id);
		stackRepository.deleteAllByPortfolioId(id);
		return "Deleted";
	}

}
