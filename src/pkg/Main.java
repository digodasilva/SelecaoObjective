package pkg;

/**
 * Starts the game.
 * @author Rodrigo da Silva (digodasilva@gmail.com)
 * {@link} https://github.com/digodasilva/SelecaoObjective 
 */

public class Main {
	
	public static void main(String[] args) { 
		Tree tree = new Tree();
        Dish dish = tree.getRoot().getDish();
        Dish lastDish = tree.getRoot().getRightNode().getDish(); 
        new NewRound().newRound(tree, dish, lastDish);
   }

}
