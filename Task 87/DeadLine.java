
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class DeadLine {

    public static void checkingDate(Task t) {
        if (!t.getDeadLineDate().isEmpty()) {
            LocalDate TaskDate = LocalDate.parse(t.getDeadLineDate());
            LocalDate Today = LocalDate.now();

            if (Today.isAfter(TaskDate) && !t.isDone()) {
                JOptionPane.showMessageDialog(null,
                        "DeadLine Has Passed",
                        "Warning Message",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
