public class EvilStaff extends Adventurer
{
	// first row - basic attacks, last element always special/ultimate move | second row - support
	private String[][] activeMoveset = {{"[CS] Chemical Splash","[U] Uranium"},{"[MC] Mystery Concoction","[AT] Apple Totem"}};
	private String[][] lunchLady_MS =
	private String[][] teacher_MS = 
	private int healCounter = 2; // decreases by 1 whenever Mystery Concoction is used
	private String[][] movesetDescription = {{"BASIC ATTACK - 20% chance of dealing 15 damage to yourself; 80% change of dealing 15 damage to enemy","SPECIAL ATTACK - 25% chance of dealing 100 damage to enemy; 25% chance of healing 20 HP; 50% chance of instant death"},{"HEAL (" + healCounter + "uses left) - 30% chance of dealing 10 damage to you; 10% chance of killing you instantly; 60% chance of healing 20 HP","DEFENSE - 60% chance of protecting against all damage; 20% chance of doing nothing; 20% chance of doubling all damage taken for 1 turn"}};
	private int IQ = 0; // this is the special resource | max is 5, which is also the cost of performing the special
	private int enemyState = 0; // 0 - mindcontrolled teacher | 1 - Lunch Lady
	private int floor = 10;
	
	public void setMoveSet(String[][] newMS)
	{
		this.moveset = newMS;
	}
	
	public refreshState() // checks enemyState to determine whether moveset should be swapped
	
	
	
	
	
}
