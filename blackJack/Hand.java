package blackJack;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// Use Hand class to represent the cards in player's hand
public class Hand {
	protected final List<Card> cards = new ArrayList<>();
	
	public int score() {
		int score = 0;
		for (Card card : cards) {
			score += card.value();
		}
		return score;
	}
	
	public void addCards(Card[] c) {
		Collections.addAll(cards, c);
	}
	
	public int size() {
		return cards.size();
	}
}
