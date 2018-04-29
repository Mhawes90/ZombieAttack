package Zombie;
import java.util.*;

// seperate zombie class for organization
public class Zombies {
	private int zombies, deathCount;
	private Random rn = new Random();
	
	// generates random number of zombies, and resets death counter
	public void generateZombies(){
		zombies = rn.nextInt(30)+10;
		deathCount = 0;
	}
	
	// gets current number of zombies
	public int getZombies(){
		return zombies;
	}
	
	// kills off a zombie, and counts number killed
	public void killZombie(){
		zombies -= 1;
		deathCount += 1;
	}
	
	// returns random chance for zombie to hit
	// figured it would look better in this class then taking up space in utility class
	public int zombieAttack(){
		// lowered chance for zombie to hit, to add some extra survivability for the survivors
		int damage = rn.nextInt(5)+1;
		return damage;
	}
	
	// returns number of killed zombies
	public int killedZombies(){
		return deathCount;
	}

}
