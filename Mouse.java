import java.util.Random;

public class Mouse extends Creature {
  
  private int count = 0;
  public Mouse(int x, int y, City city, Random rand) 
  {
    super( x,  y,  city,  rand);    //inherit from the Creture class
    lab = LAB_BLUE;
    count = 0;

  } 



  @Override
  public void randomTurn() {
 
      this.count++;
      int i = rand.nextInt(3);
      int prob = rand.nextInt(9); //calculate 20% probability

      if (prob == 0 || prob ==1){
        if (i == 1){
          this.setDir((this.getDir() + 1) % 4);

        }
        if (i == 2){
          this.setDir((this.getDir() + 3) % 4);
        }
      }
  }

  @Override
  public boolean isDead() {
  
    if (this.count ==29){
      dead = true;
      return dead;
    }
    return false; 
  }
  @Override
  public void takeAction() {
  
     if (this.count == 19){
      Creature m = new Mouse(getGridPoint().x, getGridPoint().y, city, rand);
      city.creaturesToAdd.add(m);
    }
      
  }
  @Override
  public void step() {
   
   
    GridPoint p = getGridPoint();

    p.x += dirX[this.getDir()];
    p.y += dirY[this.getDir()];
 
    this.setGridPoint(p);
  }
  
      
  }

