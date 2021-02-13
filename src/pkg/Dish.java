package pkg;

/**
 * Object to store dish attributes.
 * @author Rodrigo da Silva (digodasilva@gmail.com)
 * {@link} https://github.com/digodasilva/SelecaoObjective 
 */

public class Dish {
	
	private String name;
	private String feature;

	
	public Dish() {}
	
	public Dish(String name, String feature) {
		super();
		this.name = name;
		this.feature = feature;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	
	
	public String toString() {
		return "Prato [nome = " + name + ", caracteristica = " + feature + "]";
	}

}
