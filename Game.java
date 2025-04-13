import java.util.Random;

public class Game {
    private Player currentPlayerTurn;
    private Player previousPlayerTurn;
    private final Player player1;
    private final Player player2;

    public Player getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public Player getPreviousPlayerTurn() {
        return previousPlayerTurn;
    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayerTurn = this.player1;
        previousPlayerTurn = this.player2;
    }

    public static int dice(){
        Random rand = new Random();
        return rand.nextInt(6 - 1 + 1) + 1;
    }

    public static boolean winCheck(){ //Change the number to smaller to bug test
        return BoatRacing.gameInstance.player1.getCurrentTile() > 98 || BoatRacing.gameInstance.player2.getCurrentTile() > 98;
    }

    public static void Trap(GameEvent gameEvent, Board[] tile){
        Board.deletePlayer(tile);
        System.out.println("OH NO YOU HIT A TRAP AND MOVED " + gameEvent.getMove() + " SPACES BACKWARDS!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BoatRacing.gameInstance.getCurrentPlayerTurn().setCurrentTile(Math.max(BoatRacing.gameInstance.getCurrentPlayerTurn().getCurrentTile() + gameEvent.getMove(), 0));
    }

    public static void Current(GameEvent gameEvent, Board [] tile){
        Board.deletePlayer(tile);
        System.out.println("YES! YOU HIT A CURRENT AND MOVED " + gameEvent.getMove() + " SPACES FORWARD!");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BoatRacing.gameInstance.getCurrentPlayerTurn().setCurrentTile(BoatRacing.gameInstance.getCurrentPlayerTurn().getCurrentTile() + gameEvent.getMove());
    }

    public static void stun(){
        if(BoatRacing.gameInstance.currentPlayerTurn.getStun() != 0){
            BoatRacing.gameInstance.previousPlayerTurn.setSkipTurn(true);
            BoatRacing.gameInstance.currentPlayerTurn.setTurn(BoatRacing.gameInstance.currentPlayerTurn.getTurn() - 1);
            BoatRacing.gameInstance.currentPlayerTurn.setStun(BoatRacing.gameInstance.currentPlayerTurn.getStun() - 1);
            System.out.println("STUNNED");
            BoatRacing.gameInstance.currentPlayerTurn.setCooldown(3);
        }
    }

    public static void doubleRoll(){
        if(BoatRacing.gameInstance.currentPlayerTurn.getDoubleRoll() != 0) {
            BoatRacing.gameInstance.currentPlayerTurn.setDoubleCheck(true);
            BoatRacing.gameInstance.currentPlayerTurn.setTurn(BoatRacing.gameInstance.currentPlayerTurn.getTurn() - 1);
            BoatRacing.gameInstance.currentPlayerTurn.setDoubleRoll(BoatRacing.gameInstance.currentPlayerTurn.getDoubleRoll() - 1);
            System.out.println("SLAYYY DOUBLED");
            BoatRacing.gameInstance.currentPlayerTurn.setCooldown(3);
        }
    }
    public static void gameEvent(Board [] tile){
        GameEvent gameEvent = tile[BoatRacing.gameInstance.getCurrentPlayerTurn().getCurrentTile()].getGameEvent();
        if (gameEvent instanceof Trap){
            Trap(gameEvent, tile);
            System.out.println(Board.movePlayer(tile));
        }
        if (gameEvent instanceof Current){
            Current(gameEvent, tile);
            System.out.println(Board.movePlayer(tile));
        }
    }


    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void switchTurn(){
        this.currentPlayerTurn = currentPlayerTurn == player1 ? player2 : player1;
        this.previousPlayerTurn = previousPlayerTurn == player1 ? player2 : player1;
    }
}
