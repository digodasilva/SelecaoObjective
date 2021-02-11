package model;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) { 
		Tree tree = new Tree();
        Main main = new Main();
        Dish dish = tree.getRoot().getDish();
        Dish otherDish = tree.getRoot().getRightNode().getDish(); 
        main.newRound(tree, dish, otherDish);
   }
	
	private void newRound(Tree tree, Dish dish, Dish otherDish){
		boolean option = JOptionPane.showConfirmDialog(null, "Pense em um prato que você gosta", "Jogo Gourmet", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if (option) {
			if (!shotFeature(tree, dish))
				if (shotDish(tree, otherDish, "no")) {
					JOptionPane.showMessageDialog(null, "Acertei de novo", "Jogo Gourmet", JOptionPane.INFORMATION_MESSAGE);
				}
			newRound(tree, tree.getRoot().getDish(), tree.getRoot().getRightNode().getDish());
		}
   }

	private boolean shotFeature(Tree tree, Dish dish) {
		if (dish.getFeature() == null)
			return false;
		boolean hitFeature = JOptionPane.showConfirmDialog(null, "O prato que você pensou é "+ dish.getFeature() +"?",
				"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		Dish nextDish = tree.searchNextDish(dish, hitFeature ? "yes" : "no");
		return   hitFeature ?
					nextDish == null ? shotDish(tree, dish, "yes") : shotFeature(tree, nextDish)  : 
					nextDish == null ? false : shotFeature(tree, nextDish);
	}

	private boolean shotDish(Tree tree, Dish dish, String answer) {
		boolean hitDish = JOptionPane.showConfirmDialog(null, "O prato que você pensou é "+ dish.getName() +"?",
				"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if (hitDish) {
			JOptionPane.showMessageDialog(null, "Acertei de novo", "Jogo Gourmet", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} else 
			return addDish(tree, dish, answer);
	}
	
	private boolean addDish(Tree tree, Dish dish, String answer) {
		Dish newDish = new Dish();	
		newDish.setName(
				JOptionPane.showInputDialog(null, "Qual prato você pensou?", "Desisto", JOptionPane.QUESTION_MESSAGE));
		newDish.setFeature(
				JOptionPane.showInputDialog(null,newDish.getName() + " é ______ mas " + dish.getName() + " não.", 
						"Complete", JOptionPane.QUESTION_MESSAGE));
		if (answer.equals("no"))
			tree.addNo(newDish);
		else {
			if (tree.searchNodeContainsDish(tree.getRoot(), dish) != null)
				tree.setCurrentNode(tree.searchNodeContainsDish(tree.getRoot(), dish));
			tree.addYes(newDish);
			return true;
		}
		return false;
	}

}
