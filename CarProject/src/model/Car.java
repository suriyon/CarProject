package model;

public class Car {
	private int id;
	private String brand;
	private String model;
	private String color;
	private int price;
	public Car() {
		super();
	}
	public Car(String brand, String model, String color, int price) {
		super();
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.price = price;
	}
	public Car(int id, String brand, String model, String color, int price) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
