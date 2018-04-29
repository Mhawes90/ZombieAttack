package Zombie;

import java.util.Random;

public class Assets {
	private int food = 80, bullets = 3750;
	private int forageCounter;
	private Random rn = new Random();
	
	public int getFood(){
		return food;
	}
	
	public void forage(int living){
		// figured a loop would be easier to show that each person forages, 
		// but can only get small amounts
		for (int i = 0; i < living; i++){
		forageCounter += rn.nextInt(3);
		}
		// add forages counter to total food
		food += forageCounter;
	}
	
	public void getForaged(){
		System.out.println(forageCounter + " pieces of food foraged");
		
		forageCounter = 0;
	}
	
	public void eatFood(int living){
		food -= living;
	}
	
	public void useBullets(){
		bullets -= 1;
	}
	
	public int getBullets(){
		return bullets;
	}
}
