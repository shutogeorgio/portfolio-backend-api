package portfolio.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import portfolio.api.model.Portfolio;
import portfolio.api.repository.PortfolioRepository;
import portfolio.api.service.PortfolioService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("ut")
public class PortfolioServiceTests {

	@MockBean
	private PortfolioService portfolioService;

	@Autowired
	private PortfolioRepository portfolioRepository;

	@BeforeEach
	public void setup() {
		this.portfolioService = new PortfolioService(portfolioRepository);
	}

	@Test
	public void getAllShouldReturnList() throws Exception {
		List<Portfolio> portfolioList = this.portfolioService.getAll();
		assertThat(portfolioList.size()).isEqualTo(2);
	}

	@Test
	public void getOneShouldReturnSingle() throws Exception {
		int id = 1;
		Portfolio portfolio = this.portfolioService.getOne(id);
		assertThat(portfolio.getTitle()).isEqualTo("Job Interview Pro");
	}

	@Test
	public void createOneShouldReturnOne() throws Exception {
		Portfolio req = new Portfolio();
		req.setTitle("Slack Auto Notification");
		req.setDescription("Cool");
		req.setUrl("blog.onejapanesedev.com");
		req.setMetaInfo("Wordpress");
		Portfolio portfolio = this.portfolioService.createOne(req);
		List<Portfolio> portfolioList = this.portfolioService.getAll();
		assertThat(portfolio).isNotNull();
		assertThat(portfolioList.size()).isEqualTo(3);
	}

	@Test
	public void updateOneShouldUpdateSingle() throws Exception {
		Integer id = 1;
		Portfolio req = new Portfolio();
		req.setTitle("Slack Auto Notification");
		req.setDescription("Cool");
		req.setUrl("blog.onejapanesedev.com");
		req.setMetaInfo("Wordpress");
		Portfolio portfolio = this.portfolioService.updateOne(id, req);
		assertThat(portfolio.getTitle()).isEqualTo(req.getTitle());
	}

	@Test
	public void deleteOneShouldReturnSome() throws Exception {
		Integer id = 1;
		String resultStr = this.portfolioService.deleteOne(id);
		assertThat(resultStr).isEqualTo("Deleted");
	}

}
