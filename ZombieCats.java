import java.util.Random;
public class ZombieCats extends Creature {
    private int count = 0;
    private int hunt = 0;
    public ZombieCats(int x, int y, City city, Random rand) 
    {
      super( x,  y,  city,  rand);    //inherit from the Creture class
      lab = LAB_RED;
      count = 0;
  
    }  
    
    @Override
  public void randomTurn() {
      
      this.count++;
      //find the relative direction of the mouse and make 
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
  
  Creature hunted = city.ZombieHunt(this);
  if (hunted != null)
  {
    lab = LAB_BLACK;
    if (this.dist(hunted) <= 40)
    {
      int pointTo = hunted.getDir();
      this.setDir(pointTo);
    }
    
}else{
    lab = LAB_RED;
}

}
  
  public boolean isDead() {
   
    boolean status = false;
    if (this.count ==99){
      status = true;
    }
    return status; 
  }

  @Override
  public void takeAction() {
    //if the zombiecat finds a mouse, kill the mouse
    for (Creature c : this.city.creatures){
      if (c instanceof Mouse && this.dist(c) <= 40){
       int dirCatch = c.getDir(); //get the direction of the creature
        this.setDir(dirCatch);   //set the direction of the zombie cat so it chases the mouse
        die(c);
        this.hunt = 0;
      }
      if(c instanceof Cat && this.dist(c) <= 40){
        
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
    p.y += 3*(dirY[this.getDir()]); //triple distance of y
    //make sure new x and y are in the grid 
    p.x = (p.x+80)%80;
    p.y = (p.y+80)%80;
  

    this.setGridPoint(p);
  }
  
      
  
}

