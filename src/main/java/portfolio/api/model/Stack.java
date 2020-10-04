package portfolio.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stacks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "portfolio_id")
	private int portfolioId;

	@Column(name = "platform")
	private int platform;

	@Column(name = "name")
	private String name;

}
