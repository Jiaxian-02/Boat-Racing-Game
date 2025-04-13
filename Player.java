public class Player {
    private String name;
    private int turn = 1;
    private int currentTile = 0;
    private int tiles;
    private int stun = 3;
    private int doubleRoll = 3;
    private int cooldown = 0;
    private boolean doubleCheck = false;
    private boolean winner = false;
    private boolean skipTurn = false;



    public Player(String name, int turn, int currentTile, int tiles, boolean skipTurn, boolean winner, int stun, int doubleRoll, int cooldown, boolean doubleCheck) {
        this.name = name;
        this.turn = turn;
        this.currentTile = currentTile;
        this.tiles = tiles;
        this.skipTurn = skipTurn;
        this.winner = winner;
        this.stun = stun;
        this.doubleRoll = doubleRoll;
        this.cooldown = cooldown;
        this.doubleCheck = doubleCheck;
    }

    public Player(String name) {
        this.name = name;
    }
    public boolean getSkipTurn() {
        return skipTurn;
    }

    public void setSkipTurn(boolean skipTurn) {
        this.skipTurn = skipTurn;
    }

    public int getTiles() {
        return tiles;
    }

    public void setTiles(int tiles) {this.tiles = tiles;}

    public boolean getDoubleCheck() {
        return doubleCheck;
    }

    public void setDoubleCheck(boolean doubleCheck) {
        this.doubleCheck = doubleCheck;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        if (cooldown < 0) {
            BoatRacing.gameInstance.getCurrentPlayerTurn().setCooldown(0);
        }
        this.cooldown = cooldown;
    }

    public int getDoubleRoll() {
        return doubleRoll;
    }

    public void setDoubleRoll(int doubleRoll) {
        this.doubleRoll = doubleRoll;
    }

    public int getStun() {
        return stun;
    }

    public void setStun(int stun) {
        this.stun = stun;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(int currentTile) {
        this.currentTile = currentTile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
