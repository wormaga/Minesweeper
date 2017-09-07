import java.util.*;

/**
 * This class creates array Cel[][], where it store all cells. 
 * Add bombs to the field.
 * Count number of bombs around each cell
 * 
 * @author (Aleksandrs Konopackis) 
 * @version (05-06.09.2017)
 */
public class GameField
{
    private Cell[][]  gameField;
    private Minesweeper mainObj;

    public GameField(Minesweeper mainObj)
    {
        this.mainObj = mainObj;
        gameField = new Cell[Constants.CELLS_IN_ROW][Constants.CELLS_IN_COLUMN];
        createField();
        fillFieldWithBombs();
        countBoumbAround();
    }
    
    /* Fill field with cells, and give postion in Cell[][] to each cell;*/
    private void createField()
    {
        for (int i=0; i< Constants.CELLS_IN_COLUMN; i++)
            for (int j=0; j<Constants.CELLS_IN_ROW ;j++ ) {
                Cell cell = new Cell(mainObj);
                gameField[j][i] = cell;

                /*Give position in array gameField to each cell; gameField[positonX][positionY]*/
                cell.setPositionX(j);
                cell.setPositionY(i);

            }
    }

    /*
     * Add bombs on Empty gameField (before game starts)
     * Add exactly (int)Constants.COUNT_BOMB bombs.
     */
    private void fillFieldWithBombs()
    {
        for (int i=0; i< Constants.COUNT_BOMBS; i++) {
            int x = new Random().nextInt(Constants.CELLS_IN_ROW);
            int y = new Random().nextInt(Constants.CELLS_IN_COLUMN);
            Cell cell = gameField[x][y];
            if (cell.getIsBomb()) 
            {
                    i--;
            }
            else 
            {
                cell.setIsBomb(true);   
            }
        }
    }

    /*
     * Define number of bombs around for each cell.
     */
    private void countBoumbAround()
    {
        int[] points = new int[] {
            -1, -1,
            -1, 0,
            -1, 1,
            0, -1,
            0, 1,
            1, -1,
            1, 0,
            1, 1
        };

        for (int i=0; i< Constants.CELLS_IN_COLUMN; i++)
            for (int j=0; j<Constants.CELLS_IN_ROW ;j++ ) 
            {
                Cell cell = gameField[j][i];

                if (cell.getIsBomb())
                {
                    for (int l = 0; l < points.length; l++) 
                    {
                        int dx = points[l];
                        int dy = points[++l];

                        int newX = j + dx;
                        int newY = i + dy;

                        if (newX >= 0 && newX < Constants.CELLS_IN_ROW && 
                            newY >= 0 && newY < Constants.CELLS_IN_COLUMN) 
                        {
                            gameField[newX][newY].plusBombAround();
                        }
                    }
                }
            }
    }

    public void resetGameField()
    {
        for (int i=0; i< Constants.CELLS_IN_COLUMN; i++)
            for (int j=0; j<Constants.CELLS_IN_ROW ;j++ ) 
            {
                gameField[j][i].setIsOpen(false);
                gameField[j][i].setFlag(false);
                gameField[j][i].setIsBomb(false);
                gameField[j][i].setBang(false);
                gameField[j][i].setBombAround(0);
                
                gameField[j][i].repaint();
            }
        fillFieldWithBombs();  
        countBoumbAround();
    }
    
    //====================================================================
    //get / set methods:
    //===================================================================

    public Cell[][] getGameField()
    {
        return gameField;
    }
}
