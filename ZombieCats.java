import java.util.Random;

import javax.swing.text.DefaultStyledDocument.ElementSpec;
public class ZombieCats extends Creature {
    private int count = 0;
    private int hunt = 0;
    public ZombieCats(int x, int y, City city, Random rand) 
    {
      super( x,  y,  city,  rand);    //inherit from the Creture class
      lab = LAB_RED;
      count = 0;
  
    }  
  
    void chaseMouse(Creature c){

         
          GridPoint cpoint = c.getGridPoint();
          int xc = cpoint.x; 
          int yc = cpoint.y;

          GridPoint catPoint = this.getGridPoint();
          int catX = catPoint.x;
          int catY = catPoint.y;
        
            //find the direction needed to set the cat
            int xDist = xc - catX;
            
            //need to get in the same x plane
          // System.out.println("xdistance: " + xDist);
              if(xDist > 0){
                this.setDir(3);
                this.lab= LAB_BLACK;
                // System.out.println(this.getDir());
        
              }
              if (xDist < 0){
                this.setDir(1);
                
              }
              if (xDist == 0){
                int yDist = yc - catY;
                if (yDist > 0){
                  this.setDir(0);
                  this.lab=LAB_BLACK;
                }
              if (yDist < 0){
                this.setDir(2);

            }
            }
            
    
            return;
       
          }
        
    
  
  //randomly turn 5% of the time
  public void randomTurn() {
    
      this.count++;
      //check if we are within a distance of the mouse. If we are, start to chase 
      //that mouse and if not, do the random turn
      this.lab = LAB_YELLOW;
      for (Creature c : city.creatures){
        if  (this.getGridPoint().dist(c.getGridPoint()) <= 40){
          if (c instanceof Mouse){
            // System.out.println("Mouse gridpoint:" + c.getGridPoint());
            // System.out.println("Cat gridpoint:" + this.getGridPoint());

            this.chaseMouse(c);
            return;
          }
        }
      }
  
      this.lab = LAB_YELLOW;
    //if it isn't within a certain distance, randomly turn
      
      int prob = rand.nextInt(19); //find probability of 5% of the time

      if (prob ==1 || prob == 2 ){
          this.setDir((rand).nextInt(3));

        }
     return;
      }

  

  public boolean isDead() {
   
    if (this.count ==49){
      die(this);
      dead = true;
      return dead;
    }
    if (this.hunt == 0 && this.count == 100){
      die(this);
      dead = true;
      return dead;
    }
    return false; 

  }

  public void takeAction() {

    this.randomTurn();
    //if the cat catches a mouse, the mouse is removed
    for (Creature c : city.creatures){
      if( c instanceof Mouse && this.dist(c)==0){
        
        die(c);
        this.hunt++;
      }
      
    }
 
  }

  public void step() {
   
    
    GridPoint p = getGridPoint();

    p.x += 2*dirX[this.getDir()] % 80;
    p.y += 2*dirY[this.getDir()] %80; //double y distance

   
    this.setGridPoint(p);
  }
  
      
  
}


