
import java.util.ArrayList;
import java.util.List;

public class Mage extends Character {
	
	// Constructor
	public Mage(String name, int health, int mana, int attack, int defense) {
	    super(name, health, mana, attack, defense);
	}
	
	public List<Move> getMoves() {
        List<Move> moves = new ArrayList<>();
        moves.add(new Fireball());
        moves.add(new IceShard());
        moves.add(new ManaBurn());
        return moves;
    }
	
	// Fireball 
	private class Fireball extends Move {
        public Fireball() {
            super("Fireball", 5);
        }

        public void execute(Character attacker, Character target) {
            int damage = attacker.getAttack() * 2 - target.getDefense();
            if (damage < 0) {
                damage = 0;
            }
            target.setHealth(target.getHealth() - damage);
            System.out.printf("%s used %s! Dealt %d damage to %s.\n", attacker.getName(), getName(), damage, target.getName());
        }
    }
	
	// Ice Shield ]
	private class IceShard extends Move {
        public IceShard() {
            super("Ice Shard", 3);
        }

        public void execute(Character attacker, Character target) {
            int damage = attacker.getAttack() - target.getDefense();
            if (damage < 0) {
                damage = 0;
            }
            target.setHealth(target.getHealth() - damage);
            System.out.printf("%s used %s! Dealt %d damage to %s.\n", attacker.getName(), getName(), damage, target.getName());
            target.setMana(target.getMana() - 5);
            System.out.printf("%s's MP was decreased by 5.\n", target.getName());
        }
    }
	
	// Heal
	private class ManaBurn extends Move {
        public ManaBurn() {
            super("Mana Burn", 10);
        }

        public void execute(Character attacker, Character target) {
            int mpLost = target.getMana() / 2;
            target.setMana(target.getMana() - mpLost);
            System.out.printf("%s used %s! %s lost %d MP.\n", attacker.getName(), getName(), target.getName(), mpLost);
        }
    }

}
