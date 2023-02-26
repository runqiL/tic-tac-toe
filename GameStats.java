public class GameStats
{
    int nlosses;
    int nties;
    int nwins;
    
    GameStats()
    { }
    
    void recordLoss()
    {
        nlosses++;
    }
    
    void recordTie()
    {
        nties++;
    }
    
    void recordWin()
    {
        nwins++;
    }
    
    public String toString()
    {
        return "\nGame Statistics\nTotal Wins: " + nwins + "\nTotal Losses: " + nlosses + "\nTotal Ties: " + nties;
    }
}
