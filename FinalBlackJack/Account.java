//Sujan Pant 
//Creat Account Class 
public class Account{
	private int initialAc = 500;
	public int getInitialAc(){
		return initialAc;
	
	}
	//Return current Account meaning if the player wins we 
	//add but if the player looses then we let the betamount be negative
	public int currentAc(int betAm){
		return initialAc+betAm;
	
	}
	

}