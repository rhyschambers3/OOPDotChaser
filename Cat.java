import java.util.Random;

import javax.swing.text.DefaultStyledDocument.ElementSpec;
public class Cat extends Creature {
    private int count = 0;
    private int hunt = 0;
    public Cat(int x, int y, City city, Random rand) 
    {
      super( x,  y,  city,  rand);    //inherit from the Creture class
      lab = LAB_YELLOW;
      count = 0;
  
    }  
  
    void chaseMouseX(Creature c){

         
          GridPoint cpoint = c.getGridPoint();
          int xc = cpoint.x; 


          GridPoint catPoint = this.getGridPoint();
          int catX = catPoint.x;

        
            //find the direction needed to set the cat
            int xDist = xc - catX;
            
            //need to get in the same x plane
          // System.out.println("xdistance: " + xDist);
              if(xDist > 0){
                this.setDir(1);
                
                // System.out.println(this.getDir());
        
              }
              if (xDist < 0){
                this.setDir(3);
           
                
              }
              else{
                return;
              }
           }
            
           void stepY(Creature c){
             
                GridPoint cpoint = c.getGridPoint();
                int yc = cpoint.y;

                GridPoint catPoint = this.getGridPoint();
                int catY = catPoint.y;
                int yDist = yc - catY;
                // System.out.println("Mouse Y:" + c.getGridPoint());
                // System.out.println("Cat Y: " + this.getGridPoint());
                if (yDist > 0){
                  this.setDir(2);
   
                }
                else {
                this.setDir(0);
               

                }
            }
          
          
    
      
       
          
        
    
  
  //randomly turn 5% of the time
  public void randomTurn() {
    
      this.count++;
      //check if we are within a distance of the mouse. If we are, start to chase 
      //that mouse and if not, do the random turn
      this.lab = LAB_YELLOW;
      for (Creature c : city.creatures){
        if  (this.getGridPoint().dist(c.getGridPoint()) < 20){
          if (c instanceof Mouse){
            // System.out.println("Mouse gridpoint:" + c.getGridPoint());
            // System.out.println("Cat gridpoint:" + this.getGridPoint());
            this.lab=LAB_CYAN;
            this.chaseMouseX(c);
            this.stepY(c);
           
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
    if (count == 50 && hunt == 0)
    {
      city.creaturesToAdd.add(new ZombieCats(this.getGridPoint().x,this.getGridPoint().y,this.city,this.rand));
    }

  }

  public void step() {
   
    
    GridPoint p = getGridPoint();

    p.x += 2*dirX[this.getDir()] % 80;
    p.y += 2*dirY[this.getDir()] %80; //double y distance

   
    this.setGridPoint(p);
  }
  
      
  
}
