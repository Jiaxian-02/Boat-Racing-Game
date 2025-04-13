public class GameEvent {
    private final String name;
    private int move;


    public GameEvent(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getMove(){
        return move;
    }

    public void setMove(int move){
        this.move = move;
    }
}
