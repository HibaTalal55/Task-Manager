
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.*;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.time.LocalDate;

public class main extends JFrame implements ActionListener {
    public static JTextField UserName;
    public static JPasswordField Password;
    public static JButton Button;
    public static JLabel redLabel;
    public static JFrame frame1;
    public static JTextField TaskName;
    public static JButton objButton;
    public static ArrayList<Task> taskManager = new ArrayList<Task>();
    public static JTable table;
    public static DefaultTableModel model;
    public static JButton doneButton;
    public static JButton deleButton;
    public static JTable table2;
    public static DefaultTableModel model2;
    public static JComboBox<String> comboBoxFilter;
    public static JButton deletAllDone;
    public static JTextField DeadlineDate;

    public static void main(String[] args) {
        // first frame(Login Frame)
        frame1 = new JFrame();
        frame1.setTitle("LoginFrame");
        frame1.setSize(1000, 1000);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Label=>Task Manger as a text
        JLabel label1 = new JLabel();
        label1.setText("Task Manger");
        // make it horizontal at the center
        label1.setHorizontalAlignment(JLabel.CENTER);
        // edit the label text font size
        label1.setFont(new Font("Arial", Font.PLAIN, 25));
        // add it at the north of the frame
        frame1.add(label1, BorderLayout.NORTH);
        // adding textfild+passwordfield to check the user name and pass(name =
        // admin/pass=1234)
        JLabel label2 = new JLabel("Enter Your Name: ");
        UserName = new JTextField(15);
        JLabel label3 = new JLabel("Enter Your Password: ");
        Password = new JPasswordField(15);
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(label2);
        panel.add(UserName);
        panel.add(label3);
        panel.add(Password);
        // adding the login button and a redflag if the user info are wrong
        Button = new JButton("Login");
        redLabel = new JLabel();
        redLabel.setForeground(Color.RED);
        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(panel);
        panel2.add(Button);
        panel2.add(redLabel);
        // panel1=labelEnterYourName+TextField+labelEnterYourPass+passWordField
        // all the comp in one panel=panel2(panel1+button+redLabel)
        frame1.add(panel2, BorderLayout.CENTER);
        // button action= closing the login frame and opning the task manger frame
        Button.addActionListener(new main());
        frame1.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        // Login Button Action if the user info are right
        if (UserName.getText().equals("admin") &&
                String.valueOf(Password.getPassword()).equals("1234")) {
            // closing for frame 1 + opning frame 2
            frame1.dispose();
            // 2nd frame(Task Maneger)
            JFrame frame2 = new JFrame();
            frame2.setTitle("Task BoardFrame");
            frame2.setSize(1000, 1000);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // top panel(JTextFile to enter your task)+(ComboBox to rate your task)+(adding
            // button to add the new task as an obj)
            JLabel label4 = new JLabel("Enter your task");
            TaskName = new JTextField(15);
            JComboBox<String> comboBoxRating = new JComboBox<>();
            comboBoxRating.addItem("High");
            comboBoxRating.addItem("Middle");
            comboBoxRating.addItem("Low");
            objButton = new JButton("Add Task");
            objButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Add Task button action(Two Cases first one if it's high+second one if it's
                    // low or middle)
                    String title = TaskName.getText().trim();
                    String priority = (String) comboBoxRating.getSelectedItem();

                    String date = DeadlineDate.getText().trim();

                    if (!title.isEmpty()) {

                        if (!date.isEmpty()) {
                            try {
                                LocalDate.parse(date);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame2,
                                        "Enter date like 2026-10-15",
                                        "Wrong Date",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }

                        // first case
                        if (priority.equals("High")) {
                            // obj from "HighPriorityTask" class it will give an JOptionpane because it's
                            // high
                            HighPriorityTask newObjTask = new HighPriorityTask(title, date);
                            taskManager.add(newObjTask);
                        } else {
                            // second case it will be a normal obj without JOptionpane
                            Task newObjTask = new Task(title, priority, date);
                            taskManager.add(newObjTask);
                        }

                        DeadLine.checkingDate(taskManager.get(taskManager.size() - 1));

                        refreshTable();// method
                        TaskName.setText("");
                        DeadlineDate.setText("");
                    }
                }
            });
            // adding the deadlinedate
            DeadlineDate = new JTextField(10);
            JPanel topPanel = new JPanel(new FlowLayout());
            topPanel.add(label4);
            topPanel.add(TaskName);
            topPanel.add(comboBoxRating);
            topPanel.add(DeadlineDate);
            topPanel.add(objButton);
            frame2.add(topPanel, BorderLayout.NORTH);
            // center panel(A Tabel)
            model = new DefaultTableModel();
            model.addColumn("#");
            model.addColumn("Task");
            model.addColumn("Priority");
            model.addColumn("Case situation");
            model.addColumn("Deadline Date");
            table = new JTable(model);
            // the code in RowColorRender Class/it's a bounse
            table.setDefaultRenderer(Object.class, new RowColorRenderer());
            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.add(table);
            frame2.add(centerPanel, BorderLayout.CENTER);
            // Buttom panel(task done button)+(delete button)+(filter comboBox)+(x/y)
            // Task Done Button
            doneButton = new JButton("Task Done");
            // Action for Task Done
            doneButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int row = table.getSelectedRow();
                    if (row == -1) {
                        JOptionPane.showMessageDialog(frame2, "Choose a task");
                        return;
                    }
                    taskManager.get(row).setDone(true);
                    // Don't forget To use refreshTable method
                    refreshTable();
                }
            });
            // Delete Done Button
            deleButton = new JButton("Delete Task");
            // Action for Delete Task
            deleButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int row = table.getSelectedRow();
                    if (row == -1) {
                        JOptionPane.showMessageDialog(frame2, "Choose a task");
                        return;
                    }
                    int dele = JOptionPane.showConfirmDialog(frame2,
                            "Are You Sure Want To Delete ", "Delete", JOptionPane.YES_NO_OPTION);
                    if (dele == JOptionPane.YES_OPTION) {
                        taskManager.remove(row);
                        // Don't forget To use refreshTable method
                        refreshTable();
                    }

                }
            });
            // Filter ComboBox
            comboBoxFilter = new JComboBox<>();
            // 1.All+2.Finished+3.Non Finished
            comboBoxFilter.addItem("All");
            comboBoxFilter.addItem("Finished");
            comboBoxFilter.addItem("Non Finished");
            comboBoxFilter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // three cases insied the refreshTable method
                    refreshTable();
                }
            });
            // JTabel for done Tasks and all Tasks num#
            model2 = new DefaultTableModel();
            model2.addColumn("X");
            model2.addColumn("y");
            table2 = new JTable(model2);
            // the action is insied the refreshTable
            JPanel bottomPanel = new JPanel(new FlowLayout());
            bottomPanel.add(doneButton);
            bottomPanel.add(deleButton);
            bottomPanel.add(comboBoxFilter);
            bottomPanel.add(table2);
            // Delete all button / it's bonus
            deletAllDone = new JButton("Delete All Done Tasks");

            deletAllDone.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    for (int i = 0; i < taskManager.size(); i++) {

                        Task t = taskManager.get(i);

                        if (t.isDone()) {
                            taskManager.remove(i);
                            i--;
                        }
                    }

                    refreshTable();
                }
            });
            bottomPanel.add(deletAllDone);

            frame2.add(bottomPanel, BorderLayout.SOUTH);

            frame2.setVisible(true);

        } else {
            // in case the info user are wrong
            redLabel.setText("WRONG DATA");
        }

    }

    // refreshTabel method
    public static void refreshTable() {

        model.setRowCount(0);

        String TaskCase = (String) comboBoxFilter.getSelectedItem();
        for (int i = 0; i < taskManager.size(); i++) {

            Task t = taskManager.get(i);
            // fisrt case to show them all

            if (TaskCase.equals("All")) {

                model.addRow(new Object[] {
                        i + 1,
                        t.title,
                        t.priority,
                        t.isDone() ? "Done" : "Still Not Done",
                        t.getDeadLineDate()
                });
                // second case to show only "Done"
            } else if (TaskCase.equals("Finished")) {

                if (t.isDone()) {

                    model.addRow(new Object[] {
                            i + 1,
                            t.title,
                            t.priority,
                            "Done",
                            t.getDeadLineDate()
                    });
                }
                // third case to only shows "Still Not Done"
            } else if (TaskCase.equals("Non Finished")) {

                if (!t.isDone()) {

                    model.addRow(new Object[] {
                            i + 1,
                            t.title,
                            t.priority,
                            "Still Not Done", t.getDeadLineDate()
                    });
                }
            }
        }
        int x = taskManager.size();
        int y = 0;

        for (int i = 0; i < taskManager.size(); i++) {
            Task t = taskManager.get(i);

            if (t.isDone()) {
                y++;
            }
        }
        model2.setRowCount(0);
        model2.addRow(new Object[] { x, y });
    }

}
