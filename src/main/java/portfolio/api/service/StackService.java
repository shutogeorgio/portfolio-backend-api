package portfolio.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.api.model.Portfolio;
import portfolio.api.model.Stack;
import portfolio.api.repository.StackRepository;

import java.util.List;

@Service
public class StackService {

	private StackRepository stackRepository;

	@Autowired
	public StackService(StackRepository stackRepository) {
		this.stackRepository = stackRepository;
	}

	public List<Stack> getAll(int portfolioId) {
		return this.stackRepository.findAllByPortfolioId(portfolioId);
	}

	public Stack getOne(int id) {
		return this.stackRepository.findById(id).orElse(null);
	}

	public Stack createOne(int portfolioId, Stack req) {
		Stack newStack = new Stack();
		newStack.setPortfolioId(portfolioId);
		newStack.setPortfolioId(req.getPlatform());
		newStack.setName(req.getName());
		return this.stackRepository.saveAndFlush(newStack);
	}

	public Stack updateOne(int id, Stack req) {
		Stack stack = this.stackRepository.findById(id).orElse(null);
		if (stack == null) {
			return null;
		}
		stack.setName(req.getName());
		stack.setPlatform(req.getPlatform());
		return this.stackRepository.saveAndFlush(stack);
	}

	public String deleteOne(int id) {
		Stack trStack = stackRepository.findById(id).orElse(null);
		if (trStack == null) {
			return "Not found";
		}
		return "Deleted";
	}

}
