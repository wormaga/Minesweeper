import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
/**
 * This class create cell object, set all parametrs (isOpen, flag, isBomb, bang, positionX, positionY, bombAround)
 * of cell. 
 * To cell is added mouseListener that allow recognize when player did his/her step.
 * 
 * Here are methods that display bomb (method "paintBomb") and number/letter (method "paintString").
 * 
 * Inside method "paint" are written all rules what must be desplayed in cell depending on its (cell's) parametrs.
 * 
 * @author (Aleksandrs Konopackis) 
 * @version (05-06.09.2017)
 * 
 */
public class Cell extends JPanel 
{
    private boolean isOpen, flag, isBomb, bang;
    private int positionX, positionY, bombAround;
    private Minesweeper mainObj;

    public Cell(Minesweeper mainObj){
        this.mainObj = mainObj;
        isOpen = false;
        flag = false;
        isBomb = false; //never change, otherwise GameField.fillFieldWithBombs() will never leave the loop
        bang = false;
        positionX = -1;
        positionX = -1;
        bombAround = 0;
        this.setBackground(Constants.COLORS.get("BACKGROUND"));
        
        this.addMouseListener(new MouseAdapter() 
            { 
                public void mouseReleased(MouseEvent e) { 
                    //System.out.println(e); //for testing
                    //System.out.println(e.getComponent()); //for testing
                    //String k = detectMouseButton(e); //for testing

                    if (!mainObj.getGameEnd())
                    {
                        mainObj.setMouseEvent (e);
                        mainObj.stepInGame(positionX, positionY);
                        e.getComponent().repaint(); //last in list
                    }
                } 
            });
    }

    public void plusBombAround()
    {
        if (!isBomb) bombAround++;
    }

    /* Painting */
    
    /*
     * Draw bomb in a cell
     */
    void paintBomb(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(6, 9, 18, 10);
        g.fillRect(10, 5, 10, 18);
        g.fillRect(8, 7, 14, 14);
        g.setColor(Color.white);
        g.fillRect(10, 9, 4, 4);
    }
    
    /*
     * Draw number (bombAround) or letter (flag) in a cell
     */
    void paintString(Graphics g, String str, Color color) {
        if (str.equals("0")) str="";
        g.setColor(color);
        g.setFont(new Font("", Font.BOLD, Constants.CELL_SIZE-8));
        g.drawString(str, 9, 22);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g); //allow to initialize cells

        if (isOpen)
        {
            if(isBomb)
            {
                if (bang)
                {
                    paintBomb(g, Constants.COLORS.get("BANG"));
                } 
                else
                {
                    paintBomb(g, Constants.COLORS.get("BOMB"));
                }
            }
            else
            {
                this.setBackground(Constants.COLORS.get("BACKGROUND"));
                paintString(g,""+bombAround,Constants.COLORS.get(""+bombAround));
            }
        }
        else if (flag)
        { 
            g.setColor(Constants.COLORS.get("CLOSED"));
            g.fillRect(0,0, Constants.CELL_SIZE, Constants.CELL_SIZE);
            paintString(g, "f", Constants.COLORS.get("FLAG")); 
        }
        else
        {
            //!isOpen && !flag
            g.setColor(Constants.COLORS.get("CLOSED"));
            g.fillRect(0,0, Constants.CELL_SIZE, Constants.CELL_SIZE);
        }
    }

    //====================================================================
    //inverse methods:
    //===================================================================

    public void inverseFlag()
    {
        flag = !flag;
    }

    //====================================================================
    //get / set methods:
    //===================================================================

    public void setIsOpen(boolean isOpen)
    {
        this.isOpen = isOpen;
    }

    public boolean getIsOpen ()
    {
        return isOpen;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    public boolean getFlag ()
    {
        return flag;
    }

    public void setIsBomb(boolean isBomb)
    {
        this.isBomb = isBomb;
    }

    public boolean getIsBomb()
    {
        return isBomb;
    }

    public void setBang(boolean bang)
    {
        this.bang = bang;
    }

    public boolean getBang ()
    {
        return bang;
    }

    public void setPositionX (int positionX)
    {
        this.positionX = positionX;
    }

    public void setPositionY (int positionY)
    {
        this.positionY = positionY;
    }

    public int getPositionX()
    {
        return positionX;
    }

    public int getPositionY()
    {
        return positionY;
    }

    public void setBombAround(int bombAround)
    {
        this.bombAround = bombAround;
    }

    public int getBombAround()
    {
        return bombAround;
    }
}