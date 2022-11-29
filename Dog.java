// import java.util.Random;
// public class Dog extends Creature {
//     private int count;

//     public Dog(int x, int y, City cty, Random rnd){
//         super(x, y, cty, rnd);
//         lab = LAB_MAGENTA;
//         count = 0;
//     }

//     public void randomTurn() {
//         this.count++;
//         int i = rand.nextInt(3);
//         int prob = rand.nextInt(15);

//         if(prob == 0){
            
//             if (i == 1) {
//             this.setDir((this.getDir() + 1) % 4); 
//             }
    
//             if (i == 2) {
//             this.setDir((this.getDir() + 3) % 4);
//             }
//         }


//         Creature hunted = city.dogHunt(this);
//         if(hunted!=null){
    
//             int rdif = this.getGridPoint().x - hunted.getGridPoint().x;
//             int cdif = this.getGridPoint().y - hunted.getGridPoint().y;
//             if(Math.abs(rdif)>Math.abs(cdif)&& rdif>0){
//                 this.setDir(0);
//             }
//             if(Math.abs(rdif)>Math.abs(cdif)&& rdif<0){
//                 this.setDir(2);
//             }
//             if(Math.abs(cdif)>Math.abs(rdif)&& cdif>0){
//                 this.setDir(3);
//             }
//             if(Math.abs(cdif)>Math.abs(rdif)&& cdif<0){
//                 this.setDir(1);
//             }
    
//         }
        
//     }
    
//     //method where the dog hunts everything else, using method allAnimals
//     //dog can attack all animals within 10 gridpoints
//     public void takeAction() {
//         //if the cat catches a mouse, the mouse is removed
//         for (Creature c : this.city.creatures){
//           if( c instanceof Mouse && this.dist(c)==0){
//             die(c);
//             this.hunt = 0;
//           }
          
//         }

          
//       }

//     public boolean isDead() {
//         boolean status = false;
//         if(this.count == 49){
//             city.queueAddCreature(new cat(this.getGridPoint().row,this.getGridPoint().col,this.city,this.rand));
//             status = true;
//         }
//         return status;
//     }

//     public void step() {
//         this.maybeTurn();
//         GridPoint p = getGridPoint();
  
//           p.x += (3*dirX[this.getDir()]);
//           p.y += (4*dirY[this.getDir()]);
//           p.x = (p.x+80)%80;
//           p.y = (p.y+80)%80;
          
//           this.setGridPoint(p);
        
//     }
    
// }
// }

