/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.model.*;
import devfortress.model.facade.DevFortressMain;
import devfortress.utility.*;
import java.awt.BorderLayout;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Mr Dat
 */
public class NewDevListPanel extends JPanel {

    private JTable devTable;
    private JScrollPane scrollableTable;
    private NewTableModel tableModel;

    public NewDevListPanel() {
        DevFortressMain.model.generateNewDevList();
        List<Developer> devList = DevFortressMain.model.getNewDeveloperList();

        String[] columnName = {"Developer ID", "Name", "Salary", "Skills (Level)"};

        tableModel = new NewTableModel(devList, null, null, columnName, "NewDevList");
        devTable = new JTable(tableModel);

        //Set size of columns
        devTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        devTable.getColumnModel().getColumn(0).setMinWidth(90);
        devTable.getColumnModel().getColumn(1).setMinWidth(100);
        devTable.getColumnModel().getColumn(2).setMinWidth(100);
        devTable.getColumnModel().getColumn(3).setMinWidth(385);

        scrollableTable = new JScrollPane(devTable);

        setLayout(new BorderLayout());
        add(scrollableTable);
    }
}
