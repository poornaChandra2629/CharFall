import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author IDEA Developers
 */
public class CharFall extends JFrame implements KeyListener{

    public static CharFall instance;
    public int width;
    public int height;
    
    public Random random = new Random();
    private String[] chars = {"P","O","O","R","N","A"};
    private String authorString = "by Poorna Chandra";
    
    private JPanel mainPanel;
    private JLabel authorLabel;
    
    public CharFall(){
        instance = this;
        this.loadView();
    }
    
    private void loadView(){
        mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.BLACK);
        this.add(mainPanel);
        this.addKeyListener(this);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);
        this.setVisible(true);
    }
    
    public void startRain(){
        width = getSize().width;
        height = getSize().height;
       
        
        authorLabel = new JLabel(authorString);
        authorLabel.setBackground(Color.red);
        authorLabel.setForeground(Color.GREEN);
        authorLabel.setFont(new Font("monospace", Font.BOLD, 30));
        Rectangle rec = new Rectangle();
        rec.width = 350;
        rec.height = 50;
        rec.x = width - rec.width;
        rec.y = height - rec.height-20;
        authorLabel.setBounds(rec);
        
        this.mainPanel.add(authorLabel);
        this.mainPanel.invalidate();
        
        
        new Thread(() -> {
            while(true){
                try {
                    int charIndex = random.nextInt(chars.length);
                    int xPos = random.nextInt(width);
                    int yPos = 0;
                    int randomSpeed = random.nextInt(20) + 8;
                    
                    MLabel mLabel1 = new MLabel(chars[charIndex], xPos, yPos, randomSpeed);
                    charIndex = random.nextInt(chars.length);
                    yPos+=20;
                    MLabel mLabel2 = new MLabel(chars[charIndex], xPos, yPos, randomSpeed);
                    charIndex = random.nextInt(chars.length);
                    yPos+=20;
                    MLabel mLabel3 = new MLabel(chars[charIndex], xPos, yPos, randomSpeed);
                    
                    mainPanel.add(mLabel1);
                    mainPanel.add(mLabel2);
                    mainPanel.add(mLabel3);
                    mainPanel.invalidate();
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    System.err.println(ex);
                }
            }
        }).start();
    }
    
    public static void main(String[] args) {
        new CharFall().startRain();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
}