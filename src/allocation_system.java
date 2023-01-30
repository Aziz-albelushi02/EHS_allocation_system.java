import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class allocation_system extends JFrame implements ActionListener {
    
    
    
    //JLabels
    private static JLabel activity;
    private static JLabel tutor;
    private static JLabel activityName;
    private static JLabel studentID;
    private static JLabel studentActivity;
    
    //JTextFields
    private static JTextField tutorName;
    private static JTextField activityText;
    private static JTextField activities;
    private static JTextField activitiesStu;
    private static JTextField studentDetails;
    
    //JButtons
    private static JButton addBtn;
    private static JButton addButton;
    private static JButton removeBtn;
    private static JButton submitBtn;
    
    //JPanels
    private static JPanel activityPanel;
    private static JPanel tutorPanel;
    private static JPanel studentPanel;
    
    //JTextArea
    private static JTextArea activityArea;
    
    //JTabbedPanes
    private static JTabbedPane tabbedPane;
    
    //File I/O
    String activitiesFile = "activities.txt";
    String studentFile = "students.txt";
    String activityStudent = "students-activities.txt";
    private static FileReader fileReader;
    private static FileWriter fileWriter;
    
    public allocation_system() throws FileNotFoundException, IOException {
        super("EHS Allocation System");
        createGUI();
    }
    
    private void createGUI() throws FileNotFoundException, IOException {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Activity Coordinator", createActivityPanel());
        tabbedPane.addTab("Tutor", createTutorPanel());
        tabbedPane.addTab("Student", createStudentPanel());
        tabbedPane.setFocusable(false);
        add(tabbedPane);
    }
    
    //creating the panel for the activity coordinator
    private JPanel createActivityPanel() throws FileNotFoundException, IOException {
        activityPanel = new JPanel();
        addAcLabelTextFieldButton(activityPanel);
        
        return activityPanel;
    }
    
    //creating the panel for the tutor
    private JPanel createTutorPanel() throws FileNotFoundException, IOException {
        tutorPanel = new JPanel();
        addTutLabelTextFieldButton(tutorPanel);
        
        return tutorPanel;
    }
    
    //creating the panel for the student
    private JPanel createStudentPanel() throws FileNotFoundException, IOException {
        studentPanel = new JPanel();
        addStuLabelTextFieldButton(studentPanel);
        
        return studentPanel;
    }
    
    private void addAcLabelTextFieldButton(JPanel activityPanel) throws FileNotFoundException, IOException {
        activity = new JLabel("Activity Name: ");
        activityPanel.add(activity, BorderLayout.CENTER);
        
        activities = new JTextField(50);
        activityPanel.add(activities, BorderLayout.CENTER);
        
        addBtn = new JButton("Add Activity");
        addBtn.addActionListener(this);
        activityPanel.add(addBtn, BorderLayout.SOUTH);
        
        removeBtn = new JButton("Remove Activity");
        removeBtn.addActionListener(this);
        activityPanel.add(removeBtn, BorderLayout.SOUTH);
        
        activityArea = new JTextArea(20,50);
        activityPanel.add(activityArea, BorderLayout.SOUTH);
        try {
            String textLine;
            fileReader = new FileReader("activities.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            while ((textLine = reader.readLine()) != null) {
                activityArea.read(reader, "activities.txt");
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }
    }
    
    private void addTutLabelTextFieldButton(JPanel tutorPanel) throws FileNotFoundException, IOException {
        tutor = new JLabel("Tutor Name: ");
        tutorPanel.add(tutor, BorderLayout.CENTER);
        
        tutorName = new JTextField(50);
        tutorPanel.add(tutorName, BorderLayout.CENTER);
        
        activityName = new JLabel("Activity Name: ");
        tutorPanel.add(activityName, BorderLayout.CENTER);
        
        activityText = new JTextField(50);
        tutorPanel.add(activityText, BorderLayout.CENTER);
        
        addButton = new JButton("Add Details");
        addButton.addActionListener(this);
        tutorPanel.add(addButton, BorderLayout.SOUTH);
        
        activityArea = new JTextArea(20, 50);
        tutorPanel.add(activityArea, BorderLayout.SOUTH);
        try {
            String textLine;
            fileReader = new FileReader("student-activities.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            while ((textLine = reader.readLine()) != null) {
                activityArea.read(reader, "student-activities.txt");
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }
    }
    
    private void addStuLabelTextFieldButton(JPanel studentPanel) throws FileNotFoundException, IOException {
        studentID = new JLabel("Student Details: ");
        studentPanel.add(studentID, BorderLayout.CENTER);
        
        studentDetails = new JTextField(50);
        studentPanel.add(studentDetails, BorderLayout.CENTER);
        
        studentActivity = new JLabel("Activity: ");
        studentPanel.add(studentActivity, BorderLayout.CENTER);
        
        activityText = new JTextField(50);
        studentPanel.add(activityText, BorderLayout.CENTER);
        
        submitBtn = new JButton("Submit Details");
        submitBtn.addActionListener(this);
        studentPanel.add(submitBtn, BorderLayout.SOUTH);
        
        activityArea = new JTextArea(20, 50);
        studentPanel.add(activityArea, BorderLayout.SOUTH);
        try {
            String textLine;
            fileReader = new FileReader("activities.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            while ((textLine = reader.readLine()) != null) {
                activityArea.read(reader, "activities.txt");
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (addBtn == e.getSource()) {
            try {
                fileWriter = new FileWriter("activities.txt", true);
                fileWriter.write(activities.getText());
                fileWriter.write(System.getProperty("line.separator"));
                fileWriter.close();
                JOptionPane.showMessageDialog(null, "Success");
                setVisible(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error");
            }
        }
        
        if (addButton == e.getSource()) {
            try {
                fileWriter = new FileWriter("activities.txt", true);
                fileWriter.write("Activity: " + activityText.getText() + " - " + "Tutor Name: " + tutorName.getText());
                fileWriter.write(System.getProperty("line.separator"));
                fileWriter.close();
                JOptionPane.showMessageDialog(null, "Success");
                setVisible(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error");
            }
        }
        
        if (submitBtn == e.getSource()) {
            try {
                fileWriter = new FileWriter("student-activities.txt", true);
                fileWriter.write("Activity: " + activityText.getText() + " Student: " + studentDetails.getText());
                fileWriter.write(System.getProperty("line.separator"));
                fileWriter.close();
                JOptionPane.showMessageDialog(null, "Success! This activity will occur in the main hall. The activity will start from 10:00am till 14:30pm.");
                setVisible(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error");
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            allocation_system ehs;
            try {
                ehs = new allocation_system();
                ehs.setLocationByPlatform(true);
                ehs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ehs.setSize(600, 600);
                ehs.setResizable(true);
                ehs.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(allocation_system.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
