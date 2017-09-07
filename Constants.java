import java.awt.Color;
import java.util.HashMap;
/**
 * This class contains all constans. 
 * Minesweeper fiels contains 255 cells (15x15) with 10 bobms.
 * Change field's width - CELLS_IN_ROW
 * Change field's hight - CELLS_IN_COLUMN
 * Change number of bombs - COUNT_BOMBS
 * 
 * If you change CELL_SIZE, you need to change "paintBomb" and "paintString" in "Cell" class.
 * 
 * @author (Aleksandrs Konopackis) 
 * @version (05-06.09.2017)
 */
public class Constants
{
    // instance variables - replace the example below with your own

    /*Size of each cell in pixels*/
    public static final int CELL_SIZE = 30;

    /*Space between cells in pixels*/
    public static final int CELL_MARGIN = 2;

    /*how wide is field*/
    public static final int CELLS_IN_ROW = 9; 

    /*how high is field*/
    public static final int CELLS_IN_COLUMN = 9; 

    /*Frame size (width, hight)*/
    public static final int FRAME_WIDTH = CELL_SIZE*CELLS_IN_ROW + (CELLS_IN_ROW-1)*CELL_MARGIN;
    public static final int FRAME_HIGHT = CELL_SIZE*CELLS_IN_COLUMN + (CELLS_IN_COLUMN)*CELL_MARGIN;

    /*Name of the frame*/
    public static final String FRAME_TITLE = "Minesweeper";

    public static final int COUNT_CELLS = CELLS_IN_ROW * CELLS_IN_COLUMN;
    
    /*Number of bombs must be less then cells in total*/
    public static final int COUNT_BOMBS = 10;

    /*Color of cell */
    public static final HashMap<String, Color> COLORS;
    static
    {
    	COLORS = new HashMap<String, Color>();
    	COLORS.put("BACKGROUND",new Color(255, 255, 255));
    	COLORS.put("BORDER",new Color(153,153,153));
    	COLORS.put("CLOSED",new Color(192, 192, 192));
    	COLORS.put("FLAG",new Color(255, 0, 0));
    	COLORS.put("BANG",new Color(255, 0, 0));
    	COLORS.put("BOMB",new Color(0,0,0));
    	COLORS.put("1",new Color(1, 0, 254));
    	COLORS.put("2",new Color(1, 127, 1));
    	COLORS.put("3",new Color(255, 0, 0));
    	COLORS.put("4",new Color(1, 0, 128));
    	COLORS.put("5",new Color(129, 1, 2));
    	COLORS.put("6",new Color(0, 128, 129));
    	COLORS.put("7",new Color(0, 0, 0));
    	COLORS.put("8",new Color(128, 128, 128));

    }
}
