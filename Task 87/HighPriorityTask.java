import javax.swing.JOptionPane;

//extends from Task
public class HighPriorityTask extends Task {
    // alertMassege var
    public static String alertMassege;

    public HighPriorityTask(String title, String DeadlineDate) {
        super(title, "High", DeadlineDate);
        // JOptionPane
        JOptionPane.showMessageDialog(null, "High Priority Task!");
    }
    // the obj creating in the main

}
