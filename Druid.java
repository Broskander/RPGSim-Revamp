import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Druid extends Character{
	
	// Constructor
	public Druid(String name, int health, int mana, int attack, int defense) {
	    super(name, health, mana, attack, defense);
	}
	
	public List<Move> getMoves() {
        List<Move> moves = new ArrayList<>();
        moves.add(new HealingTouch());
        moves.add(new EntanglingRoots());
        moves.add(new WrathOfNature());
        return moves;
    }
	
	// HealingTouch 
	private class HealingTouch extends Move {
        public HealingTouch() {
            super("Healing Touch", 5);
        }

        public void execute(Character attacker, Character target) {
            int heal = attacker.getAttack();
            target.setHealth(target.getHealth() + heal);
            System.out.printf("%s used %s! %s healed %d HP.\n", attacker.getName(), getName(), target.getName(), heal);
        }
    }
	
	// Entangle 
	private class EntanglingRoots extends Move {
        public EntanglingRoots() {
            super("Entangling Roots", 3);
        }

        public void execute(Character attacker, Character target) {
            Random rand = new Random();
            if (rand.nextDouble() < 0.5) {
                System.out.printf("%s used %s! But it missed.\n", attacker.getName(), getName());
            } else {
                target.setDefense(target.getDefense() / 2);
                System.out.printf("%s used %s! %s's defense was halved.\n", attacker.getName(), getName(), target.getName());
            }
        }
    }

	// Wrath of Nature
	private class WrathOfNature extends Move {
        public WrathOfNature() {
            super("Wrath of Nature", 10);
        }

        public void execute(Character attacker, Character target) {
            int damage = attacker.getAttack() * 3 - target.getDefense();
            if (damage < 0) {
                damage = 0;
            }
            target.setHealth(target.getHealth() - damage);
            System.out.printf("%s used %s! Dealt %d damage to %s.\n", attacker.getName(), getName(), damage, target.getName());
        }
    }


}
