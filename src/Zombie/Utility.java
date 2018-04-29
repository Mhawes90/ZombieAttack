package Zombie;

public class Utility {
	protected Person people = new Person();
	private Assets supplies = new Assets();
	private Zombies zombie = new Zombies();
	private boolean end = false;
	
	// checks to see if people are living/able to live in order to continue game
	public boolean checkStatus(){		
		if((people.getLiving()> 0)&&(supplies.getFood() > 0)&&end!=true)
			end = false;
		else
			end = true;
		
		return end;
	}
	
	// gets status of the game
	public boolean getStatus(){
		return end;
	}
	
	// allows game to be instantly turned off
	public void setStatus(boolean in){
		end = in;
	}
	
	// displays current status of people
	public void displayStatus(){
		System.out.println(people.getLiving() + " people living " + supplies.getFood() + " food and " 
	+ supplies.getBullets() + " bullets left.");
	}
	
	// organizes eating and foraging for food into one step
	public void foodPhase(){
		supplies.eatFood(people.getLiving());
		supplies.forage(people.getLiving());
		supplies.getForaged();
	}
	
	// displays and does the attack phase
	public void attackUI(){
		zombie.generateZombies();
		
		System.out.println(zombie.getZombies()+ " zombies attacked!");
		
		// attack 
		attack(supplies.getBullets());
		
		System.out.println(people.getLiving() + " people living, " + zombie.killedZombies() 
		+ " zombies defeated");
	}
	
	// attack method figures out which attack to use based on bullet count
	public void attack(int bullets){
		
		// checks which attack to use based on bullets
		if (bullets > 0){
			rangedAttack();
		}
		// uses melee if no bullets
		else if (bullets <= 0){
			meleeAttack();
		}
	}
	
	// ranged attack method
	public void rangedAttack(){
		int damageCounter = 0;
		
		// loop attacks while zombies and people are alive
		while(zombie.getZombies() > 0 && people.getLiving() > 0){
			
			// damage counter keeps track of damage for each attack and 
			// changes to know if zombie should be killed or not
			damageCounter = people.gunAttack();
			
			// change battle outcome based on damage from attack
			switch (damageCounter){
			
			// critical kills zombie
			case 2: zombie.killZombie();
			supplies.useBullets();
			damageCounter = 0; break;
			
			// hit leave open for single attack from zombie
			case 1: 
			supplies.useBullets();
				if(zombie.zombieAttack()==5){
					people.killPerson();
					damageCounter = 0;
				}
				else
					damageCounter = 1;
				break;
			
				// miss means zombie gets to attack and may kill person
			case 0: supplies.useBullets();
			if (zombie.zombieAttack() == 5)
				people.killPerson(); break;
				
				// loop back around to attack current or next zombie, based on attacks used
			}
		}
	}
	
	// melee attack method
	public void meleeAttack(){
		int damageCounter = 0;
		
		// loops attack while zombies and people are alive
		while(zombie.getZombies() > 0 && people.getLiving() > 0){
			
			// damage counter keeps track of damage for each attack and 
			// changes to know if zombie should be killed or not
			damageCounter = people.meleeAttack();
			
			// change battle outcome based on attack
			switch (damageCounter){
			
			// instant kill zombie
			case 2: zombie.killZombie();
			damageCounter = 0; break;
			
			// opens space for zombie's attack
			case 1: 
				if(zombie.zombieAttack()==1){
					people.killPerson();
					damageCounter = 0;
				}
				else
					damageCounter = 1;
				break;
				
				// miss mean person dies
			case 0: people.killPerson(); break;
			
			// loop back around for next attack
			}
		}
	}
}
