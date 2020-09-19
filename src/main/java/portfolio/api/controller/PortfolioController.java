package portfolio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.api.service.PortfolioService;
import portfolio.api.model.Portfolio;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class PortfolioController {

	private final PortfolioService portfolioService;

	@Autowired
	PortfolioController(PortfolioService portfolioService) {
		this.portfolioService = portfolioService;
	}

	@GetMapping("/post")
	public ResponseEntity<List<Portfolio>> getAll() {
		return ok().contentType(MediaType.APPLICATION_JSON)
				.body(portfolioService.getAll());
	}

	@GetMapping("/post/{id}")
	public ResponseEntity<Portfolio> getOne(@PathVariable Integer id) {
		return ok().contentType(MediaType.APPLICATION_JSON)
				.body(portfolioService.getOne(id));
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
