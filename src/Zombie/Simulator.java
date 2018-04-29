package Zombie;

import java.util.Scanner;

public class Simulator {
	Utility zombie = new Utility();
	Scanner input = new Scanner(System.in);
	int days = 1;
	boolean quit = false;
	
	// main method, tells what to run/how for simulator
	// runs when invoked in Test.java
	public void main(){
		
		// loops daily tasks, and prompts
		dayLoop();
		
		// calls method to figure out final message to display
		endMessage(quit, days);
		
		// close scanner... no loose ends
		input.close();
	}
	
	public void dayLoop(){
		do{
			
			// does daily tasks within loop
			dailyTasks(days);
			
			// added space between days, for formatting purposes
			System.out.println();
			
			// checks to see that game can continue then asks if player wants to continue
			if (zombie.checkStatus()==false&&days<10)
				if (continuePrompt().equals("n")){
					zombie.setStatus(true);
					quit = true;
				}
			
			// moves to next day
			days++;
		}while(days <= 10 && zombie.getStatus()==false); // loop parameters
	}
	
	// method asking player if they want to continue
	public String continuePrompt(){
		// prompt
		System.out.println("Proceed to next day(y/n)?");
		String answer = input.next();
		
		// answer
		return answer;
	}
	
	// organized method of tasks to perform daily
	public void dailyTasks(int days){
		// checks status
		zombie.checkStatus();
		
		// prints current day
		System.out.println("Day: " + days);
		
		// displays total status
		zombie.displayStatus();
		
		// performs tasks involving food
		zombie.foodPhase();
		
		// performs the whole attack phase
		zombie.attackUI();
	}
	
	// method that decides final message
	public void endMessage(boolean end, int days){
		
		//if player quits display
		if (quit == true)
			quitMessage();
		
		// else display
		else 
			finalMessage(days-1);
	}
	
	// made seperate method for game over message for organization reasons
	public void finalMessage(int days){
		String message = "";
		
		// chooses message based on days survived
		switch (days){
		case 10: message = zombie.people.getLiving() + " survivors have been rescued!"; break;
		case 9: message = "So close";
		case 1: message = "Do you even survive, bro?";
		default: message = "Everyone's dead. Only lasted " + days + " days";
		}
		
		// actual output
		System.out.println(message);
	}
	
	// message to display if player quits
	public static void quitMessage(){
		System.out.println("The survivors gave in to the infection");
	}
}  
