package portfolio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.api.model.Portfolio;
import portfolio.api.model.PortfolioResponse;
import portfolio.api.model.Stack;
import portfolio.api.service.PortfolioService;
import portfolio.api.service.StackService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class PortfolioController {

	private final PortfolioService portfolioService;

	private final StackService stackService;

	@Autowired
	PortfolioController(PortfolioService portfolioService, StackService stackService) {
		this.portfolioService = portfolioService;
		this.stackService = stackService;
	}

	@GetMapping("/post")
	public ResponseEntity<List<PortfolioResponse>> getAll() {
		List<PortfolioResponse> portfolioResponseList = new ArrayList<>();
		for (Portfolio portfolio : this.portfolioService.getAll()) {
			PortfolioResponse resEle = new PortfolioResponse();
			resEle.setId(portfolio.getId());
			resEle.setTitle(portfolio.getTitle());
			resEle.setDescription(portfolio.getDescription());
			List<Stack> stackList = this.stackService.getAll(portfolio.getId());
			resEle.setStackList(stackList);
			portfolioResponseList.add(resEle);
		}
		return ok().contentType(MediaType.APPLICATION_JSON).body(portfolioResponseList);
	}

	@GetMapping("/post/{id}")
	public ResponseEntity<PortfolioResponse> getOne(@PathVariable Integer id) {
		Portfolio portfolio = this.portfolioService.getOne(id);
		PortfolioResponse res = new PortfolioResponse(portfolio.getId(),
				portfolio.getTitle(), portfolio.getDescription(), portfolio.getUrl(),
				this.stackService.getAll(id));
		return ok().contentType(MediaType.APPLICATION_JSON).body(res);
	}

	@PostMapping("/post")
	public ResponseEntity<Portfolio> create(@RequestBody Portfolio req) {
		return ok().contentType(MediaType.APPLICATION_JSON)
				.body(portfolioService.createOne(req));
	}

	@PutMapping("/post/{id}")
	public ResponseEntity<Portfolio> update(@PathVariable Integer id,
			@RequestBody Portfolio req) {
		return ok().contentType(MediaType.APPLICATION_JSON)
				.body(portfolioService.updateOne(id, req));
	}

	@DeleteMapping("/post/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		return ok().contentType(MediaType.APPLICATION_JSON)
				.body(portfolioService.deleteOne(id));
	}

}
