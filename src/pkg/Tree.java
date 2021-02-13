package pkg;

/**
 * Manage the tree.
 * @author Rodrigo da Silva (digodasilva@gmail.com)
 * {@link} https://github.com/digodasilva/SelecaoObjective 
 */

public class Tree {
	
	private Node root;  
    private Node currentNode;
    
    public Tree() {
    	root = new Node(null, null, null, new Dish("Lasanha", "Massa"));
    	root.setRightNode(new Node(root, null, null, new Dish("Bolo de Chocolate", null)));
    	currentNode = root;
    }
    
    /** 
     * Add a dish in a new node, created in a right branch. 
     * @param dish  
     */ 
    public void addNo(Dish dish){
		Node node = new Node();
		node.setDish(dish);
		node.setParentNode(currentNode);
		currentNode.setRightNode(node);
    } 
    
    /** 
     * Add a dish before last node. 
     * @param dish  
     */ 
    public void addPenultimate(Dish dish){
		Node node = new Node();
		node.setDish(dish);
    	node.setRightNode(currentNode);
		node.setParentNode(currentNode.getParentNode());
		
		currentNode.getParentNode().setRightNode(node);
		currentNode.setParentNode(node);
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
     * Search the next dish. The method searchNodeContainsDish method assists in the search.
     * @param dish, current dish
     * @param answer, defines direction  of the serch, yes for left or no for right.
     * @return dish or null for no child found in chosen direction (yes or no).
     */
    public Dish searchNextDish(Dish dish, String answer) {
		Node node = new Node();
    	node = searchNodeContainsDish(root, dish);
		if (node == null)
			return null;
    	return answer.equals("no") ?
    				node.getRightNode() == null ? null : node.getRightNode().getDish() :
    				node.getLeftNode() == null ? null : node.getLeftNode().getDish();
		
	}
    
    /**
     * Look for a node where the dish is, recursively.
     * @param node: current node, normally root.
     * @param dish: Dish to be found.
     * @param justNode: Defines if the method helps to search for a dish (searchNextDish) or if it only searches for the node.
     * @return node or null for no child found
     */
    
    public Node searchNodeContainsDish(Node node, Dish dish) {
    	if (root.getDish().equals(dish))
    		return root;
    	Node leftNode = node.getLeftNode() == null ? null : node.getLeftNode();
    	Node rightNode = node.getRightNode() == null ? null : node.getRightNode();
    	if (leftNode != null) {
    		if (leftNode.getDish().equals(dish)) {
				return leftNode;
    		} else {
    			return searchNodeContainsDish(leftNode, dish);
    		}
    	}
		if (rightNode != null) {
			if (rightNode.getDish().equals(dish)) {
				return rightNode;
			} else {
				return searchNodeContainsDish(rightNode, dish);
			}
		}
		return searchAtRightInParentNode(node, node.getParentNode(), dish);
	}

    /**
     * When the search ends on a leaf and the dish is not found, it helps to search within the parents, climbing the tree recursively.. 
     * @param node: current node.
     * @param parentNode: parent of the current node.
     * @param dish: Dish to be found.
     * @return node
     */
	private Node searchAtRightInParentNode(Node node, Node parentNode, Dish dish) {
    	Node rightParentNode = parentNode.getRightNode() == null ? null : parentNode.getRightNode();
		if (parentNode.getLeftNode() != null && node.equals(parentNode.getLeftNode())) {
			if (rightParentNode != null && rightParentNode.getDish().equals(dish)) {
				return rightParentNode;
			} else if (rightParentNode != null) {
				return searchNodeContainsDish(rightParentNode, dish);
			}
		}
		return searchAtRightInParentNode(parentNode, parentNode.getParentNode(), dish);
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
