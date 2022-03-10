package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Billboard implements Serializable {
	private double width;
	private double height;
	private double area;
	private boolean inUse;
	private String brand;
	
	public Billboard(double width, double height, boolean inUse, String brand) {
		this.width = width;
		this.height = height;
		this.inUse = inUse;
		this.brand = brand;
		setArea();
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getArea() {
		return area;
	}

	public void setArea() {
		area = width * height;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
