import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class Character {
	// Private Variables
	private String name;
    private int health;
    private int mana;
    private int attack;
    private int defense;
    
    // Character Constructor
    public Character(String name, int health, int mana, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.attack = attack;
        this.defense = defense;
    }
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public void setMana(int mana) {
        this.mana = mana;
    }
    
    public void setAttack(int attack) {
        this.attack = attack;
    }
    
    public void setDefense(int defense) {
        this.defense = defense;
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getHealth() {
        return health;
    }
    
    public int getMana() {
        return mana;
    }
    
    public int getAttack() {
        return attack;
    }
    
    public int getDefense() {
        return defense;
    }
    
    // Attack method
    public void attack(Character target) {
        int damage = this.attack - target.getDefense();
        if (damage < 0) {
            damage = 0;
        }
        target.setHealth(target.getHealth() - damage);
        System.out.println(this.name + " attacks " + target.getName() + " for " + damage + " damage!");
    }
    
    // Display method
    public void display() {
        System.out.println("Name: " + this.name);
        System.out.println("HP: " + this.health);
        System.out.println("MP: " + this.mana);
        System.out.println("AP: " + this.attack);
        System.out.println("DP: " + this.defense);
    }
    
    public abstract List<Move> getMoves();
    
    // Prompt the user to choose a move and call the appropriate method
    public void chooseMove() {
    	List<Move> moves = getMoves();
        System.out.println("Available moves:");
        for (int i = 0; i < moves.size(); i++) {
            System.out.printf("%d. %s (costs %d MP)\n", i+1, moves.get(i).getName(), moves.get(i).getMpCost());
        }
        Scanner scanner = new Scanner(System.in);
        int moveIndex = scanner.nextInt() - 1;
        if (moveIndex < 0 || moveIndex >= moves.size()) {
            System.out.println("Invalid move index!");
            return;
        }
        Move move = moves.get(moveIndex);
        if (move.getMpCost() > getMana()) {
            System.out.println("Not enough MP to use this move!");
            return;
        }
        setMana(getMana() - move.getMpCost());
        move.execute(this, null);  // pass null as the target for now
    }

	public Move getRandomMove() {
		Random rand = new Random();
	    List<Move> moves = getMoves();
	    int moveIndex = rand.nextInt(moves.size());
	    return moves.get(moveIndex);
	}

}
