package portfolio.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import portfolio.api.controller.PortfolioController;
import portfolio.api.model.Portfolio;
import portfolio.api.service.PortfolioService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@WebMvcTest(controllers = PortfolioController.class)
public class PortfolioControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PortfolioService service;

	@Test
	@WithMockUser(username = "user")
	public void getAllShouldReturnOk() throws Exception {
		Portfolio p1 = new Portfolio(1, "Job Interview Pro",
				"a job interview platform for everybody",
				"https://job-interview-pro.herokuapp.com/", "Ruby, Rails",
				LocalDateTime.now(), LocalDateTime.now());

		Portfolio p2 = new Portfolio();
		p2.setId(2);
		p2.setTitle("Imagine Debater");
		p2.setDescription("a debate game makes us more productive and creative");
		p2.setUrl("https://imagine-debater.netlify.app/");
		p2.setMetaInfo("Next.js, JSX");
		p2.setCreatedAt(LocalDateTime.now());
		p2.setUpdatedAt(LocalDateTime.now());

		List<Portfolio> portfolioList = new ArrayList<>();
		portfolioList.add(p1);
		portfolioList.add(p2);

		BDDMockito.given(this.service.getAll()).willReturn(portfolioList);

		this.mockMvc.perform(get("/api/v1/post")).andExpect(status().isOk())
				.andDo(document("get-all"));
	}

	@Test
	@WithMockUser(username = "user")
	public void getOneShouldReturnOk() throws Exception {
		int id = 1;
		Portfolio p1 = new Portfolio();
		p1.setId(id);
		p1.setTitle("Job Interview Pro");
		p1.setDescription("a job interview platform for everybody");
		p1.setUrl("https://job-interview-pro.herokuapp.com/");
		p1.setMetaInfo("Ruby, Rails");
		p1.setCreatedAt(LocalDateTime.now());
		p1.setUpdatedAt(LocalDateTime.now());

		BDDMockito.given(this.service.getOne(anyInt())).willReturn(p1);

		this.mockMvc.perform(get("/api/v1/post/{id}", id)).andExpect(status().isOk())
				.andDo(document("get-one", pathParameters(
						parameterWithName("id").description("The id of post"))));
	}

	@Test
	@WithMockUser(username = "user")
	public void createShouldReturnOk() throws Exception {
		Portfolio req = new Portfolio();
		req.setTitle("Job Interview Pro");
		req.setDescription("a job interview platform for everybody");
		req.setUrl("https://job-interview-pro.herokuapp.com/");
		req.setMetaInfo("Ruby, Rails");

		Portfolio res = new Portfolio();
		res.setId(1);
		res.setTitle(req.getTitle());
		res.setDescription(req.getDescription());
		res.setUrl(req.getUrl());
		res.setMetaInfo(req.getMetaInfo());
		res.setCreatedAt(LocalDateTime.now());
		res.setUpdatedAt(LocalDateTime.now());

		ObjectMapper mapper = new ObjectMapper();

		BDDMockito.given(this.service.createOne(req)).willReturn(res);

		this.mockMvc
				.perform(post("/api/v1/post").with(csrf())
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(req)))
				.andExpect(status().isOk()).andDo(document("create-one"));
	}

	@Test
	@WithMockUser(username = "user")
	public void updateShouldReturnOk() throws Exception {
		int id = 1;
		Portfolio req = new Portfolio();
		req.setTitle("Job Interview Pro");
		req.setDescription("a job interview platform for everybody");
		req.setUrl("https://job-interview-pro.herokuapp.com/");
		req.setMetaInfo("Ruby, Rails");

		Portfolio res = new Portfolio();
		res.setId(id);
		res.setTitle(req.getTitle());
		res.setDescription(req.getDescription());
		res.setUrl(req.getUrl());
		res.setMetaInfo(req.getMetaInfo());
		res.setCreatedAt(LocalDateTime.now());
		res.setUpdatedAt(LocalDateTime.now());

		ObjectMapper mapper = new ObjectMapper();

		BDDMockito.given(this.service.updateOne(id, req)).willReturn(res);

		this.mockMvc
				.perform(put("/api/v1/post/{id}", id).with(csrf())
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(req)))
				.andExpect(status().isOk()).andDo(document("update-one", pathParameters(
						parameterWithName("id").description("The id of post"))));
	}

	@Test
	@WithMockUser(username = "user")
	public void deleteShouldReturnOk() throws Exception {
		int id = 1;
		String res = "Deleted";

		BDDMockito.given(this.service.deleteOne(anyInt())).willReturn(res);

		this.mockMvc.perform(delete("/api/v1/post/{id}", id).with(csrf()))
				.andExpect(status().isOk()).andDo(document("delete-one", pathParameters(
						parameterWithName("id").description("The id of post"))));
	}

}
