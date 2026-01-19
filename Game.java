public class Game
{	
	public static void main(String[] args)
	{
		Adventurer P1 = new STEMSuperhuman("Adam");
		P1.printMoveset();
		System.out.println("\n");
		P1.applyDamage(50);
		P1.printStatus();
		
		
		Adventurer enemy = new EvilStaff("Dr. Beerman", 1);
		enemy.printMoveset();
		
	}

}