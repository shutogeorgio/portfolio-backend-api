package portfolio.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.api.mode.Portfolio;
import portfolio.api.repository.PortfolioRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PortfolioService {

	private final PortfolioRepository portfolioRepository;

	@Autowired
	public PortfolioService(PortfolioRepository portfolioRepository) {
		this.portfolioRepository = portfolioRepository;
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
		newPortfolio.setMetaInfo(req.getMetaInfo());
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
		previousOne.setMetaInfo(req.getMetaInfo());
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
		return "Deleted";
	}

}
