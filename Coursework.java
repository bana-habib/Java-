package coursework2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author bh6779k
 */
public class Coursework2 extends JFrame implements ActionListener, KeyListener {

    CommonCode cc = new CommonCode();
    JPanel pnl = new JPanel(new BorderLayout());
    JTextArea txtNewNote = new JTextArea();
    JTextArea txtDisplayNotes = new JTextArea();
    JTextField search = new JTextField();
    ArrayList<String> note = new ArrayList<>();
    ArrayList<String> course = new ArrayList<>();
    JComboBox courseList = new JComboBox();
    String crse = "";
    AllNotes allNotes = new AllNotes(crse);

    //varaible for creating folder
    //Help from https://www.youtube.com/watch?v=WUJKivDbbAo
    String folderPath = "";

    public static void main(String[] args) {
        //required for coursework
        //JOptionPane.showMessageDialog(null, "Andy Wicks - wa02");
        Coursework2 prg = new Coursework2();

    }

    //Using MVC
    public Coursework2() {
        model();
        view();
        controller();
    }

    private void view() {
        Font fnt = new Font("Georgia", Font.PLAIN, 24);
        JMenuBar menuBar = new JMenuBar();

        JMenu kurs = new JMenu();
        kurs = new JMenu("Course");
        kurs.setToolTipText("Course tasks");
        kurs.setFont(fnt);

        kurs.add(makeMenuItem("Add", "AddCourse", "Add a new course.", fnt));
        kurs.add(makeMenuItem("Amend", "AmendCourse", "Amend an existing course.", fnt));
        kurs.add(makeMenuItem("Delete", "DeleteCourse", "Delete an existing course.", fnt));
        kurs.addSeparator();
        kurs.add(makeMenuItem("Coursework Screen", "Coursework", "Go to the coursework screen.", fnt));
        menuBar.add(kurs);

        //JPanel pnlTop = new JPanel();
        JMenu note = new JMenu();

        note = new JMenu("Note");
        note.setToolTipText("Note tasks");
        note.setFont(fnt);

        note.add(makeMenuItem("New", "NewNote", "Create a new note.",
                fnt));
        note.addSeparator();
        note.add(makeMenuItem("Close", "Close", "Clear the current note.",
                fnt));

        menuBar.add(note);

        JMenu searchMenu = new JMenu();
        searchMenu = new JMenu("Search");
        //adds help message when over button
        searchMenu.setToolTipText("Search notes");
        searchMenu.setFont(fnt);

        searchMenu.add(makeMenuItem("By Course", "SearchbyCourse", "Search by course", fnt));
        searchMenu.add(makeMenuItem("By Date", "SearchbyDate", "Search by course", fnt));
        menuBar.add(searchMenu);

        menuBar.add(makeMenuItem("Exit", "Exit", "Close this program",
                fnt));

        // this will add each course to the combobox
        for (String crse : course) {
            courseList.addItem(crse);
        }
        courseList.setFont(fnt);
        courseList.setMaximumSize(courseList.getPreferredSize());
        courseList.addActionListener(this);
        courseList.setActionCommand("Course");
        menuBar.add(courseList);

        this.setJMenuBar(menuBar);

        JToolBar toolBar = new JToolBar();

        JButton button = null;
        button = makeButton("Document", "Coursework",
                "Open the coursework window",
                "Coursework");
        button = makeButton("Create", "NewNote",
                "Create a new note.",
                "New");
        toolBar.add(button);

        button = makeButton("closed door", "Close",
                "Close this note.",
                "Close");
        toolBar.add(button);
        toolBar.addSeparator();

        button = makeButton("exit", "Exit",
                "Exit from this program.",
                "Exit");
        toolBar.add(button);
        toolBar.addSeparator();

        //forces everything after it to the right 
        toolBar.add(Box.createHorizontalGlue());

        search.setMaximumSize(new Dimension(6900, 50));
        search.setFont(fnt);
        toolBar.add(search);
        toolBar.addSeparator();
        button = makeButton("search", "SearchKeyword",
                "Search for this text.",
                "Search");
        toolBar.add(button);

        add(toolBar, BorderLayout.NORTH);

        JPanel pnlWest = new JPanel();
        pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
        pnlWest.setBorder(BorderFactory.createLineBorder(Color.black));

        //JButton btnShowText = new JButton("Show note");
        //btnShowText.setActionCommand("NewNote");
        //btnShowText.addActionListener(this);
        //pnlWest.add(btnShowText);
        //pnlWest.add(txtShowText);
        txtNewNote.setFont(fnt);
        pnlWest.add(txtNewNote);

        JButton btnAddNote = new JButton("Add note");
        btnAddNote.setActionCommand("NewNote");
        btnAddNote.addActionListener(this);
        pnlWest.add(btnAddNote);

        add(pnlWest, BorderLayout.WEST);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Coursework - Bana Habib");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel cen = new JPanel();
        //cen.setLayout(new FlowLayout());
        cen.setLayout(new BoxLayout(cen, BoxLayout.Y_AXIS));
        cen.setBorder(BorderFactory.createLineBorder(Color.black));
        txtDisplayNotes.setFont(fnt);
        cen.add(txtDisplayNotes);

        add(cen, BorderLayout.CENTER);

        setVisible(true); // needed to ensure that the itmens can be seen.

    }

    private void model() {
        //adding courses from what user types in 
        //Help from Isobel Storer - 000973390
        course = cc.readTextFile("Courses.txt");

        crse = course.get(0);

        //course.add("COMP1753");
        //crse = course.get(0);
        //Note nt - new Note();
        //nt.noteID = 1;
        //nt.dayte = getDateAndTime();
        //nt.course = crse;
        //note.add("Arrays are of fixed length and are inflexible.");
        //allNotes.allNotes.add(nt);
        //nt = new Note();
        //nt.noteID = 2;
        //nt.dayte = getDateAndTime();
        //nt.course = crse;
        //note.add("ArraysList can  be added to and items can be deleted.");
        //allNotes.allNotes.add(nt);
    }

    private void controller() {
        addAllNotes();
    }

    protected JMenuItem makeMenuItem(
            String txt,
            String actionCommand,
            String toolTipText,
            Font fnt) {

        JMenuItem mnuItem = new JMenuItem();
        mnuItem.setText(txt);
        mnuItem.setActionCommand(actionCommand);
        mnuItem.setToolTipText(toolTipText);
        mnuItem.setFont(fnt);
        mnuItem.addActionListener(this);

        return mnuItem;

    }

    protected JButton makeButton(
            String imageName,
            String actionCommand,
            String toolTipText,
            String altText) {

        //Create and intialize the button.
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);

        //look for the image
        String imgLocation = System.getProperty("user.dir")
                + "\\icons\\"
                + imageName
                + ".png";

        File fyle = new File(imgLocation);
        if (fyle.exists() && !fyle.isDirectory()) {
            //image found
            Icon img;
            img = new ImageIcon(imgLocation);
            button.setIcon(img);
        } else {

            //image not found:
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }
        return button;
    }

    private void createDirectory() {
        //defining a path
        Path xPath = Paths.get(folderPath);
        try {
            //creating directory
            Files.createDirectory(xPath);
        } catch (IOException e) {
            //error message
            System.out.println("Couldn't create directory");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if ("Coursework".equals(ae.getActionCommand())) {
            CWDetails cw = new CWDetails();
        }

        if ("NewNote".equals(ae.getActionCommand())) {
            addNote(txtNewNote.getText());
            txtNewNote.setText("");
        }

        if ("Course.".equals(ae.getActionCommand())) {
            crse = courseList.getSelectedItem().toString();
            System.out.println(crse);
        }
        //opens pop-up window to let user add new course when button is pressed
        if ("AddCourse".equals(ae.getActionCommand())) {
            String courseName = JOptionPane.showInputDialog("Enter Course Name:");
            System.out.println(courseName);
            //telling where to save folder 
            //Help from https://www.youtube.com/watch?v=WUJKivDbbAo
            folderPath = "G:\\Banas Code\\FF2\\Coursework2" + courseName;
            createDirectory();
            try {
                addCourse(courseName);
            } catch (IOException ex) {
            }
        }
        //Creates pop up window for user to input course to search
        if("SearchbyCourse".equals(ae.getActionCommand())) {
            String courseName = JOptionPane.showInputDialog("Enter Course Name:");
            System.out.println(courseName);            
        }
        //creates pop up window for user to input 
        if("SearchbyDate".equals(ae.getActionCommand())) {
            String courseName = JOptionPane.showInputDialog("Enter Date:");
            System.out.println(courseName);            
        }

        if ("SearchKeyword".equals(ae.getActionCommand())) {
            String lyst = allNotes.searchAllNotesByKeyword("", 0, search.getText());
            txtDisplayNotes.setText(lyst);

        }

        if ("Exit".equals(ae.getActionCommand())) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("Key typed not coded yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("Key pressed Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println(" Key Released Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addAllNotes() {
        String txtNotes = "";

        for (Note n : allNotes.getAllNotes()) {
            txtNotes += n.getNote() + "\n";
        }

        txtDisplayNotes.setText(txtNotes);
    }

    private void addNote(String text) {
        allNotes.addNote(allNotes.getMaxID(), crse, text);
        addAllNotes();
    }

    //Letting user add courses 
    private void addCourse(String courseName) throws IOException {
        //reads the text 
        course = cc.readTextFile("Courses.txt");
        course.add(courseName);

        cc.writeTextFile("Courses.txt", course);
        courseList.addItem(courseName);
    }

    private String getDateAndTime() {
        String UK_DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";
        String ukDateAndTime;
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat uksdf = new SimpleDateFormat(UK_DATE_FORMAT_NOW);
        ukDateAndTime = uksdf.format(cal.getTime());

        return ukDateAndTime;
    }

}
