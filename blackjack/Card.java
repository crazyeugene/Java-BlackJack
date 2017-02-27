import java.awt.*;

class Card {
  Image image;
  int value;
  
  /* Constructor */
  public Card(Image image, int value) {
    this.image = image;
    this.value = value;
  }
  
  /* Return the image associated with the card */
  public Image getImage() {
    return image;
  }
  
  /* Return the current value of the card */
  public int getValue() {
    return value;
  }
  
  /* Change the value of the card */
  public void setValue(int value) {
    this.value = value;
  }
} 
   
