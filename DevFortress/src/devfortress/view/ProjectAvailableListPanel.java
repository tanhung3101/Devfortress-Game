/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.model.*;
import devfortress.model.facade.DevFortressMain;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Nop
 */
public class ProjectAvailableListPanel extends JPanel {

    private JTable devTable;
    private JScrollPane scrollableTable;
    private TableModel tableModel;
    private int selectRow;

    public ProjectAvailableListPanel() {
        DevFortressMain.model.generateNewProjectList();
        List<Project> projecList = DevFortressMain.model.getNewProjectList();

        String[] columnName = {"Project Name", "Price", "Duration"};

        tableModel = new devfortress.utility.NewTableModel(null, projecList, null, columnName, "ProjectList");
        devTable = new JTable(tableModel);

        devTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        devTable.getColumnModel().getColumn(0);
        devTable.getColumnModel().getColumn(1).setMinWidth(100);
        devTable.getColumnModel().getColumn(2).setMinWidth(100);

        devTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectRow = devTable.rowAtPoint(e.getPoint());

                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {

                    // Double click to open a new popup about that project
                    ProjectPanel a = new ProjectPanel();
                }



            }
        });

        scrollableTable = new JScrollPane(devTable);
        setLayout(new BorderLayout());
        add(scrollableTable);


    }

    class ProjectPanel extends JDialog {

        private JLabel idTF = new JLabel();
        private JLabel priceTF = new JLabel();
        private JLabel durationTF = new JLabel();
        private JLabel maxFuntionPointTF = new JLabel();
        private JLabel levelTF = new JLabel();
        private JLabel normalSkillTF = new JLabel();
        private JLabel hideSkillTF = new JLabel();

        public ProjectPanel() {
            super();

            setTitle("Project Information");
            add(getProjectPanel());
            pack();
            setVisible(true);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        }

        private JPanel getProjectPanel() {

            JPanel showPanel = new JPanel(new GridLayout(6, 2, 10, 10));

            String selectedProject = (String) tableModel.getValueAt(selectRow, 0);

            idTF.setText(selectedProject);
            List<Project> newProjectList = DevFortressMain.model.getNewProjectList();

            Project newProject = new Project();

            for (Project pro : newProjectList) {
                if (pro.getID().equalsIgnoreCase(selectedProject)) {
                    newProject = pro;
                }
            }

            String skillTree = "";
            for (String str : newProject.getNormalFunctionPoints().keySet()) {


                skillTree += str.toString() + " (" + newProject.getNormalFunctionPoints().get(str) + "); ";

            }
            for (String str : newProject.getHiddenFunctionalAreas().keySet()) {


                skillTree += str.toString() + "( ? )";

            }

            levelTF.setText(String.valueOf(newProject.getLevel()));
            priceTF.setText(String.valueOf(newProject.getPrice()));
            maxFuntionPointTF.setText(String.valueOf(newProject.getMaxFunctionPoint()));
            //normalSkillTF.setText(newProject.getNormalFunctionPoints().toString());
            normalSkillTF.setText(skillTree);

            showPanel.add(new JLabel("ID Project:"));
            showPanel.add(idTF);
            showPanel.add(new JLabel("Level:"));
            showPanel.add(levelTF);
            showPanel.add(new JLabel("Price:"));
            showPanel.add(priceTF);
            showPanel.add(new JLabel("Max Function Point:"));
            showPanel.add(maxFuntionPointTF);
            showPanel.add(new JLabel("Function Areas:"));
            showPanel.add(normalSkillTF);
            
            return showPanel;
        }
    }
}
