import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

//source from orecal + stack flow
public class RowColorRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {

        Component cell = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        String priority = table.getValueAt(row, 2).toString();
        // cloring method
        if (priority.equals("High")) {
            cell.setBackground(Color.RED);

        } else if (priority.equals("Middle")) {
            cell.setBackground(Color.YELLOW);

        } else {
            cell.setBackground(Color.GREEN);
        }

        return cell;
    }
}