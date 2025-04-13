import java.util.Random;

public class Board {
    private String tile;
    private GameEvent gameEvent;

    public Board(String tile) {
        this.tile = tile;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public GameEvent getGameEvent(){ return gameEvent; }

    public void setGameEvent(GameEvent gameEvent){ this.gameEvent = gameEvent; }

    public static Board[] generateBoard(){
        Board [] tile = new Board [100];
        tile[0] = new Board("\n|P1.P2|");
        for (int i = 1; i<100; i++) {
            if (i % 10 == 0) {
                tile[i] = new Board("\n|  .  |");
            } else {
                tile[i] = new Board("|  .  |");
            }
        }
        return tile;
    }

    public static void generateTraps(Board[] tiles){
        Random rand = new Random();
        for (int i = 0; i<15; i++){
            while (true) {
                int resRandom = rand.nextInt(97) + 1;
                if (tiles[resRandom].getTile().contains(".")) {
                    tiles[resRandom].setTile(tiles[resRandom].getTile().replace(".", "T"));
                    tiles[resRandom].setGameEvent(new Trap());
                    break;
                }
            }
            while (true) {
                int resRandom = rand.nextInt(97) + 1;
                if (tiles[resRandom].getTile().contains(".")) {
                    tiles[resRandom].setTile(tiles[resRandom].getTile().replace(".", "C"));
                    tiles[resRandom].setGameEvent(new Current());
                    break;
                }
            }
        }
    }

    public static StringBuilder movePlayer(Board [] tile){
        StringBuilder board = new StringBuilder(("----------------------------------------------------------------------"));
        try{
            if (BoatRacing.gameInstance.getCurrentPlayerTurn() == BoatRacing.gameInstance.getPlayer1()) {
                String p1Tile = tile[BoatRacing.gameInstance.getPlayer1().getCurrentTile()].getTile();
                tile[BoatRacing.gameInstance.getPlayer1().getCurrentTile()].setTile(p1Tile.replaceFirst(" {2}", "P1"));
            }
            if (BoatRacing.gameInstance.getCurrentPlayerTurn() == BoatRacing.gameInstance.getPlayer2()) {
                String p2Tile = tile[BoatRacing.gameInstance.getPlayer2().getCurrentTile()].getTile();
                tile[BoatRacing.gameInstance.getPlayer2().getCurrentTile()].setTile(p2Tile.replaceFirst(" {2}", "P2"));
            }
        } catch (Exception ignored){

        }
        for (Board i:tile){
            board.append(i.getTile());
        }
        return board;
    }

    public static void deletePlayer(Board[] tile){
        try {
            if (BoatRacing.gameInstance.getCurrentPlayerTurn() == BoatRacing.gameInstance.getPlayer1()) {
                String p1Tile;
                if (BoatRacing.gameInstance.getCurrentPlayerTurn().getCurrentTile() < 0){
                    p1Tile = tile[0].getTile();
                }
                else {
                    p1Tile = tile[BoatRacing.gameInstance.getPlayer1().getCurrentTile()].getTile();
                }
                tile[BoatRacing.gameInstance.getPlayer1().getCurrentTile()].setTile(p1Tile.replaceFirst("P1", "  "));
            }
            if (BoatRacing.gameInstance.getCurrentPlayerTurn() == BoatRacing.gameInstance.getPlayer2()) {
                String p2Tile;
                if (BoatRacing.gameInstance.getCurrentPlayerTurn() == BoatRacing.gameInstance.getPlayer2()) {
                    if (BoatRacing.gameInstance.getCurrentPlayerTurn().getCurrentTile() < 0) {
                        p2Tile = tile[0].getTile();
                    } else {
                        p2Tile = tile[BoatRacing.gameInstance.getPlayer2().getCurrentTile()].getTile();
                    }
                    tile[BoatRacing.gameInstance.getPlayer2().getCurrentTile()].setTile(p2Tile.replaceFirst("P2", "  "));
                }
            }
        }
        catch (Exception ignored){
        }
    }
}
