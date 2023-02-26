public class Game
{
    char[][] board;
    int boardSize;
    private char SYMBOL_BLANK = ' ';
    private char SYMBOL_CPU = 'O';
    private char SYMBOL_HUMAN = 'X';

    public Game(int boardSize)
    {
        this.boardSize = boardSize;
        board = new char[boardSize][boardSize];
        for(int r = 0; r < boardSize; r++){
            for(int c = 0; c < boardSize; c++){
                board[r][c] = ' ';
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Welcome to Tic-tac-toe!");
        Game game = new Game(0);
        GameStats gs = new GameStats();
        boolean quit = false;

        if(args.length==0){
            game = new Game(3);
        }
        else if(Integer.parseInt(args[0]) >= 1 && Integer.parseInt(args[0])<=9){
            game = new Game(Integer.parseInt(args[0]));
        }
        else{
            game = new Game(3);
        }

        while(!(quit)){
            System.out.println("\n-----------------NEW GAME------------------");
            System.out.println(game);

            HumanPlayer human = new HumanPlayer(game,'X');
            CpuPlayer cpu = new CpuPlayer(game,'O');
            cpu.game = game;
            human.game = game;

            boolean cpuFirst = Math.random() < .5;
            while (game.getGameStatus() == '?'){
                if (cpuFirst){ 
                    quit = game.playSingleGame(cpu, human);
                    if (quit) break;
                }
                else {
                    quit = game.playSingleGame(human, cpu);
                    if (quit) break;
                }
            }

            if (game.getGameStatus() == 'X'){
                gs.nwins++;
                System.out.println("Congratulations! You beat the computer.");}
            else if (game.getGameStatus() == 'O'){
                gs.nlosses++;
                System.out.println("You lost the game. Give it another try!");}
            else if (game.getGameStatus() == 'T'){
                gs.nties++;
                System.out.println("It's a tie. Maybe you'll win next time!");
            }
            game.resetGame();
        }
        System.out.println(gs);
    }

    public boolean playSingleGame(APlayer p1, APlayer p2){
        Move move = p1.pickMove();
        if(move == null) return true;
        this.executeMove(move, p1.symbol); 
        if(p1 instanceof CpuPlayer) System.out.println("The computer made a move at " + (char)(move.row + '@') + move.col);
        System.out.println(this.toString());

        if(this.getGameStatus() == '?'){
            move = p2.pickMove();
            if(move == null) return true;
            this.executeMove(move, p2.symbol);
            System.out.println(this.toString());
        }
        return false;
    }

    protected boolean executeMove(Move move, char symbol)
    {
        if(isValidMove(move) == 'V'){
            board[move.row-1][move.col-1] = symbol;
            return true;
        }
        else{
            return false;
        }
    }

    public int getBoardSize()
    {
        return this.boardSize;
    }

    public char getGameStatus()
    {
        boolean full = true;
        char winner;

        //check for rows
        for(int r = 0; r < boardSize; r++){
            for(int c = 0; c < boardSize; c++){
                if(board[r][c] != board[r][0]){
                    break;
                }
                if(c == boardSize - 1 && board[r][c] == board[r][0] && board[r][c] != ' '){
                    winner = board[r][c];
                    return winner;
                }
            }
        }

        //check for columns
        for(int c = 0; c < boardSize; c++){
            for(int r = 0; r < boardSize; r++){
                if(board[r][c] != board[0][c]){
                    break;
                }
                if(r == boardSize - 1 && board[r][c] == board[0][c] && board[r][c] != ' '){
                    winner = board[r][c];
                    return winner;
                }
            }
        }

        //check for diagonal
        for(int i = 0; i < boardSize; i++){
            if(board[i][i] != board[0][0] || board[0][0] == ' '){
                break;
            }
            if(i == boardSize - 1 && board[i][i] == board[0][0]){
                winner = board[i][i];
                return winner;
            }
        }

        for(int i = 0; i < boardSize; i++){
            if(board[i][boardSize - i - 1] != board[0][boardSize-1]){
                break;
            }
            if(i == boardSize - 1 && board[i][0] == board[0][boardSize-1] && board[i][0] != ' '){
                winner = board[i][0];
                return winner;
            }
        }

        //check if the board is full
        for(int r = 0; r < boardSize; r++){
            for(int c = 0; c < boardSize; c++){
                if(board[r][c] == ' '){
                    full = false;
                }
            }
        }

        if(full){
            return 'T';
        }

        return '?';
    }

    public char isValidMove(Move move)
    {
        int r = move.row - 1;
        int c = move.col - 1;
        if(board[r][c] != ' '){
            return 'O';
        }
        if(r < 0 || r > (boardSize - 1)){
            return 'R';
        }
        if(c < 0 || c > (boardSize - 1)){
            return 'C';
        }
        else{
            return 'V';
        }
    }

    protected void resetGame()
    {
        for(int r = 0; r < boardSize; r++){
            for(int c = 0; c < boardSize; c++){
                board[r][c] = ' ';
            }
        }
    }

    public String toString()
    {
        String gameBoard = "    ";

        //print the number indices
        for(int i = 1; i <= boardSize; i++){
            gameBoard += i + "   ";
        }
        gameBoard += "\n";

        for(int row = 0; row < boardSize; row++)
        {
            gameBoard += " " + (char)(row + 65) + " ";    //print the letter indices

            for(int col = 0; col < boardSize; col++){
                if (col == boardSize-1){
                    gameBoard += " " + board[row][col] + " \n   ";
                }
                else{
                    gameBoard += " " + board[row][col] + " |";
                }
            }

            if(row < boardSize-1){
                for(int col = 1; col <= boardSize; col++){
                    if (col == boardSize){
                        gameBoard += "---\n";
                    }
                    else{
                        gameBoard += "---|";
                    }
                }
            }

        }
        return gameBoard;
    }
}
