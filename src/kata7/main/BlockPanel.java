package kata7.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import kata7.view.BlockDisplay;

public class BlockPanel extends JPanel implements BlockDisplay {
    private static final int SIZE = 100;
    private int x = 0;
    private int y = 0;
    private int max;
    private Moved moved;

    public BlockPanel(int max) {
        this.max = max;
        MouseHandler handler = new MouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g.setColor(Color.black);
        int size = max*SIZE;
        for (int i = 0; i <= size; i+=SIZE) {
            g.drawLine(0, i, size, i);
            g.drawLine(i, 0, i, size);
        }
        
        g.setColor(Color.red);
        g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
    }

    @Override
    public void display(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void on(Moved moved) {
        this.moved = moved;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener{
        private boolean grabbed = false;
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if ((e.getX()/SIZE < x) || (e.getY()/SIZE < y) ||
                    (e.getX()/SIZE > x + 1) || (e.getY()/SIZE > y + 1)) return;
            grabbed = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            grabbed = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(grabbed && moved != null) moved.to(e.getX()/SIZE, e.getY()/SIZE);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
        
    }
}