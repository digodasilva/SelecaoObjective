package model;

import javax.swing.JOptionPane;

public class NewRound {
	
	String firstAnswer = "";
	
	public void newRound(Tree tree, Dish dish, Dish lastDish){
		boolean option = JOptionPane.showConfirmDialog(null, "Pense em um prato que você gosta", "Jogo Gourmet", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if (option) {
			Dish otherDish= shotFeature(tree, dish,lastDish);
			if (otherDish == null || otherDish.getName() == null)
				System.out.println();
			else if (otherDish.getName().equals("Lasanha"))
				shotDish(tree, otherDish, "yes");
			else if (otherDish.getName().equals("Bolo de Chocolate"))
				shotDish(tree, otherDish, "no");
			else 
				System.out.println();
			//if (shotDish(tree, otherDish, "no"))
			//	JOptionPane.showMessageDialog(null, "Acertei de novo", "Jogo Gourmet", JOptionPane.INFORMATION_MESSAGE);
			
			newRound(tree, tree.getRoot().getDish(), lastDish);
		}
   }

	private Dish shotFeature(Tree tree, Dish dish, Dish lastDish) {
		if (dish.getFeature() == null)
			return lastDish;
		boolean hitFeature = JOptionPane.showConfirmDialog(null, "O prato que você pensou é "+ dish.getFeature() +"?",
				"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if (dish.getName().equals(tree.getRoot().getDish().getName())) {
			firstAnswer = hitFeature ? "yes" : "no";
		}
		Dish nextDish = tree.searchNextDish(dish, hitFeature ? "yes" : "no");
		Dish returnDish = new Dish();
		if (hitFeature)
			returnDish = dish;
		else
			if (firstAnswer.equals("yes"))
				returnDish = tree.getRoot().getDish();
			else
				returnDish = lastDish;
		return   hitFeature ?
					nextDish == null ? returnDish : shotFeature(tree, nextDish, lastDish)  : 
					nextDish == null ? returnDish : shotFeature(tree, nextDish, lastDish);
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
