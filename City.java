import java.util.*;

public class City{


    //Determine the City Grid based on the size of the Plotter
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;

    
    // The Grid World for your reference
    //
    //        
    //       (x)
    //        0 1 2 3 4 5 ... WIDTH
    //       .----------------...
    //  (y) 0|           ,--y
    //      1|      * (3,1) 
    //      2|         ^    
    //      3|         '-x
    //      .|
    //      .|
    //      .|       
    //HEIGHT :
    //



    //-------------------------------------
    //The simulation's Data Structures
    //
    public List<Creature> creatures; //list of all creatues
    public Queue<Creature> creaturesToAdd;

    //Random instance
    private Random rand;
    
    public City(Random rand, int numMice, int numCats, int numZombieCats) {
        this.rand = rand;

        this.creatures = new LinkedList<Creature>();
        this.creaturesToAdd = new LinkedList<Creature>();
        
        /* Populate mice */
        for (int i=0; i<numMice; i++) addMouse();
        for (int i=0; i<numCats; i++) addCat();
        for (int i=0; i<numZombieCats; i++) addZombieCat();
        addNewCreatures();
      
    }


    //Return the current number of creatures in the simulation
    public int numCreatures(){
        return creatures.size();
    }
    
    public void addMouse(){
        creaturesToAdd.add(new Mouse(rand.nextInt(HEIGHT),rand.nextInt(WIDTH),this,rand));
    }
    
    public void addCat(){
        
        creaturesToAdd.add(new Cat(rand.nextInt(HEIGHT),rand.nextInt(WIDTH),this,rand));
    }
    
    public void addZombieCat(){
        creaturesToAdd.add(new ZombieCats(rand.nextInt(HEIGHT),rand.nextInt(WIDTH),this,rand));
    }


    //use this method to queue up a create to be added
    public void addNewCreatures(){
        while(!creaturesToAdd.isEmpty()){
            creatures.add(creaturesToAdd.remove());
        }
    }


    //check if the cat is within a certain distance of the mouse
    //if it is, move it to be where the mouse is
    //return the type, mouse
    public Creature catHunt(Creature c){
        Creature hunted = null;
        int dist = 100;
        for (Creature cre : creatures){
            if ((cre instanceof Mouse) && c.dist(cre) <= 20)
            {
                if(c.dist(cre) < dist){
                    hunted = cre;
                    dist = c.dist(cre);
                }
            }
           
        }
        return hunted;
    }
    public Creature ZombieHunt(Creature c){
        Creature hunted = null;
        int dist = 100;
        for(Creature cre : creatures){
            if((cre instanceof ZombieCats) && c.dist(cre)<=40){
                if(c.dist(cre)<dist){
                    hunted = cre;
                    dist = c.dist(cre);
                }
            }
        }
        return hunted;
    }
    // //method to return the mouse that is being caught by the cat
    // public Creature MouseAt(GridPoint c){
    //     ArrayList<Creature> temp = (ArrayList<Creature>)this.get(c);
    //     Creature caught = null;
    //     for(int i = 0; i<temp.size();i++){
    //         if(temp.get(i) instanceof Mouse){
    //             caught = temp.get(i);
    //             break;
    //         }
            
    //     }
    //     return caught;
    // }

    // public Creature dogHunt(GridPoint c){
    //     Creature hunted = null;
    //     int dist = 100;
    //     for(Creature cre : creatures){
    //         if((cre instanceof ZombieCats || cre instanceof Cat || cre instanceof Mouse) && c.dist(cre.getGridPoint())<=40){
    //             if(c.dist(cre.getGridPoint())<dist){
    //                 hunted = cre;
    //                 dist = c.dist(cre.getGridPoint());
    //             }
    //         }
    //     }
    //     return hunted;
    // }
    

    //You need to realize in your code such that simulate works for
    //**ALL** levels of simulkation, which means you'll need to take
    //advantage of inheritance and polymorphism.
    public void simulate() {
        //DO NOT EDIT!
        
        //You get this one for free, but you need to review this to
        //understand how to implement your various creatures

        //First, for all creatures ...
        for(Creature c : creatures){
            c.step(); 
        } //move everyone forward one step in simulation
        
        //Second, for all cratures ...
        for(Creature c : creatures){
            c.takeAction(); 
        }//take some action based on the new positions

        //Third, for all creatures ...
        LinkedList<Creature> deadCreatures = new LinkedList<Creature>();
        for(Creature c: creatures){
            if(c.isDead()) deadCreatures.add(c);
        }//find those that are dead after the action is taken

        //Four, for all creatures ...
        for(Creature c: deadCreatures){
            creatures.remove(c);
        }//remove any creatures that are dead
        
        //Five, add in any new creatures that have been added before ...
        addNewCreatures();

        //Five, for all creatures
        for(Creature c : creatures){
            System.out.println(c);
        }//print out all creatures

    }
}
