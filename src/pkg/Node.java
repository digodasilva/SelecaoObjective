package pkg;

/**
 * Object to store Node attributes.
 * @author Rodrigo da Silva (digodasilva@gmail.com)
 * {@link} https://github.com/digodasilva/SelecaoObjective 
 */

public class Node {
	
	private Node parentNode;
	private Node rightNode;
	private Node leftNode;
	private Dish dish;
	
		
	public Node() {
		super();
	}
	public Node(Node parentNode, Node rightNode, Node leftNode, Dish dish) {
		super();
		this.parentNode = parentNode;
		this.rightNode = rightNode;
		this.leftNode = leftNode;
		this.dish = dish;
	}
	public Node getParentNode() {
		return parentNode;
	}
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	public Node getRightNode() {
		return rightNode;
	}
	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}
	public Node getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}

}
