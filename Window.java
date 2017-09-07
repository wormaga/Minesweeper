import javax.swing.*;
import java.awt.*;

/**
 * Visual part of game minesweeper.
 * This object create JFrame add to it cells.
 * 
 * @author (Aleksandrs Konopackis) 
 * @version (05-06.09.2017)
 */
class Window extends JFrame
{
    private JFrame frame;
    private Minesweeper mainObj;
    /**
     * Constructor for objects of class Window
     */
    public Window(Minesweeper mainObj)
    {
        this.mainObj = mainObj;
        Cell[][] gameField = mainObj.getGameFieldobject().getGameField();
        initFrame();
        initCells(gameField);
        pack();
        frame.setVisible(true);//last in list  
    }

    private void initFrame ()
    {
        frame = new JFrame(Constants.FRAME_TITLE);
        frame.setDefaultCloseOperation(3); //3 means EXIT_ON_CLOSE
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HIGHT);
        //frame.setLocation(1610,20);// for testing
        frame.setLocationRelativeTo(null); //Locate frame in the center
        frame.setResizable(false);
        frame.getContentPane().setBackground(Constants.COLORS.get("BORDER"));
        frame.setLayout(new GridLayout(Constants.CELLS_IN_COLUMN,Constants.CELLS_IN_ROW, Constants.CELL_MARGIN,Constants.CELL_MARGIN)); 

    }

    private void initCells(Cell[][] gameField){
        for (int i=0; i< Constants.CELLS_IN_COLUMN; i++)
            for (int j=0; j<Constants.CELLS_IN_ROW ;j++ ) {
                Cell cell = gameField[j][i];
                frame.add(cell);
            }
    }

    /*
     * This is a windows that appears when you win/lose game. You can restart game, exit game, close popup window.
     * 
     * @param Text that contains info "You lose" or "You win" 
     */
    public void popupWindow(String whoYouAre)
    {
        String message = whoYouAre + " Do you want reset game?";
        String nameOfTheWindow = "";

        UIManager.put("OptionPane.noButtonText", "Exit Game");
        int response = JOptionPane.showConfirmDialog(null, message, nameOfTheWindow,
                JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.NO_OPTION) {
            //System.out.println("No button clicked");
            mainObj.exitTheProgram();
        } else if (response == JOptionPane.YES_OPTION) {
            //System.out.println("Yes button clicked");
            mainObj.startNewGame();
        } else if (response == JOptionPane.CLOSED_OPTION) {
            //System.out.println("JOptionPane closed");
        }

    }

    //====================================================================
    //get / set methods:
    //===================================================================

    public JFrame getFrame()
    {
        return frame;
    }
}