public abstract class Move {
	private String name;
    private int manaCost;

    public Move(String name, int mpCost) {
        this.name = name;
        this.manaCost = mpCost;
    }

    public String getName() {
        return name;
    }

    public int getMpCost() {
        return manaCost;
    }

    public abstract void execute(Character attacker, Character target);
}
