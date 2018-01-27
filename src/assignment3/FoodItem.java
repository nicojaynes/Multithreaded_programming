package assignment3;

/**
 * This class represents a food item. It has a name, weight and volume
 * @author Nicolai Jaynes
 *
 */
public class FoodItem {
	private String name;
	private double weight;
	private int volume;

	public FoodItem(String name, double weight, int volume) {
		this.name = name;
		this.weight = weight;
		this.volume = volume;
	}

	public String getName() {
		return name;
	}

	public double getWeight() {
		return weight;
	}

	public int getVolume() {
		return volume;
	}

}
