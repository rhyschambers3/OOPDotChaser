import java.util.Random;

public class Mouse extends Creature{

  public Mouse(int x, int y, City city, Random rand) 
  {
    super( x,  y,  city,  rand);    //inherit from the Creture class
    lab = LAB_BLUE;
  } 

  public void takeAction(){

    if (Simulator.rounds == 20){ //produce new baby after 20 rounds simulation
      this.clone(); 
    }
    if (Simulator.count == 30) //mouse dies after 30 rounds
    {
      dead = true;
    }
    if (count/round == .2) //mouse randomly turns 20% of time
    {
      this.randomTurn();
    }
    
  }
}
