package model;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_id")
	private Integer foodId;

	@Column(name = "food_calcium")
	private double foodCalcium;

	@Column(name = "food_calories")
	private double foodCalories;

	@Column(name = "food_cholesterol")
	private double foodCholesterol;

	@Column(name = "food_iron")
	private double foodIron;

	@Column(name = "food_magnesium")
	private double foodMagnesium;

	@Column(name = "food_name")
	private String foodName;

	@Column(name = "food_potassium")
	private double foodPotassium;

	@Column(name = "food_price")
	private double foodPrice;

	@Column(name = "food_protein")
	private double foodProtein;

	@Column(name = "food_sodium")
	private double foodSodium;

	@Column(name = "food_total_carbohydrate")
	private double foodTotalCarbohydrate;

	@Column(name = "food_total_fat")
	private double foodTotalFat;

	@Column(name = "food_vitamina")
	private double foodVitamina;

	@Column(name = "food_vitaminb12")
	private double foodVitaminb12;

	@Column(name = "food_vitaminb6")
	private double foodVitaminb6;

	@Column(name = "food_vitaminc")
	private double foodVitaminc;

	@Column(name = "food_vitamind")
	private double foodVitamind;

	@ManyToOne
	@JoinColumn(name = "food_type_id")
	private FoodType foodType;

	public Food() {
	}

	public Integer getFoodId() {
		return this.foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public double getFoodCalcium() {
		return this.foodCalcium;
	}

	public void setFoodCalcium(double foodCalcium) {
		this.foodCalcium = foodCalcium;
	}

	public double getFoodCalories() {
		return this.foodCalories;
	}

	public void setFoodCalories(double foodCalories) {
		this.foodCalories = foodCalories;
	}

	public double getFoodCholesterol() {
		return this.foodCholesterol;
	}

	public void setFoodCholesterol(double foodCholesterol) {
		this.foodCholesterol = foodCholesterol;
	}

	public double getFoodIron() {
		return this.foodIron;
	}

	public void setFoodIron(double foodIron) {
		this.foodIron = foodIron;
	}

	public double getFoodMagnesium() {
		return this.foodMagnesium;
	}

	public void setFoodMagnesium(double foodMagnesium) {
		this.foodMagnesium = foodMagnesium;
	}

	public String getFoodName() {
		return this.foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getFoodPotassium() {
		return this.foodPotassium;
	}

	public void setFoodPotassium(double foodPotassium) {
		this.foodPotassium = foodPotassium;
	}

	public double getFoodPrice() {
		return this.foodPrice;
	}

	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}

	public double getFoodProtein() {
		return this.foodProtein;
	}

	public void setFoodProtein(double foodProtein) {
		this.foodProtein = foodProtein;
	}

	public double getFoodSodium() {
		return this.foodSodium;
	}

	public void setFoodSodium(double foodSodium) {
		this.foodSodium = foodSodium;
	}

	public double getFoodTotalCarbohydrate() {
		return this.foodTotalCarbohydrate;
	}

	public void setFoodTotalCarbohydrate(double foodTotalCarbohydrate) {
		this.foodTotalCarbohydrate = foodTotalCarbohydrate;
	}

	public double getFoodTotalFat() {
		return this.foodTotalFat;
	}

	public void setFoodTotalFat(double foodTotalFat) {
		this.foodTotalFat = foodTotalFat;
	}

	public double getFoodVitamina() {
		return this.foodVitamina;
	}

	public void setFoodVitamina(double foodVitamina) {
		this.foodVitamina = foodVitamina;
	}

	public double getFoodVitaminb12() {
		return this.foodVitaminb12;
	}

	public void setFoodVitaminb12(double foodVitaminb12) {
		this.foodVitaminb12 = foodVitaminb12;
	}

	public double getFoodVitaminb6() {
		return this.foodVitaminb6;
	}

	public void setFoodVitaminb6(double foodVitaminb6) {
		this.foodVitaminb6 = foodVitaminb6;
	}

	public double getFoodVitaminc() {
		return this.foodVitaminc;
	}

	public void setFoodVitaminc(double foodVitaminc) {
		this.foodVitaminc = foodVitaminc;
	}

	public double getFoodVitamind() {
		return this.foodVitamind;
	}

	public void setFoodVitamind(double foodVitamind) {
		this.foodVitamind = foodVitamind;
	}

	public FoodType getFoodType() {
		return this.foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

}