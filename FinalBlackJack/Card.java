//Sujan Pant 
import java.awt.*;

class Card {
  
  int value;
  int suit;
  
  
  public Card(int value) {
   
    this.value = value;
  }
  public Card(int value,int suit){
    this.value = value;
    this.suit = suit;
  }
  
   /* Return the current value of the card */
  public int getValue() {
    return value;
  }
  public int getSuit() {
    return suit;
  }
  
  /* Change the value of the card */
  public void setValue(int value) {
    this.value = value;
  }
} 
   
