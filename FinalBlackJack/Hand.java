
class Hand {
	private Card cards[] = new Card[10];
	private int count;
	private int value;

	
	public void Hand(){
		count=0;
		value=0;
	}
	
	public Card[] getCards(){

		return cards;

	}

	public int getCardCount(){
		return count;
	}

	public void addCard(Card c) {
		cards[count] = c;
		value += cards[count].getValue();

		if(c.getValue() == 0 && value > 21)
		{	//got an ace, so decrement the value of the hand by 10, if it 
			//would have been above 21...
			//value-=10;
			
			//Find number of aces, and the total value of the non-aces
			int nonAceVal = 0;
			int aces = 0;
			for(int i = 0; i <= count; i++)
			{
			//	if(cards[i] == 0)
					//aces++;
		}	
					


		}
		count++;
	}
	  
	public int getValue() {
		return value;
	}
  
	public boolean ace() {
		int aceIndex = 0;
		boolean result = false;

		for (int i=0; i<count; i++) {
			if (cards[i].getValue() == 11) {
				value-=10;
				return true;
			}
		}
		return false;
	}  

}

    
    
  
