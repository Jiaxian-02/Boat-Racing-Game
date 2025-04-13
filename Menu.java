import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Menu {
    public static int input(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input: ");
        int input = 0;
        try {
            input = scanner.nextInt();
        }
        catch (Exception ignored){}
        if (input < min || input > max) {
            System.out.println(
                    "  _____ _   ___      __     _      _____ _____    _____ _   _ _____  _    _ _______ \n" +
                            " |_   _| \\ | \\ \\    / /\\   | |    |_   _|  __ \\  |_   _| \\ | |  __ \\| |  | |__   __|\n" +
                            "   | | |  \\| |\\ \\  / /  \\  | |      | | | |  | |   | | |  \\| | |__) | |  | |  | |   \n" +
                            "   | | | . ` | \\ \\/ / /\\ \\ | |      | | | |  | |   | | | . ` |  ___/| |  | |  | |   \n" +
                            "  _| |_| |\\  |  \\  / ____ \\| |____ _| |_| |__| |  _| |_| |\\  | |    | |__| |  | |   \n" +
                            " |_____|_| \\_|   \\/_/    \\_\\______|_____|_____/  |_____|_| \\_|_|     \\____/   |_|");
            if (BoatRacing.gameInstance != null) {
                BoatRacing.gameInstance.getCurrentPlayerTurn().setTurn(BoatRacing.gameInstance.getCurrentPlayerTurn().getTurn() - 1);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {
                System.out.print("\n");
            }
        }
        return input;
    }

    public static int MainMenu() {
        System.out.println("----Boat Racing----");
        System.out.println("Press 1 to Play");
        System.out.println("Press 2 for Instruction");
        System.out.println("Press 3 for Leaderboard");
        System.out.println("Press 4 to Quit");
        System.out.println("------------------");
        return input(1,4);
    }

    public static int abilityMenu(){
        System.out.println("----Abilities----");
        System.out.println("1. Stun x" + BoatRacing.gameInstance.getCurrentPlayerTurn().getStun());
        System.out.println("2. Double Roll x" + BoatRacing.gameInstance.getCurrentPlayerTurn().getDoubleRoll());
        System.out.println("3. Go Back");
        return input(1,3);
    }
    public static int gameMenu(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("\n" + BoatRacing.gameInstance.getCurrentPlayerTurn().getName().toUpperCase() + "'s TURN " + BoatRacing.gameInstance.getCurrentPlayerTurn().getTurn() + " | Cooldown: " + BoatRacing.gameInstance.getCurrentPlayerTurn().getCooldown());
        System.out.println("====================");
        BoatRacing.gameInstance.getCurrentPlayerTurn().setTurn(BoatRacing.gameInstance.getCurrentPlayerTurn().getTurn()+1);
        System.out.println("press 1 to roll dice");
        System.out.println("press 2 for power up");
        System.out.println("press 3 to quit");
        return input(1,3);
    }
    static void instruction(){
        System.out.println("\nHow to play:");
        System.out.println("1. You will need a friend! Both of you will be racing on a boat by rolling a dice.");
        System.out.println("2. The river has currents and traps! Currents will move you forward :) and traps will move you backwards :(");
        System.out.println("3. You are given 2 power ups which are Stun and Double Roll. But you only have 3 chances to use each power up so use them wisely!");
        System.out.println("4. You can stun your opponent by using Stun and double your dice roll with Double Roll.");
        System.out.println("5. After you use a power up, there is a cooldown of 3 rounds before you can use another power up.");
        System.out.println("6. The first player to reach the end wins! Yay!");
        System.out.println("7. You can stand a chance to put your name on the leaderboard by using the shortest amount of rounds to reach the end!");
        System.out.println("8. You can stop playing by pressing the number '3' but surely that won't be necessary :>");
        System.out.println("9. Have fun !\n");
    }

    static void Leaderboard(){
        int counter = 1;
        System.out.println("\nTOP 5 PLAYER OF ALL TIME");
        System.out.println("---------------------------");
        try {
            File leaderboard = new File(System.getProperty("user.dir") + "\\src\\leaderboard.txt");
            Scanner myReader = new Scanner(leaderboard);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(counter + ". " + data);
                counter += 1;
            }
            myReader.close();
            System.out.println("\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
