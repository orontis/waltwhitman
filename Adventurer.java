// Collaborators: Marc and Leon

import java.util.Random;

public abstract class Adventurer{
	private int health;
	private int MAX_HP;
	private String role;
	private String name;
	private int defense = 0;
	private int damageMultiplier = 1;
	private boolean isStunned = false;
	private int stunCount = 0;

	private int specialMax;
	private int specialCount = 0;
	private String specialName;

  public Adventurer(String name, int health, int MAX_HP, int specialMax, String specialName, String role){
      this.name = name;
      this.health = health;
      this.MAX_HP = MAX_HP;
	  this.specialMax = specialMax;
	  this.specialName = specialName;
	  this.role = role;
  }
  
 //----------------------------------------------------------------------------------//
 //Information about characters
 
public abstract void printMoveset(); // prints all of the character's moves

/**Prints a character's status. It shows their current health, whether they are stunned and for how long, their defense, and the number of special resources they have*/
public void printStatus()
{
	System.out.println(role + " of name " + name + ":");
	if (health > MAX_HP * 0.5)
	{
		System.out.print("\u001b[32mGreen");
	}
	else if (health <= MAX_HP * 0.5 && health > MAX_HP * 0.25)
	{
		System.out.print("u001b[33mYellow");
	}
	else
	{
		System.out.print("uoo1b[31mRed");
	}
	System.out.print("1) Health - " + health + "/" + MAX_HP + "u001b[0m" + "\n2) Stunned - ");
	if (isStunned == true)
	{
		System.out.print(isStunned + " for " + stunCount + " turns");
	}
	else
	{
		System.out.print(isStunned);
	}
	System.out.print("\n3) Defense - " + defense + "\n4) " + specialName + " Count - " + specialCount + "/" + "specialMax");	
}

  //toString method
  public String toString(){
    return this.getName();
  }

//---------------------------------------------------------------------------------------//

	public String rest()
	{
		this.restoreSpecial(2);
		return "You rest for 1 turn and gain 2 " + specialName;
	}


//Want to add a general action(String choice) method that allows the user to choose what option they have. The user isn't allowed to choose anything if they are stunned.


/*Action Mechanics:
* 1) Resting - This costs 1 turn, but it increases your special resource by 2
* 2) Attacking - As the name suggests, you choose any of the attacks that you want to do. You can only use your special move if you have enough special.
* 3) Supporting - You can either support your allies or yourself
* All abilities can only be performed if the adventurer is not stunned.
*/
//	public abstract void action(String selection) // choices are Rest, Attack <Name>, Support, Support <Name>

  //concrete method written using abstract methods.
  //refill special resource by amount, but only up to at most getSpecialMax()
  public int restoreSpecial(int n){
      if( n > getSpecialMax() - getSpecial()){
              n = getSpecialMax() - getSpecial();
      }
      setSpecial(getSpecial()+n);
      return n;
  }

  //hurt or hinder the target adventurer
  public abstract String attack(String move, Adventurer other);

  //heall or buff the target adventurer
  public abstract String support(String move, Adventurer other);

  //heall or buff self
  public abstract String support(String move);



  //hurt or hinder the target adventurer, consume some special resource
  public abstract String specialAttack(Adventurer other);


  public void applyDamage(int amount){
	int totalDamage = amount * damageMultiplier;
	if (defense >= totalDamage)
		{
			this.defense -= totalDamage;
		}
	else if (defense < totalDamage && defense > 0)
		{
			int temp = this.defense;
			this.defense = 0;
			this.health -= totalDamage - temp; 
		}
	else
		{
			this.health = this.health - totalDamage;
		}
		
	if (health < 0)
	{
		health = 0;
	}
	
	if (damageMultiplier != 1)
	{
		damageMultiplier = 1;
	}
}

public void heal(int healing) // heals entity
	{
		this.health = this.health + healing;
		if (this.health > MAX_HP)
		{
			this.health = MAX_HP;
		}
	}

  public void defenseUp(int addDefense) // increases defense by addDefense
	{
		this.defense += addDefense;
	}
//---------------------------------------------------------------------//
  //Get Methods
  public String getName(){
    return name;
  }
  
  public String getRole()
  {
	return role;
  }

  public int getHP(){
      return health;
  }

  public int getmaxHP(){
      return MAX_HP;
  }
  
  public String getSpecialName()
  {
	return specialName;
  }
  
  public int getSpecial()
  {
	return specialCount;
  }
  
  public int getSpecialMax()
  {
	return specialMax;
  }
  
  public boolean getStunState()
  {
	return this.isStunned;
  }
  
  public int getStunCount()
  {
	return this.stunCount;
  }
  
  public int getDefense()
  {
	return this.defense;
  }
  
//-----------------------------------------------------------------------//
//Set methods
    public void setSpecial(int n)
  {
	specialCount = n; 
  }
  
  public void setName(String s){
      this.name = s;
  }
  
  public void setDamageMult(int newDM)
	{
		damageMultiplier = newDM;
	}
}
