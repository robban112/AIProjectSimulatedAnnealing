import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

class DrawLines {

    public static void main(String[] args) {

        Runnable r = () -> {
            LineComponent lineComponent = new LineComponent(400,400);
            for (int ii=0; ii<30; ii++) {
                lineComponent.addLine(2,2, 3, 3);
            }
            JOptionPane.showMessageDialog(null, lineComponent);
        };
        SwingUtilities.invokeLater(r);
    }
}

class LineComponent extends JComponent {

    ArrayList<Line2D.Double> lines;
    Tour tour;
    Random random;

    LineComponent(int width, int height) {
        super();
        setPreferredSize(new Dimension(width,height));
        lines = new ArrayList<Line2D.Double>();
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        Line2D.Double line = new Line2D.Double(x1,y1,x2,y2);
        Point2D.Double p1 = new Point2D.Double(x1,y1);
        Point2D.Double p2 = new Point2D.Double(x2,y2);
        lines.add(line);
        repaint();
    }

    public void setTour(Tour tour){ this.tour = tour; }

    public void paintComponent(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        Dimension d = getPreferredSize();
        g.setColor(Color.black);
        for (Line2D.Double line : lines) {
            g.drawLine(
                    (int)line.getX1(),
                    500-(int)line.getY1(),
                    (int)line.getX2(),
                    500-(int)line.getY2()
            );
        }
        g.setColor(Color.red);
        for(City city : tour.getTour()) {
            g.drawString(city.name, city.x*5, 500-city.y*5+5);
        }
    }
}