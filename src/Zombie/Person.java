package Zombie;
import java.util.*;

public class Person {
	private Random rand = new Random();
	// use the number living as a sort of health bar
	private int living = 25;
	
	// finds total living
	public int getLiving(){
		return living;
	}
	
	// method to kill a person
	public void killPerson(){
		living -= 1;
	}
	
	// method to use if attacking with guns
	public int gunAttack(){
		int damage=0;
		
		// computes hit or miss, followed by critical
		// gave a 80% chance to hit with bullets to add some extra chance for survival
		switch (rand.nextInt(4)+1){
		
		// by defaults checks for critical
		// used default for switch because I want it to hit by default
		default: damage = gunCritical(); break;
		// case for if attack misses
		case 5: damage = 0; break;
		}
		
		// returns damage/miss
		return damage;
	}
	
	// method to check if damage w/ gun is critical
	public int gunCritical(){
		int damage;
		
		// raised critical chance to 2/3 for extra survival
		switch(rand.nextInt(3)+1){
		case 1: damage = 1; break;
		default: damage = 2; break;
		}
		
		return damage;
	}
	
	// method to use if attacking with melee
	// separate from gunAttack for organization
	public int meleeAttack(){
		int damage = 0;
		
		// computes hit or miss, followed by critical
		switch (rand.nextInt(3)+1){
		
		// checks for critical damage
		case 1: damage = meleeCritical();break;
		
		// melee misses by default
		default: damage = 0;break;
		}
		
		// return melee damage/miss
		return damage;
	}
	
	// method to check if melee damage is critical
	public int meleeCritical(){
		int damage;
		
		// calculates critical or not
		switch (rand.nextInt(10)+1){
		// case for if critical
		case 1: damage = 2; break;
		
		// default damage;
		default: damage = 1; break;
		}
		
		return damage;
	}
}
