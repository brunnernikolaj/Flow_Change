package boundary;

import java.awt.AlphaComposite;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class FadeTool {
    public static void main(String[] args) throws Exception {
       JFrame f = new JFrame();
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       FadingMirror glassPane = new FadingMirror();
       f.setGlassPane(glassPane);

       JPanel contentPane = new JPanel();
       contentPane.setLayout(new java.awt.GridLayout(10,10));
       for(int row = 0; row < 10; row++)
           for(int column = 0; column < 10; column++) {
               contentPane.add(new JButton("Fading"));
           }
       f.setContentPane(contentPane);

       f.pack();
       f.setVisible(true);

       try{Thread.sleep(500);}
       catch(InterruptedException e) {e.printStackTrace();}

       glassPane.setVisible(true);
       glassPane.beginFade();

    }
    public static class FadingMirror extends JPanel
            implements ActionListener
    {
        private float opacity = 0f;
        private Timer fadeTimer;

        public void beginFade() {
            fadeTimer =
                    new javax.swing.Timer(75,this);
            fadeTimer.setInitialDelay(0);
            fadeTimer.start();

        }
        public void actionPerformed(ActionEvent e) {
            opacity += .03;
            if(opacity > 1) {
                opacity = 1;
                fadeTimer.stop();
                fadeTimer = null;
            }
            repaint();
        }
        public void paintComponent(Graphics g) {
            ((Graphics2D) g).setComposite(
                    AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                               opacity));
            g.setColor(getBackground());
            g.fillRect(0,0,getWidth(),getHeight());
        }
    }
}