package portfolio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {

    private PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public List<Portfolio> getAll() {
        return portfolioRepository.findAll();
    }

    public Optional<Portfolio> getOne(Long id) {
        return portfolioRepository.findById(id);
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

    public Portfolio updateOne(Long id, Portfolio req) {
        Portfolio previousOne = portfolioRepository.findById(id).orElse(null);
        if(previousOne == null) {  return new Portfolio(); }
        previousOne.setTitle(req.getTitle());
        previousOne.setDescription(req.getDescription());
        previousOne.setMetaInfo(req.getMetaInfo());
        previousOne.setUrl(req.getUrl());
        previousOne.setUpdatedAt(LocalDateTime.now());
        return portfolioRepository.saveAndFlush(previousOne);
    }

    public Optional<String> deleteOne(Long id) {
        Portfolio previousOne = portfolioRepository.findById(id).orElse(null);
        if(previousOne == null) { return Optional.of("Not found"); }
        portfolioRepository.deleteById(id);
        return Optional.of("Deleted");
    }
}
