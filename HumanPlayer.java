import java.util.Scanner;
import java.io.IOException;

public class HumanPlayer extends APlayer
{
    public HumanPlayer(Game game, char symbol)
    {
        super(game, symbol);
    }
    
    public Move pickMove()
    {
        Scanner scanner = new Scanner(System.in);
        Move move = new Move(0, 0);
        
        while(true){
            System.out.print("Please enter your move (enter 'quit' to leave the game): ");
            String input = scanner.next().toUpperCase();
            if(input.equals("QUIT")){
                return null;
            }
            else if (input.length() != 2){
                System.out.println("Invalid input length. Please enter two characters.");
                continue;
            }
            
            try{
                if(input.length() == 2){
                int row = (int)(input.charAt(0) - '@');
                int col = (int)(input.charAt(1) - '0');
                move.row = row;
                move.col = col;
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
            
            if(this.game.isValidMove(move) == 'V'){
                break;
            }
            if(this.game.isValidMove(move) == 'O'){
                System.out.println("This position is already occupied. Please try again.");
                continue;
            }
            else{
                System.out.println("This position is outside the bound of the game board. Please try again.");
                continue;
            }
        }
        return move;
    }
    
    public char getSymbol() {
        return this.symbol;
    }
}
