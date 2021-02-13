package model;

import javax.swing.JOptionPane;

public class NewRound {
	
	String answer = "";
	Dish currentDish = new Dish();
	Dish returnDish = new Dish();
	
	/**
	 * Starts a new game round.
	 * @param tree
	 * @param dish: root
	 * @param lastDish
	 */
	public void newRound(Tree tree, Dish dish, Dish lastDish){
		returnDish = tree.getRoot().getDish();
		boolean option = JOptionPane.showConfirmDialog(null, "Pense em um prato que você gosta", "Jogo Gourmet", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if (option) {
			Dish presumedDish= shotFeature(tree, dish,lastDish);
			shotDish(tree, presumedDish, lastDish);
			newRound(tree, tree.getRoot().getDish(), lastDish);
		}
	}	

	/**
	 * Try to guess the dish feature.
	 * @param tree
	 * @param dish
	 * @param lastDish
	 * @return Dish
	 */
	private Dish shotFeature(Tree tree, Dish dish, Dish lastDish) {
		currentDish = dish; 
		if (dish.getFeature() == null)
			return lastDish;
		boolean hitFeature = JOptionPane.showConfirmDialog(null, "O prato que você pensou é "+ dish.getFeature() +"?",
				"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		answer = hitFeature ? "yes" : "no";
		Dish nextDish = tree.searchNextDish(dish, answer);
		if (hitFeature) {
			returnDish = dish;
		}
		return nextDish == null ? returnDish : shotFeature(tree, nextDish, lastDish);
	}

	/**
	 * Try to guess the dish name.
	 * @param tree
	 * @param dish
	 */
	private void shotDish(Tree tree, Dish dish, Dish lastDish) {
		boolean hitDish = JOptionPane.showConfirmDialog(null, "O prato que você pensou é "+ dish.getName() +"?",
				"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if (hitDish) {
			JOptionPane.showMessageDialog(null, "Acertei de novo", "Jogo Gourmet", JOptionPane.INFORMATION_MESSAGE);
		} else 
			addDish(tree, currentDish, lastDish);
	}

	/**
	 * Add dish.
	 * @param tree
	 * @param dish: wrong shot
	 */
	private void addDish(Tree tree, Dish dish, Dish lastDish) {
		Dish newDish = new Dish();	
		newDish.setName(
				JOptionPane.showInputDialog(null, "Qual prato você pensou?", "Desisto", JOptionPane.QUESTION_MESSAGE));
		newDish.setFeature(
				JOptionPane.showInputDialog(null,newDish.getName() + " é ______ mas " + dish.getName() + " não.", 
						"Complete", JOptionPane.QUESTION_MESSAGE));
		Node searchedNode = tree.searchNodeContainsDish(tree.getRoot(), currentDish);
		tree.setCurrentNode(searchedNode != null ? searchedNode : tree.getCurrentNode());
		if (answer.equals("no"))
			if (searchedNode.getDish().getName().equals(lastDish.getName()))
				tree.addPenultimate(newDish);
			else
				tree.addNo(newDish);
		else {
			tree.addYes(newDish);
		}
	}
}
