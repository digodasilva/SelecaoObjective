package model;

public class Tree {
	
	private Node root;  
    private Node currentNode;
    
    public Tree() {
    	root = new Node(null, new Node(root, null, null, new Dish("Bolo de Chocolate", null)), null, new Dish("Lasanha", "Massa"));
    	currentNode = root;
    }
    
    /** 
     * Add a dish in a new node, created in a right branch. 
     * @param dish  
     */ 
    public void addNo(Dish dish){
		Node node = new Node();
		if (currentNode.getDish() == root.getDish()) {
			currentNode = new Node(root, null, null, new Dish("Bolo de Chocolate", null));
		}
		node.setDish(dish);
		node.setRightNode(currentNode);
		node.setParentNode(currentNode.getParentNode());
		currentNode.getParentNode().setRightNode(node);
		currentNode.setParentNode(node);
		currentNode = root.getRightNode();
    } 
    
    /** 
     * Add a dish in a new node, created in a left branch. 
     * @param dish  
     */ 
    public void addYes(Dish dish){
    	Node node = new Node();
		node.setDish(dish);
		node.setParentNode(currentNode);
		currentNode.setLeftNode(node);
    } 
    
   
    /**
     * Search a next dish.
     * @param dish
     * @return dish or null for empty
     */
    public Dish searchNextDish(Dish dish, String answer) {
		Node node = new Node();
    	if (root.getDish().equals(dish))
    		node = root;
    	else 
    		node = searchNodeContainsDish(root, dish);
		if (node == null)
			return null;
    	return answer.equals("no") ?
    				node.getRightNode() == null ? null : node.getRightNode().getDish() :
    				node.getLeftNode() == null ? null : node.getLeftNode().getDish();
		
	}
    
    public Node searchNodeContainsDish(Node node, Dish dish) {
    	Node leftNode = node.getLeftNode() == null ? null : node.getLeftNode();
    	Node rightNode = node.getRightNode() == null ? null : node.getRightNode();
    	if (leftNode != null && leftNode.getDish().equals(dish)) {
			return leftNode;
		} else if (leftNode != null)
			return searchNodeContainsDish(leftNode, dish);
		if (rightNode != null && rightNode.getDish().equals(dish)) {
			return rightNode;
		} else if (rightNode != null)
			return searchNodeContainsDish(rightNode, dish);
		return null;
	}

	/** 
	* Get and set current Node. 
	*/  
	public Node getCurrentNode(){ 
		return this.currentNode; 
	}
	
	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}
	
	/** 
	* Get root Node. 
	*/ 
	public Node getRoot() {
		return root;
	}

}
