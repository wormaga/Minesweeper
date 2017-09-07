import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * Minesweeper class is main class of this game, here are all calculation and initialization.
 * Action on clicks.
 * Open cells around if you open empty cell.
 * Open cells with bombs at the end of the game.
 * 
 * @author (Aleksandrs Konopackis) 
 * @version (05-06.09.2017)
 */
public class Minesweeper
{
    private GameField gameFieldobject;
    private Cell[][] gameField;
    private int countFlagedCells, countOpenCells;
    private boolean gameEnd;
    private MouseEvent mouseEvent;
    private Window gui;
    private Minesweeper mainObj;

    public static void main(String[] args) {
        new Minesweeper();
    }

    /**
     * Create GameField object (with setuped cells inside)
     * Set own parametrs (int countFlagedCells, int countOpenCells, boolean gameEnd)
     * Create Frame (gui)
     */
    public Minesweeper()
    {
        gameFieldobject = new GameField(this);
        gameField = gameFieldobject.getGameField();
        countFlagedCells = 0;
        countOpenCells = 0;
        gameEnd = false;

        gui = new Window(this);
    }

    /*
     * This method executes when user click on the cells with right/left mouse button.
     * This method change paramets of cells depending on user's actions.
     */
    public void stepInGame(int positionX, int positionY)
    {
        Cell cell = gameField[positionX][positionY];

        if (detectMouseButton(mouseEvent).equals("left"))
        {
            if (!cell.getIsOpen() && !cell.getFlag()) 
            {
                if (cell.getIsBomb())  
                {
                    cell.setBang(true);
                }
                else
                {
                    openArea(cell.getPositionX(), cell.getPositionY());
                }
                cell.setIsOpen(true);
            }

            checkGameEnd(cell);
            if (getGameEnd()) 
            {
                showAllBombs();
                String whoYouAre;
                if (cell.getBang()) whoYouAre = "You lose.";
                else whoYouAre = "You win!";
                gui.popupWindow(whoYouAre);
            }
        }

        if (detectMouseButton(mouseEvent).equals("right"))
        {
            if (!cell.getIsOpen())
            {
                if (countFlagedCells < Constants.COUNT_BOMBS)
                {
                    if (cell.getFlag())minusCountFlagedCells();
                    else plusCountFlagedCells(); 
                    cell.inverseFlag();
                }
                else
                {
                    if (cell.getFlag())
                    {
                        minusCountFlagedCells();
                        cell.inverseFlag();
                    }
                }

            }

        }

        if (!detectMouseButton(mouseEvent).

        equals("undetected"))
        {

        }
    }

    /*
     * After each user's action this method check if user finished game 
     * and depending on it modify: (boolean)gameEnd
     */
    private void checkGameEnd(Cell cell)
    {
        if (countOpenCells == Constants.COUNT_CELLS - Constants.COUNT_BOMBS ||  cell.getBang() )
        {
            setGameEnd(true);
        }
    }

    /*
     * @return string describing which mouse buttod did the action
     */
    private String detectMouseButton(MouseEvent mouseEvent)
    {
        switch (mouseEvent.getButton()) {
            case 1: return "left";
            case 3: return "right";
            default : return "undetected";        
        }
    }

    /*
     * This recursive method open cells around empty cell.
     */
    private void openArea(int positionX, int positionY)
    {
        Cell cell = gameField[positionX][positionY];
        if (cell.getBombAround() > 0) 
        {
            if (!cell.getIsOpen()) plusCountOpenCells();
            cell.setIsOpen(true);
            cell.repaint();
        }
        else if (cell.getBombAround() == 0)
        {
            if (!cell.getIsOpen()) plusCountOpenCells();
            cell.setIsOpen(true);
            cell.repaint();
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

            for (int l = 0; l < points.length; l++) 
            {
                int dx = points[l];
                int dy = points[++l];

                int newX = positionX + dx;
                int newY = positionY + dy;

                if (newX >= 0 && newX < Constants.CELLS_IN_ROW && 
                newY >= 0 && newY < Constants.CELLS_IN_COLUMN &&
                !gameField[newX][newY].getIsOpen() ) 
                {
                    openArea(newX, newY);
                }
            }

        }

    } //end of openArea

    /*
     * When game is finished this method open cells where are bombs.
     */
    public void showAllBombs()
    {
        for (int i=0; i< Constants.CELLS_IN_COLUMN; i++)
            for (int j=0; j<Constants.CELLS_IN_ROW ;j++ ) {
                Cell cell = gameField[j][i];
                if (cell.getIsBomb())
                {
                    cell.setIsOpen(true);
                    cell.setFlag(false);
                    cell.repaint();
                }
            }
    }

    private void plusCountFlagedCells()
    {
        countFlagedCells++;
    }

    private void plusCountOpenCells()
    {
        countOpenCells++;
    }

    private void minusCountFlagedCells()
    {
        countFlagedCells--;
    }

    public void exitTheProgram ()
    {
        System.exit(0);
    }

    public void startNewGame()
    {
        resetMinesweeper();
        gameFieldobject.resetGameField();
    }

    private void resetMinesweeper()
    {
        countFlagedCells = 0;
        countOpenCells = 0;
        gameEnd = false;
    }
    //====================================================================
    //get / set methods:
    //===================================================================

    public boolean getGameEnd()    {
        return gameEnd;
    }

    public void setGameEnd (boolean gameEnd)
    {
        this.gameEnd = gameEnd;
    }

    public void setMouseEvent (MouseEvent mouseEvent)
    {
        this.mouseEvent = mouseEvent;
    }

    public GameField getGameFieldobject()
    {
        return gameFieldobject;
    }

}
