package portfolio.api.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PortfolioResponse {

	private int id;

	private String title;

	private String description;

	private String url;

	private List<Stack> stackList;

}
