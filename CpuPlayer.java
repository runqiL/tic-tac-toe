
public class CpuPlayer extends APlayer
{
    public CpuPlayer(Game game, char symbol)
    {
        super(game, symbol);
    }
    
    public Move pickMove()
    {
        Move move = new Move (0, 0);
        int row;
        int col;

        while(true){
            row = (int) ((game.getBoardSize() * Math.random())+1);
            col = (int) ((game.getBoardSize() * Math.random())+1);

            move.row = row;
            move.col = col;

            if (game.isValidMove(move) == 'V')
            {
                break;
            }
        }
        return move;
    }
    
    public char getSymbol()
    {
        return this.symbol;
    }
}
