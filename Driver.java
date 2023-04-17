import java.util.*;

public class Driver {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an array of characters to choose from
        Character[] characters = {new Warrior("Warrior", 100, 50, 20, 10),
                                   new Mage("Mage", 70, 100, 10, 5),
                                   new Druid("Druid", 80, 70, 15, 8)};

        // Print out the list of characters for the user to choose from
        System.out.println("Choose a character to play as:");
        for (int i = 0; i < characters.length; i++) {
            System.out.printf("%d. %s\n", i + 1, characters[i].getName());
        }

        // Get the user's character choice
        int characterChoice = scanner.nextInt();
        while (characterChoice < 1 || characterChoice > characters.length) {
            System.out.println("Invalid choice. Choose a character to play as:");
            characterChoice = scanner.nextInt();
        }
        Character player = characters[characterChoice - 1];

        // Generate a randomized enemy
        Character enemy = generateRandomEnemy();

        // Print out the battle info
        System.out.printf("%s vs. %s\n\n", player.getName(), enemy.getName());

        // Battle loop
        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            // Player's turn
            System.out.printf("%s's turn. Choose a move:\n", player.getName());
            List<Move> moves = player.getMoves();
            for (int i = 0; i < moves.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, moves.get(i).getName());
            }
            int moveChoice = scanner.nextInt();
            while (moveChoice < 1 || moveChoice > moves.size()) {
                System.out.println("Invalid choice. Choose a move:");
                moveChoice = scanner.nextInt();
            }
            Move playerMove = moves.get(moveChoice - 1);
            playerMove.execute(player, enemy);
            if (enemy.getHealth() <= 0) {
                break;
            }

            // Enemy's turn
            System.out.printf("%s's turn.\n", enemy.getName());
            Move enemyMove = enemy.getRandomMove();
            enemyMove.execute(enemy, player);
            System.out.println();
        }

        // Print out the battle result
        if (player.getHealth() <= 0) {
            System.out.printf("%s has been defeated by %s.\n", player.getName(), enemy.getName());
        } else {
            System.out.printf("%s has defeated %s.\n", player.getName(), enemy.getName());
        }
    }

    private static Character generateRandomEnemy() {
        Random rand = new Random();
        int enemyType = rand.nextInt(3);
        switch (enemyType) {
            case 0:
                return new Warrior("Orc", 80, 40, 15, 8);
            case 1:
                return new Mage("Dark Wizard", 50, 80, 8, 3);
            case 2:
                return new Druid("Ent", 70, 60, 10, 5);
            default:
                return null;
        }
    }

}
