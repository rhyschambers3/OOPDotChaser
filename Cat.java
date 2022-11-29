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
    
    //randomly turn 5% of the time
    @Override
  public void randomTurn() {
    
      this.count++;
      //check if we are within a distance of the mouse. If we are, start to chase 
      //that mouse and if not, do the random turn
      int curDist = 1000;
      for (Creature c : this.city.creatures){
        if (c instanceof Mouse && this.dist(c) <=20)
        {

          //find the x and y values of of the cat and the mouse
         GridPoint cpoint = c.getGridPoint();
         int xc = cpoint.x; //getX();
         int yc = cpoint.y;

         GridPoint catPoint = this.getGridPoint();
         int catX = catPoint.x;
         int catY = catPoint.y;
        
         //find the direction needed to set the cat
         int xDist = xc - catX;
         
         //need to get in the same x plane
         while (xDist != 0){
          if(xDist > 0){
            this.setDir(3);
            this.step();
            int newDistance = this.dist(c);
            xDist = newDistance;
          }
          if (xDist < 0){
            this.setDir(1);
            this.step();
            int newDistance = this.dist(c);
            xDist = newDistance;
          }
         }
         
         int yDist = yc - catY;
         while(yDist != 0){
          if (yDist > 0){
            this.setDir(0);
            this.step();
            int newDistance = this.dist(c);
            yDist = newDistance;
        }
        if (xDist < 0){
          this.setDir(2);
          this.step();
          int newDistance = this.dist(c);
          yDist = newDistance;
        }
         }
    }
  }
    //if it isn't within a certain distance, randomly turn
      int i = rand.nextInt(3);
      int prob = rand.nextInt(19); //find probability of 5% of the time

      if (prob ==1){
        if (i == 1){
          this.setDir((this.getDir() + 1) % 4);

        }
        if (i == 2){
          this.setDir((this.getDir() + 3) % 4);
        }
      }
  
  //find a creature within 20 points from the cat
  Creature hunted = city.catHunt(this);
  if (hunted != null)
  {
    lab = LAB_CYAN;
      //find the direction that the mouse is pointing 
      int pointTo = hunted.getDir();
      //go to that direction
      this.setDir(pointTo);
     
  }
  else{
    lab = LAB_YELLOW;
  }
}


  
  public boolean isDead() {
   
    if (this.count ==49){
      city.creaturesToAdd.add(new ZombieCats(this.getGridPoint().x,this.getGridPoint().y,this.city,this.rand));
      dead = true;
      return dead;
    }
    return false; 
  }
  @Override
  public void takeAction() {
    //if the cat catches a mouse, the mouse is removed
    for (Creature c : this.city.creatures){
      if( c instanceof Mouse && this.dist(c)==0){
        die(c);
        this.hunt = 0;
      }
      
    }

  // if (hunt == 7){
  //   //add a new dog if the cat has hunted 7 mice
  //   city.creaturesToAdd.add(new Dog(this.getGridPoint().x,this.getGridPoint().y,this.city,this.rand));
  // }
      
  }
  @Override
  public void step() {
   
    
    GridPoint p = getGridPoint();

    p.x += dirX[this.getDir()];
    p.y += 2*dirY[this.getDir()]; //double y distance
    p.x = (p.x+80)%80;
    p.y = (p.y+80)%80;
   
    this.setGridPoint(p);
  }
  
      
  
}
