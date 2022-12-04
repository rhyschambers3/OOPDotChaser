import java.util.Random;

public class Mouse extends Creature {
  
  private int count = 0;
  public Mouse(int x, int y, City city, Random rand) 
  {
    super( x,  y,  city,  rand);    //inherit from the Creture class
    lab = LAB_BLUE;
    count = 0;

  } 



  
  public void randomTurn() {
 
      this.count++;
      int prob = rand.nextInt(4); //calculate 20% probability

        if (prob ==1){

          this.setDir((rand).nextInt(3));
  
          }

        }
     
      
  


  public boolean isDead() {
  
    if (this.count ==29){
      die(this);
      dead = true;
      return dead;
    }
  
    return false; 
  }

  public void takeAction() {
    this.randomTurn();
     if (this.count == 19){
      Creature m = new Mouse(getGridPoint().x, getGridPoint().y, city, rand);
      city.creaturesToAdd.add(m);
    }
      
  }

  public void step() {
   
   
    GridPoint p = getGridPoint();

    p.x += dirX[this.getDir()];
    p.y += dirY[this.getDir()];
 
    this.setGridPoint(p);
  }
  
      
  }

