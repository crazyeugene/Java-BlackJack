import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/**
*	Version 2.0.1
*	
*		2002-03-07:
*			- Added double buffering
*			- Added pre-loading of cards, using a Mediatracker
*				(changed the Deck class to take in an array if (card) Images).
*				
**/

public class Blackjack extends Applet implements ActionListener,Runnable{

	private Deck deck;	//The deck...
	
	private Hand player_hand;//player hand
	private Hand dealer_hand;//dealer hand.

	private Button bHit=new Button("Hit");
	private Button bStay=new Button("Stay");
	private Button bRestart=new Button("New Game");


	//lets do some double buffering.
	Image offIm=null;
	Graphics offGraphics=null;
	Dimension offDimension=null;

	Image[] card_images = new Image[52];
	private boolean cards_loaded = false;
	private int current_card_loading = 0;

	String message;					//print a message...

	int width;		//The width of the applet
	int height;		//The height of the applet

	int card_width = -1;
	int card_padding = -1;	//How much should we pad the cards by..?
	
	private int games=-1;	//How many games have been played?
 
	public void init() {

		
		Thread card_loader = new Thread(this);
		card_loader.start();

	}
	
	public void newGame(){
		bHit.setEnabled(true);
		bStay.setEnabled(false);

		player_hand = new Hand();	//Create new hands...
		dealer_hand = new Hand();

		//shuffle the deck.
		deck.shuffleCards();

		games++;
	}
 
	public void hit(){

		bStay.setEnabled(true);

		player_hand.addCard(deck.dealCard());
		if(player_hand.getValue() > 21){
			message = "You loose! (score:"+player_hand.getValue()+")";
			//disable the hit and stay buttons...
			bHit.setEnabled(false);
			bStay.setEnabled(false);
			return;
		}
		message = "Score: "+player_hand.getValue();	
	}

	public void stay(){

		while(dealer_hand.getValue() < 17){
			dealer_hand.addCard(deck.dealCard());
		}
		if(dealer_hand.getValue() <= 21 && player_hand.getValue() < dealer_hand.getValue())
		{
			message = "You loose! (" + player_hand.getValue()+
					" - "+dealer_hand.getValue()+")";

		}else if (player_hand.getValue() == dealer_hand.getValue())
		{
			message = "You draw! (" + player_hand.getValue()+")";

		}else {

			message = "You win! (" + player_hand.getValue()+
					" - "+dealer_hand.getValue()+")";

		}

		bHit.setEnabled(false);
		bStay.setEnabled(false);

	}


	public void actionPerformed(ActionEvent e) {
 

		if(e.getSource() == bRestart)
		{
			newGame();
		}else if (e.getSource() == bHit)
		{
			hit();
		}else if (e.getSource() == bStay)
		{
			stay();
		}
		   
		repaint();
	}
  
	public void paint(Graphics g) {
		update(g);
	}
  
	public void update(Graphics g) {
	
		//lets sord out double buffering
		if(offGraphics==null){
			offIm=createImage(getSize().width,getSize().height);
			offGraphics=offIm.getGraphics();
		}

		if(!cards_loaded){
			//display a message saying we're loading the cards...
			offGraphics.setFont(new Font("Arial",Font.PLAIN,14));
			offGraphics.setColor(new Color(171,205,239));
			offGraphics.fillRect(0,0,getSize().width,getSize().height);
			offGraphics.setColor(Color.black);
			offGraphics.drawString("Loading cards... ",5,20);
			offGraphics.drawRect(15,40, 102 ,10);
			offGraphics.drawRect(13,38, 106 ,14);
			offGraphics.setColor(new Color(171,205,239));
			offGraphics.fillRect(0,0,150,35);
			offGraphics.setColor(Color.black);
			offGraphics.fillRect(15,40, (current_card_loading)*2 ,10);
			offGraphics.drawString("Loading card: "+current_card_loading+1,15,20);


		}else{
			Image currentCard;

			while(card_width == -1)
			{
				card_width = deck.getCard(0).getImage().getWidth(this);
			}
			
			if(card_padding == -1)
			{
				card_padding = (width - (card_width * 2) - 4) / 4;
			}

			//clear the background...
			offGraphics.setColor(Color.blue);
			offGraphics.fillRect(0,0,width,height);
			offGraphics.setColor(Color.black);
			offGraphics.fillRect(1,1,width-2,height-2);
			offGraphics.setColor(Color.white);
			offGraphics.drawString("PLAYER:",card_padding,40);
			offGraphics.drawString("DEALER:",(width/2) + card_padding,40);
			offGraphics.drawString(message,5,height - 20);
			if(games > 0)
			{
				offGraphics.drawString(games + " game(s) played...",5,height - 10);
			}
			//Draw the players hand...

			for(int i=0;i<player_hand.getCardCount();i++){
				currentCard = player_hand.getCards()[i].getImage();
				offGraphics.drawImage(currentCard, card_padding, 70+(20*(i-1)), this);
			}
			//now draw the dealers hand...
			for(int i=0;i<dealer_hand.getCardCount();i++){
				currentCard = dealer_hand.getCards()[i].getImage();
				offGraphics.drawImage(currentCard, (width/2 ) + card_padding, 70+(20*(i-1)), this);
			}

		}
		//draw buffered image.
		g.drawImage(offIm,0,0,this);
 
	}


   public void run(){

		MediaTracker t=new MediaTracker(this);
		System.out.println("Applet getting cards...");

		for(current_card_loading=0; current_card_loading < 52; current_card_loading++){

		  //try{
			card_images[current_card_loading] = getImage(getCodeBase(),
												"cards/" + (current_card_loading+1) + ".gif");

			if(card_images[current_card_loading] == null){
				System.out.println("Null card... ["+current_card_loading+"]");
			}else{
				t.addImage(card_images[current_card_loading],0);
			}
		
			try{
				t.waitForID(0);
			}catch(InterruptedException e){
				System.err.println("Interrupted waiting for images..>");
			}
			//lets show our new status.
			repaint();
		}

		//create the deck object now
		deck = new Deck(this,card_images);	//Create a new deck object.

		card_width = deck.getCard(0).getImage().getWidth(this);

		bHit.addActionListener(this);
		bStay.addActionListener(this);
		bRestart.addActionListener(this);

		bHit.setEnabled(false);
		bStay.setEnabled(false);

		width  = getSize().width;
		height = getSize().height;


		this.add(bHit);
		this.add(bStay);
		this.add(bRestart); 

		player_hand = new Hand();	//Create the hands...
		dealer_hand = new Hand();

		//make sure that the buttons appear properly.
		this.validate();

		message = "";
		cards_loaded = true;
		System.out.println("End of thread...");
		repaint();
	}



}  
  
    
      
