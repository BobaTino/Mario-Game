import javax.swing.JFrame;
import java.awt.Dimension;

class windows {
    private JFrame frame;
    private Dimension size;

    public windows(int width, int height, String title, gaming game) {
        size = new Dimension(width, height);
        frame = new JFrame(title);

        frame.setPreferredSize(size);
        frame.setMaximumSize(size);
        frame.setMinimumSize(size);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
    }
}
