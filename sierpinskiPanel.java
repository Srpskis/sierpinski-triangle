package fills;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SierpinskiPanel extends JPanel{


    // data members
    private static final long serialVersionUID = 1L;
    // 600 - 25 = 575
    public static final int WIDTH = DisplayPanel.WIDTH - 25;
    // 500 - 75 = 425
    public static final int HEIGHT = DisplayPanel.HEIGHT - 75;

    //set beginning order
    private int order;
    // JButton object reference
    private JButton button;
    private JButton button2;
    // JLabel object reference
    private JLabel label;
    // JPanel object reference
    public JPanel subPanel;
    // Point object references
    private Point p0;
    private Point p1;
    private Point p2;
    // Polygon object reference
    private Polygon triangle;

    //****************************************************
    //* Check the boundaries of the display
    //****************************************************

    // Constructor
    public SierpinskiPanel() {
        // set order to 0
        order = 1;
        // set subPanel reference to new JPanel object
        subPanel = new JPanel();
        // set label to new JLabel object with the text
        label = new JLabel("n = " + order);
        // set button to new Jbutton object with a text
        button = new JButton("  +  ");
        button2 = new JButton("  -  ");
        // addActionListener to button using anonymous class object ActionListener
        button.addActionListener(new ActionListener() {
            @Override
            // implementing method
            public void actionPerformed(ActionEvent e) {
                // increment order
                order++;
                // set label test after incrementing
                label.setText("n = " + order);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            // implementing method
            public void actionPerformed(ActionEvent e) {
                if(order > 1) {
                    // increment order
                    order--;
                    label.setText("n = " + order);
                }
            }
        });
        // add button to subPanel
        subPanel.add(button);
        subPanel.add(button2);
        // add label to subPanel
        subPanel.add(label);
        // adding subPanel to TrianglePanel's object panel
        add(subPanel);
        // a call to setTriangle method
        setTriangle();
    }

    //****************************************************
    //* display triangle of order n
    //****************************************************
    public void paintComponent(Graphics pen) {
        // call displayTriangle method, and pass a pen argument, triangle, and order
        displayTriangles(pen, triangle, order);
    }

    //****************************************************
    //* Set zeroth order vertices of Triangle
    //****************************************************
    public void setTriangle() {
        // 287.5, 25, 550
        int[] x = {WIDTH / 2, 25, WIDTH - 25};
        // 10, 400, 400
        int[] y = {10, HEIGHT - 25, HEIGHT - 25};
        // new Triangle object with x coordinates set as array of {287.5, 25, 550}
        // and y coordinates set as array of {10, 400, 400},
        // and 3 number of points
        triangle = new Polygon(x, y, 3);

    }

    //****************************************************
    //* Set a new order
    //****************************************************
    public void displayTriangles(Graphics pen, Polygon triangle, int order) {
        // if the order is 0, display the triangle of order 0
        if (order == 1) {
            setTriangle();
            // calling the drawPolygon method using passed Graphics object pen
            // passing a triangle
            pen.fillPolygon(triangle);
        }
        // else display triangle of order - 1 with triangle of order n
        else {
            // create array and populate it by calling createTriangles method. pass a triangle
            Polygon[] sierpinski = createTriangles(triangle);
//
            // recursively display three triangles
            // call itself and pass a pen, stored polygon in index'ses, and -1 order
            displayTriangles(pen, sierpinski[0], order - 1);
            displayTriangles(pen, sierpinski[1], order - 1);
            displayTriangles(pen, sierpinski[2], order - 1);

            // draw a new polygon
            pen.drawPolygon(sierpinski[0]);
            pen.drawPolygon(sierpinski[1]);
            pen.drawPolygon(sierpinski[2]);

        }
        // call repaint
        repaint();
    }

    //This method calculates the midpoint of the side of a triangle.
    private static Point midpoint(Point p1, Point p2) {
        return new Point(((p1.x + p2.x) / 2), (p1.y + p2.y) / 2);
    }

    public Polygon[] createTriangles(Polygon triangle) {
        // define new Point's with coordinates stored in array of passed triangle object.
        // p0 accessing the value of first index of array using xpoints method for x and y coord values
        // etc.
        p0 = new Point(triangle.xpoints[0], triangle.ypoints[0]);
        p1 = new Point(triangle.xpoints[1], triangle.ypoints[1]);
        p2 = new Point(triangle.xpoints[2], triangle.ypoints[2]);

        // get the midpoint on each edge in the triangle
        // call midpoint method and pass the values of points
        // first midpoint is in between p0 and p1
        Point m01 = midpoint(p0, p1);
        // second  midpoint is in between p1 and p2
        Point m12 = midpoint(p1, p2);
        // third midpoint is in between p2 and p0
        Point m20 = midpoint(p2, p0);

        // store in array the coordinate values of points and midpoints
        int[] t0X = {p0.x, m01.x, m20.x};
        int[] t0Y = {p0.y, m01.y, m20.y};

        int[] t1X = {m01.x, p1.x, m12.x};
        int[] t1Y = {m01.y, p1.y, m12.y};

        int[] t2X = {m20.x, m12.x, p2.x};
        int[] t2Y = {m20.y, m12.y, p2.y};

        // Create a new Polygon with passed arrays
        Polygon t0 = new Polygon(t0X,t0Y,3);
        Polygon t1 = new Polygon(t1X,t1Y,3);
        Polygon t2 = new Polygon(t2X, t2Y,3);

        // create a new Polygon array
        Polygon[] p = new Polygon[3];
        // populate array with created polygons
        p[0] = t0;
        p[1] = t1;
        p[2] = t2;

        // return array
        return p;

    }






}