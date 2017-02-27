import java.awt.*;
import java.applet.*;
import java.net.*;
import java.io.*;

class Deck {
	Card[] cards = new Card[52];
	int currCard;

	public Deck(Blackjack myApplet,Image[] card_images) {
		// These are the (initial) values of all the cards 
		int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
			11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
			11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
			11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

		// Make all the cards, getting the images individually
		for (int i=0; i<52; i++) {
//			Image image = myApplet.getImage(myApplet.getCodeBase(), "cards/" + (i+1) + ".gif");
			cards[i] = new Card(card_images[i], values[i]);
		}

		// Shuffle the cards.
		shuffleCards();
	}

	public void shuffleCards(){

		Card temp;
		//loop through each card in the deck and swap it with some random card.
		for (int i=0; i<52; i++) {
			int rand = (int)(Math.random()*52);
			temp = cards[51-i];//take the current card...
			cards[51-i] = cards[rand];//and swap it with some random card...
			cards[rand] = temp;
		}

		//now, reset the currentCard to deal...
		currCard = 0;
	}

  
	public Card dealCard() {

		Card card = cards[currCard];
		currCard++;
		return card;
	}

	public Card getCard(int i) {
		return cards[i];
	}

} 


