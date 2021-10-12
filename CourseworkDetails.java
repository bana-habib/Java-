package coursework2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

/**
 *
 * @author bh6779k
 */
public class CWDetails extends JFrame implements ActionListener, KeyListener {

    CommonCode cc = new CommonCode();
    JPanel pnl = new JPanel(new BorderLayout());
    JTextArea txtDisplayNotes = new JTextArea();
    JTextArea txtNewNote = new JTextArea();


    // Place the global data structures here, e.g. :-
    //ArrayList<String> tmp = new ArrayList<>();
    //ArrayList<className> cls = new ArrayList<>();
    public CWDetails() {
        model();
        view();
        controller();
    }

    private void model() {
        // This is the logic of the program.

    }

    private void view() {
        
        //meanue bar 
        JMenuBar menuBar;
        JMenu fyle;

        JToolBar toolBar = new JToolBar();
        Font fnt = new Font("Georgia", Font.PLAIN, 36);

        // Setting up the MenuBar
        menuBar = new JMenuBar();
        fyle = new JMenu("File");
        fyle.setToolTipText("File tasks");
        fyle.setFont(fnt);

        JMenuItem mnuItem = null;

        mnuItem = makeMenuItem("New", "New", "Create a new something or other", fnt);
        fyle.add(mnuItem);

        fyle.addSeparator();

        mnuItem = makeMenuItem("Close", "Close", "Close something or other", fnt);
        fyle.add(mnuItem);

        menuBar.add(fyle);

        mnuItem = makeMenuItem("Exit", "Exit", "Close this program", fnt);
        menuBar.add(mnuItem);

        setJMenuBar(menuBar);

        setLayout(new BorderLayout());

        // Use setSize and setLocationRelative for a specific 
        // size of window or setExtendedState to fill the screen.
        //
        //setSize(500, 500);
        //setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setTitle("This is the title");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel lft = new JPanel();
        lft.setLayout(new FlowLayout());
        JLabel lf = new JLabel("<html><body>West</body></html>");
        lf.setFont(fnt);
        lft.add(lf);
        add(lft, BorderLayout.WEST);

        //adding center panel
        //where course spec is shown 
        JPanel cen = new JPanel();
        cen.setLayout(new BoxLayout(cen, BoxLayout.Y_AXIS));
        cen.setBorder(BorderFactory.createLineBorder(Color.black));
        txtDisplayNotes.setFont(fnt);
        cen.add(txtDisplayNotes);

        add(cen, BorderLayout.CENTER);

        //adding east panel 
        //to set deadline date 
        JPanel pnlEast = new JPanel();
        pnlEast.setLayout(new BoxLayout(pnlEast, BoxLayout.Y_AXIS));
        pnlEast.setBorder(BorderFactory.createLineBorder(Color.black));
        
        txtNewNote.setFont(fnt);
        pnlEast.add(txtNewNote);
        
        add(pnlEast, BorderLayout.EAST);
        
        //under panel 

        JPanel bot = new JPanel();
        bot.setLayout(new FlowLayout());
        JLabel copy = new JLabel("<html><body>Copyright (c) Willow & Bana</body></html>");
        copy.setFont(fnt);
        bot.add(copy);
        add(bot, BorderLayout.SOUTH);

        setVisible(true);

        // Setting up the ButtonBar
        JButton button = null;
        button = makeNavigationButton("Create", "Return2Notes",
                "Return to the Notes window",
                "Notes");
        toolBar.add(button);
        button = makeNavigationButton("closed door", "Close",
                "Close this thingy",
                "Close");
        toolBar.add(button);
        toolBar.addSeparator();
        button = makeNavigationButton("exit", "Exit",
                "Exit from this program",
                "Exit");
        toolBar.add(button);

        add(toolBar, BorderLayout.NORTH);
    }

    private void controller() {
        // This is the logic of the program.

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("Return2Notes".equals(ae.getActionCommand())) {
            Coursework2 cw = new Coursework2();
        }
        if ("New".equals(ae.getActionCommand())) {
            JOptionPane.showMessageDialog(this, "New clicked");
        }
        if ("Close".equals(ae.getActionCommand())) {
            JOptionPane.showMessageDialog(this, "Close clicked");
        }
        if ("Exit".equals(ae.getActionCommand())) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("keyTyped has not been coded yet.");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("keyPressed has not been coded yet.");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("keyReleased has not been coded yet.");
    }

    protected JMenuItem makeMenuItem(String txt,
            String actionCommand,
            String toolTipText,
            Font fnt) {

        JMenuItem mnuItem = new JMenuItem();
        mnuItem.setFont(fnt);
        mnuItem.setText(txt);
        mnuItem.setToolTipText(toolTipText);
        mnuItem.setActionCommand(actionCommand);
        mnuItem.addActionListener(this);

        return mnuItem;
    }

    protected JButton makeNavigationButton(String imageName,
            String actionCommand,
            String toolTipText,
            String altText) {

        //Look for the image.
        String imgLocation = cc.appDir + "\\icons\\"
                + imageName
                + ".png";

        //Create and initialize the button.
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);

        File fyle = new File(imgLocation);
        if (fyle.exists() && !fyle.isDirectory()) {
            // image found
            Icon img;
            img = new ImageIcon(imgLocation);
            button.setIcon(img);
        } else {
            // image NOT found
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }

        return button;
    }
}
