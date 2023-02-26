public abstract class APlayer
{
    protected Game game;
    protected char symbol;


    protected APlayer(Game ganme, char symbol)
    {
        this.game = game;
        this.symbol = symbol;
    }
    
    char getSymbol() {
        return this.symbol;
    }
    
    Move pickMove()
    {
        Move move = new Move(1,1);
        return move;
    }
    
}
