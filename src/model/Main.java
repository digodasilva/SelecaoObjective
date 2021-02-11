package model;

public class Main {
	
	public static void main(String[] args) { 
		Tree tree = new Tree();
        Dish dish = tree.getRoot().getDish();
        Dish lastDish = tree.getRoot().getRightNode().getDish(); 
        new NewRound().newRound(tree, dish, lastDish);
   }

}
