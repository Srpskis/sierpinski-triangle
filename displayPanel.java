package fills;

// importing relevant packages
import javax.swing.*;
import java.awt.BorderLayout;

public class DisplayPanel{


    // data members. represent width,height, and location of display window frame
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;
    public static final int LEFT_X = 750;
    public static final int TOP_Y = 100;

    // Constructor
    public DisplayPanel() {

        SierpinskiPanel panel = new SierpinskiPanel();

        // Creates a border with a lowered beveled edge with a bright shade of the Component’s current
        // background color for line and dark shading for shadow. This border is effective when you change the
        // background color of the Component.
        panel.subPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        // Creates a new titled border with the specified title, the default border type
        // (determined by the current look and feel), the default text position (sitting on the top line),
        // the default justification (leading),
        // and the default font and text color (determined by the current look and feel).
        panel.subPanel.setBorder(BorderFactory.createTitledBorder("Level"));

        // Creates new JFrame object with a title
        JFrame frame = new JFrame("Sierpinski Triangle");

        // setting its layout manager to new BorderLayout object using the setLayout method.
        frame.setLayout(new BorderLayout());
        //  adding the panel, and the CENTER expands
        //  and shrinks on both height and width
        frame.add(panel, BorderLayout.CENTER);
        // The north and SOUTH regions
        // expand or shrink in height only
        frame.add(panel.subPanel, BorderLayout.SOUTH);
        // setting frame size
        frame.setSize(WIDTH, HEIGHT);
        // setting frame location
        frame.setLocation(LEFT_X, TOP_Y);
        // setting default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setting visibility
        frame.setVisible(true);

    }
    // creating new Display object
    public static void main(String[] args) {
        new DisplayPanel();
    }
}

// added a comment 
