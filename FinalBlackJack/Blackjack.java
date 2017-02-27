  //Sujan Pant 
  //Blackjackr
   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import javax.swing.border.*;
   import javax.imageio.*;
   import java.io.*;
   import java.awt.image.*;
   import java.awt.geom.*;
   import java.util.*;


    public class Blackjack extends JFrame implements ActionListener{
   
     	//The deck...
   
      private Vector<Card> deck;
      private Hand player_hand;//player hand
      private Hand dealer_hand;//dealer hand.
   	
      private HandPanel pHandPane; // player hand panel
      private HandPanel dHandPane; // dealer hand panel
		private Account playerAccount;
   
      private Button hit =new Button("Hit");
      private Button stay =new Button("Stay");
      private Button beat = new Button("Bet");
      private Button newGame=new Button("New Game");
      private JLabel amtLabel = null;
       public Blackjack(){
       //make two panel 
       //one for the card to be displayed and other for buttons and player volt  
      //setLayout(new setGridLayout(1,1));
         JPanel buttomPanel = new JPanel();
         buttomPanel.setLayout(new GridLayout(1, 5));
         buttomPanel.add(hit);
         buttomPanel.add(stay);
         buttomPanel.add(beat);
         buttomPanel.add(newGame);
      	
         hit.addActionListener(this);
         stay.addActionListener(this);
         beat.addActionListener(this);
         newGame.addActionListener(this);
      	
         amtLabel = new JLabel(" Total Amount");
         amtLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
         buttomPanel.add(amtLabel);
         buttomPanel.setPreferredSize(new Dimension(10,35));
         JPanel topDisPanel = new JPanel();
         topDisPanel.setLayout(new GridLayout(1,2));
         JPanel p2 = new JPanel();
      //create another Panel to add all the buttoms and place to display Cards
         p2.setLayout(new BorderLayout());
      
         p2.add(topDisPanel, BorderLayout.CENTER);
         p2.add(buttomPanel, BorderLayout.SOUTH);
         getContentPane().add(p2);
      	
      	// Build to panels for player and cpu hands.
         JPanel playerSide = new JPanel();
         playerSide.setLayout(new BorderLayout());
         JLabel pLabel = new JLabel("Player",SwingConstants.LEFT);
         pLabel.setPreferredSize(new Dimension(10,30));
         pLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
         playerSide.add(pLabel,BorderLayout.NORTH);
         topDisPanel.add(playerSide);
         playerSide.setBorder(new EtchedBorder(EtchedBorder.RAISED));
      	
         JPanel cpuSide = new JPanel();
         cpuSide.setLayout(new BorderLayout());
         JLabel cLabel = new JLabel("Dealer",SwingConstants.RIGHT);
         cLabel.setPreferredSize(new Dimension(10,30));
         cLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
         cpuSide.add(cLabel,BorderLayout.NORTH);
         topDisPanel.add(cpuSide);
         cpuSide.setBorder(new EtchedBorder(EtchedBorder.RAISED));
      
      //add (new JPanel b);
      
      }
       public boolean isShowHands()
      {
         boolean result = false;
      	// Code to determine if all hands are to be shown goes here.
      	
         return result;
      	
      }
       public void newGame()
      {
         player_hand = new Hand();
         dealer_hand = new Hand();
         shuffleDeck();
         initializeHands();
         if((pHandPane != null)&&(dHandPane != null))
         {
            pHandPane.repaint();
            dHandPane.repaint();
         }
      }
       public void initializeHands()
      {
      	// Give player 2 cards, then dealer 2 cards
      	
      }
       public void drawCard(Hand hand)
      {
         hand.addCard(deck.get(0));
         deck.remove(0);
      }
       public void shuffleDeck()
      {
         Vector<Integer> orderedDeck = new Vector<Integer>();
         for(int i = 0; i < 52; i++)
         {
            orderedDeck.add((Integer)i);
         }
         deck = new Vector<Card>();
         Random random = new Random();
         for(int i = 0; i < 52; i++)
         {
            int rand = (int)random.nextInt(orderedDeck.size());
            int cardNo = orderedDeck.get(rand);
            orderedDeck.remove(rand);
            Card card = new Card(cardNo%13,cardNo/13);
            deck.add(card);
         }
      }
       public Vector<Card> getDeck()
      {
         return deck;
      }
       public void actionPerformed(ActionEvent e){
         String s = e.getActionCommand();
         if(s == null)
            s = "";
         if(s.equals("Hit")) // Hit button code
         {
         
         }
         else if(s.equals("Stay")) // Stay button code
         {
         
         }
         else if(s.equals("Bet")) // Bet button code
         {
         
         }
         else if(s.equals("New Game")) // New Game button code
         {
         	
         }
      
      
      	
      }
       public Hand getPlayerHand()
      {
         return player_hand;
      }
       public Hand getDealerHand()
      {
         return dealer_hand;
      }
       public static void main(String args []){
         Blackjack  frame = new Blackjack();
         frame.setTitle("Blackjack");
         frame.setSize(800,650);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLocationRelativeTo(null);
         frame.setVisible(true);
      
      
      }
   	
   }
    class HandPanel extends JPanel
   {
      String blank = "BlankServerCard";
      String ext = ".jpg";
      String[] suit = {"Club","Heart","Diamond","Spade"};
      String[] value = {"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten",
         				   "Jack","Queen","King"};
      int[] valMap = {1,2,3,4,5,6,7,8,9,10,10,10,10,11};
   	// indices 1-9 for two through 10, 10-12 for face cards, 0 and 13 for Ace.
      boolean player = false;
      Blackjack parent = null;
   	
       public HandPanel(boolean player,Blackjack parent)
      {
         this.player = player;
         this.parent = parent;
      }
       public void paintComponent(Graphics g)
      {
         BufferedImage hand = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
      	// Now paint scaled cards to the image.
         Graphics hG = hand.getGraphics();
         if(player) // Player
         {
            Card[] cards = parent.getPlayerHand().getCards();
            if(cards.length <= 0)
               return;
            int x = 0;
            for(int i = 0; i < cards.length; i++)
            {
               try{
                  String cwd = new File("").getCanonicalPath() +"/";
                  Card curCard = cards[i];
                  String cardName = value[curCard.getValue()]+suit[curCard.getSuit()];
                  BufferedImage cardImg = ImageIO.read(new File(cwd+"CardImage/"+cardName+ext));
                  cardImg = scaleImage(cardImg);
                  if(i > 0)
                     x += (int)((2.0/3.0)*(double)cardImg.getWidth());
                  int y = 0;
                  hG.drawImage(cardImg,x,y,null);
               }
                   catch(Exception ex){}
            }
         }
         else // Dealer
         {
            Card[] cards = parent.getDealerHand().getCards();
            if(cards.length <= 0)
               return;
            int x = 0;
            boolean start = true;
            for(int i = 0; i < cards.length; i++)
            {
               try{
                  String cwd = new File("").getCanonicalPath() +"/";
                  Card curCard = cards[i];
                  String cardName = value[curCard.getValue()]+suit[curCard.getSuit()];
                  if(!parent.isShowHands())
                  {
                     cardName = blank;
                  }					
                  BufferedImage cardImg = ImageIO.read(new File(cwd+"CardImage/"+cardName+ext));
                  cardImg = scaleImage(cardImg);
                  if(start)
                     x = getWidth()-cardImg.getWidth();
                  start = false;
                  if(i > 0)
                     x -= (int)((2.0/3.0)*(double)cardImg.getWidth());
                  int y = 0;
                  hG.drawImage(cardImg,x,y,null);
               }
                   catch(Exception ex){}
            }
         }
      	
         g.drawImage(hand,0,0,null);
      }
       public BufferedImage scaleImage(BufferedImage img)
      {
         double maxHandWidth = (3.0 + 2.0/3.0)*(double)img.getWidth(); // (3 + 2/3)*card image width
         double xScale = (double)getWidth()/maxHandWidth;
         double yScale = (double)getHeight()/(double)img.getHeight();
         AffineTransform xform = AffineTransform.getScaleInstance(xScale,yScale);
         BufferedImage result = new BufferedImage((int)((double)img.getWidth()*xScale),
            						             (int)((double)img.getHeight()*yScale),BufferedImage.TYPE_INT_RGB);
         Graphics2D g2 = result.createGraphics();
         g2.drawRenderedImage(img,xform);
         return result;
      }
   	
   }
	

