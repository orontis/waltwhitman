public abstract class Role
{
	private int health;
	private final int MAX_HP;
	private String type; // name of one of the three roles
	private String entityName;
	private int defense;

	public abstract void attack();

	public static void dealDamage(int damage, Role other)
	{
		other.takeDamage(damage);
	}

	public void takeDamage(int damage)
	{
		if (defense >= damage)
		{
			this.defense -= damage;
		}
		else if (defense < damage && defense > 0)
		{
			int temp = this.defense;
			this.defense = 0;
			this.health -= damage - temp; 
		}
		else
		{
			this.health = this.health - damage;
		}
	}

	public void heal(int healing)
	{
		this.health = this.health + healing;
		if (this.health > MAX_HP)
		{
			this.health = MAX_HP;
		}
	}

	public void defenseUp(int addDefense)
	{
		this.defense += addDefense;
	}

	public abstract void printStatus();

}
