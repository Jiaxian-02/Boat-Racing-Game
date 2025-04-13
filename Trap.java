public class Trap extends GameEvent{
    public Trap(){
        super("Trap");

        setMove(-Game.dice());
    }
}
