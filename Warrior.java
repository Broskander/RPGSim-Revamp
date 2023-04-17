import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Character {
	
	// Constructor
    public Warrior(String name, int health, int mana, int attack, int defense) {
        super(name, health, mana, attack, defense);
    }
    
    public List<Move> getMoves() {
        List<Move> moves = new ArrayList<>();
        moves.add(new Slash());
        moves.add(new Guard());
        moves.add(new Guillotine());
        return moves;
    }
    
    // Slash 
    private class Slash extends Move {
        public Slash() {
            super("Slash", 0);
        }

        public void execute(Character attacker, Character target) {
            int damage = attacker.getAttack() - target.getDefense();
            if (damage < 0) {
                damage = 0;
            }
            target.setHealth(target.getHealth() - damage);
            System.out.printf("%s used %s! Dealt %d damage to %s.\n", attacker.getName(), getName(), damage, target.getName());
        }
    }
    
    // Guard 
    private class Guard extends Move {
        public Guard() {
            super("Guard", 5);
        }

        public void execute(Character attacker, Character target) {
            int oldDefense = attacker.getDefense();
            int newDefense = oldDefense * 2;
            attacker.setDefense(newDefense);
            System.out.printf("%s used %s! Defense increased from %d to %d.\n", attacker.getName(), getName(), oldDefense, newDefense);
        }
    }
    
    // Guillotine
    private class Guillotine extends Move {
        public Guillotine() {
            super("Guillotine", 10);
        }

        public void execute(Character attacker, Character target) {
            Random random = new Random();
            if (random.nextInt(10) == 0) {
                target.setHealth(0);
                System.out.printf("%s used %s and executed %s in one swift strike!\n", attacker.getName(), getName(), target.getName());
            } else {
                System.out.printf("%s used %s but missed %s.\n", attacker.getName(), getName(), target.getName());
            }
        }
    }

}
