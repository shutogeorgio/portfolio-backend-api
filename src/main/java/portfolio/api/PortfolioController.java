package portfolio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/")
public class PortfolioController {

    private PortfolioService portfolioService;

    @Autowired
    PortfolioController(PortfolioService portfolioService){
        this.portfolioService = portfolioService;
    }

    @GetMapping("/post")
    public ResponseEntity<List<Portfolio>> getAll() {
        return ok().contentType(MediaType.APPLICATION_JSON).body(portfolioService.getAll());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Optional<Portfolio>> getOne(@PathVariable long id){
        return ok().contentType(MediaType.APPLICATION_JSON).body(portfolioService.getOne(id));
    }

    @PostMapping("/post")
    public ResponseEntity<Portfolio> create(@RequestBody Portfolio req) {
        return ok().contentType(MediaType.APPLICATION_JSON).body(portfolioService.createOne(req));
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<Portfolio> update(@PathVariable long id, @RequestBody Portfolio req) {
        return ok().contentType(MediaType.APPLICATION_JSON).body(portfolioService.updateOne(id, req));
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<Optional<String>> delete(@PathVariable long id) {
        return ok().contentType(MediaType.APPLICATION_JSON).body(portfolioService.deleteOne(id));
    }
}
