/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.model.SignedProject;
import devfortress.model.facade.DevFortressMain;
import devfortress.utility.*;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author HP ENVY 15
 */
public class SignedProjectListPanel extends JPanel {

    private JTable projectTable;
    private JScrollPane scrollAbleTable;
    private NewTableModel tableModel;

    public SignedProjectListPanel() {
        List<SignedProject> proList = DevFortressMain.model.getAcceptedProjectList();
        //proList.add(DevFortressMain.model.generateProject());
        String[] columnName = {"Name", "Price", "Developers", "Time Remain"};

        tableModel = new NewTableModel(null, null, proList, columnName, "SignedProjectList");
        projectTable = new JTable(tableModel);

        //set size of columns
        projectTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        projectTable.getColumnModel().getColumn(0).setMinWidth(100);
        projectTable.getColumnModel().getColumn(1).setMinWidth(100);
        projectTable.getColumnModel().getColumn(2).setMinWidth(100);
        projectTable.getColumnModel().getColumn(3).setMinWidth(100);

        scrollAbleTable = new JScrollPane(projectTable);
        setLayout(new BorderLayout());
        add(scrollAbleTable);
    }
}
