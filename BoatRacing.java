import java.util.Scanner;

public class BoatRacing {
    public static Game gameInstance;
    static void startGame() {
        boolean inGame = true;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Player 1 Name: ");
        Player player1 = new Player(scanner.nextLine());
        System.out.print("Enter Player 2 Name: ");
        Player player2 = new Player(scanner.nextLine());
        gameInstance = new Game(player1, player2);
        StringBuilder board = new StringBuilder(("----------------------------------------------------------------------"));
        Board[] tile = Board.generateBoard();
        Board.generateTraps(tile);
        for (Board i:tile){
            board.append(i.getTile());
        }
        System.out.println(board);
        while (inGame) {
            Player player = gameInstance.getCurrentPlayerTurn();
            if (player.getSkipTurn()) {
                System.out.println("\n\n" + gameInstance.getCurrentPlayerTurn().getName() + "'s TURN IS SKIPPED THIS ROUND");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                player.setSkipTurn(false);
                gameInstance.switchTurn();
            }
            int input = Menu.gameMenu();
            player = gameInstance.getCurrentPlayerTurn();
            int cooldown = gameInstance.getCurrentPlayerTurn().getCooldown();
            switch (input) {
                case 1 -> {
                    if (gameInstance.getCurrentPlayerTurn().getCooldown() > 0){
                        gameInstance.getCurrentPlayerTurn().setCooldown(gameInstance.getCurrentPlayerTurn().getCooldown() - 1);
                    }
                    Board.deletePlayer(tile);
                    int rolled = Game.dice();
                    int original = rolled;
                    if (player.getDoubleCheck()){
                        rolled *= 2;
                        player.setCurrentTile(player.getCurrentTile() + rolled);
                    }
                    else {
                        player.setCurrentTile(player.getCurrentTile() + rolled);
                    }
                    System.out.println(Board.movePlayer(tile));
                    if (player.getDoubleCheck()){
                        System.out.println(original + " * 2 = " + rolled);
                    }
                    player.setDoubleCheck(false);
                    System.out.println(player.getName().toUpperCase() + " rolled a " + rolled);
                    try {
                        Game.gameEvent(tile);
                    } catch (ArrayIndexOutOfBoundsException ignored){}
                    gameInstance.switchTurn();
                }
                case 2 -> {
                    if (cooldown == 0) {
                        int input1 = Menu.abilityMenu();
                        switch (input1) {
                            case 1 -> Game.stun();
                            case 2 -> Game.doubleRoll();
                            case 3 -> System.out.println("\n GOING BACK \n");
                        }
                    }
                    else {
                        BoatRacing.gameInstance.getCurrentPlayerTurn().setTurn(BoatRacing.gameInstance.getCurrentPlayerTurn().getTurn() - 1);
                        System.out.println("Pls wait for " + BoatRacing.gameInstance.getCurrentPlayerTurn().getCooldown() + " Turn !!!");
                    }
                }
                case 3 ->{
                    inGame = false;
                }
            }
            if (Game.winCheck()){
                if (BoatRacing.gameInstance.getCurrentPlayerTurn().getCurrentTile() > 99){
                    BoatRacing.gameInstance.getCurrentPlayerTurn().setWinner(true);
                }
                System.out.println(player.getName() + " WON!");
                Leaderboard.addPlayer(player.getName().replace(" ", "") + "|" + player.getTurn());
                break;
            }
        }
    }

    public static void main (String[] args){
        boolean on = true;
        while (on) {
            int input = Menu.MainMenu();
            switch (input) {
                case 1 -> {
                    Leaderboard.leaderboardHandler();
                    System.out.println("Game Loading");
                    startGame();
                }
                case 2 -> Menu.instruction();
                case 3 -> Menu.Leaderboard();
                case 4 -> {
                    System.out.println("GoodBYE");
                    on = false;
                }
            }
        }
    }
}
