package portfolio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import portfolio.api.model.Stack;
import portfolio.api.service.StackService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class StackController {

	private StackService stackService;

	@Autowired
	public StackController(StackService stackService) {
		this.stackService = stackService;
	}

	@GetMapping("/post/{portfolioId}/stack")
	public List<Stack> getAll(@PathVariable int portfolioId) {
		return this.stackService.getAll(portfolioId);
	}

	@GetMapping("/stack/{id}")
	public Stack getOne(@PathVariable int id) {
		return this.stackService.getOne(id);
	}

	@PostMapping("/post/{portfolioId}/stack")
	public Stack create(@PathVariable int portfolioId, @RequestBody Stack req) {
		return this.stackService.createOne(portfolioId, req);
	}

	@PutMapping("/stack/{id}")
	public Stack update(@PathVariable int portfolioId, @RequestBody Stack req) {
		return this.stackService.createOne(portfolioId, req);
	}

	@DeleteMapping("/stack/{id}")
	public String delete(@PathVariable int id) {
		return this.stackService.deleteOne(id);
	}

}
