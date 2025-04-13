public class Current extends GameEvent{

    public Current(){
        super("Current");

        setMove(Game.dice());
    }
}
