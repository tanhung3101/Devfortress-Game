/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.model.*;
import devfortress.model.facade.DevFortressMain;
import devfortress.utility.*;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableModel;

/**
 *
 * @author SONY VAIO F115FM
 */
public class HiredDevListPanel extends JPanel {

    private JTable hiredDevTable;
    private JScrollPane scrollableTable;
    private TableModel tableModel;

    public HiredDevListPanel() {
        //create dev to display
        DevFortressMain.model.generateDeveloper();

        //List<Developer> devList = DevFortressMain.model.getDeveloperList();
        List<Developer> devList = DevFortressMain.model.getNewDeveloperList();

        String[] columnName = {"Developer ID", "Name", "Salary", "Skills (Level)", "Emotion Status", "Work Status"};

        tableModel = new NewTableModel(devList, null, null, columnName, "HiredDevList");
        hiredDevTable = new JTable(tableModel);

        //Set size of columns
        hiredDevTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        hiredDevTable.getColumnModel().getColumn(0).setMinWidth(90);
        hiredDevTable.getColumnModel().getColumn(1).setMinWidth(100);
        hiredDevTable.getColumnModel().getColumn(2).setMinWidth(100);
        hiredDevTable.getColumnModel().getColumn(3).setMinWidth(385);
        hiredDevTable.getColumnModel().getColumn(4).setMinWidth(100);
        hiredDevTable.getColumnModel().getColumn(5).setMinWidth(100);

        scrollableTable = new JScrollPane(hiredDevTable);

        setLayout(new BorderLayout());
        add(scrollableTable);
    }
}
