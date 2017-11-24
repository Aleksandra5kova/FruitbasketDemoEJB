package model;

import javax.persistence.*;

@Entity
@Table(name = "food_type")
@NamedQuery(name = "FoodType.findAll", query = "SELECT f FROM FoodType f")
public class FoodType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_id")
	private Integer foodId;

	@Column(name = "food_name")
	private String foodName;

	public FoodType() {
	}

	public Integer getFoodId() {
		return this.foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return this.foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

}