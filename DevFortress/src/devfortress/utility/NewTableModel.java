/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.utility;

import devfortress.model.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mr Dat
 */
public class NewTableModel extends AbstractTableModel {

    private List<Developer> devList;
    private List<Project> projectList;
    private List<SignedProject> signedProjectList;
    private String[] columnName;
    private String tableType;

    public NewTableModel(List<Developer> devList, List<Project> projectList, List<SignedProject> signedProjectList, String[] columnName, String tableType) {
        this.devList = devList;
        this.projectList = projectList;
        this.signedProjectList = signedProjectList;
        this.columnName = columnName;
        this.tableType = tableType;
    }

    public int getColumnCount() {
        if (tableType.equals("NewDevList")) {
            return 4;
        } else if (tableType.equals("SignedProjectList")) {
            return 4;
        } else if (tableType.equals("HiredDevList")) {
            return 6;
        } else if (tableType.equals("ProjectList")) {
            return 3;
        }
        return 0;
    }

    public int getRowCount() {
        try {
            if (tableType.equals("NewDevList") || tableType.equals("HiredDevList")) {
                return devList.size();
            } else if (tableType.equals("SignedProjectList")) {
                return signedProjectList.size();
            } else if (tableType.equals("ProjectList")) {
                return projectList.size();
            }
            return 0;
        } catch (Exception ex) {
            return 0;
        }
    }

    public String getColumnName(int columnIndex) {
        return columnName[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (tableType.equals("NewDevList")) {
            Developer dev = devList.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return dev.getID();
                case 1:
                    return dev.getName();
                case 2:
                    return "$ " + dev.getSalary();
                case 3:
                    String skillTree = "";
                    for (Skill skill : dev.getSkillMap().values()) {
                        skillTree += skill.getSkillName() + " (" + skill.getCurrentLevel() + ")  ,   ";
                    }
                    return skillTree;
                default:
                    return null;
            }
        } else if (tableType.equals("SignedProjectList")) {
            SignedProject pro = signedProjectList.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return pro.getID();
                case 1:
                    return "$ " + pro.getPrice();
                case 2:
                    String name = "";
                    System.out.println(pro.getDevelopers().size());
                    for (Developer developer : pro.getDevelopers()) {
                        name += developer.getName() + "; ";
                    }
                    return name;
                case 3:
                    return pro.getProcess() + "/" + pro.getDuration();
                default:
                    return null;
            }
        } else if (tableType.equals("HiredDevList")) {
            Developer dev = devList.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return dev.getID();
                case 1:
                    return dev.getName();
                case 2:
                    return "$ " + dev.getSalary();
                case 3:
                    String skillTree = "";
                    for (Skill skill : dev.getSkillMap().values()) {
                        skillTree += skill.getSkillName() + " (" + skill.getCurrentLevel() + ")  ,   ";
                    }
                    return skillTree;
                case 4:
                    if (dev.getEmotionPoint() > 5) {
                        return "Happy";
                    } else {
                        return "Unhappy";
                    }
                case 5:
                    return dev.getCurrentProject();
                default:
                    return null;
            }
        } else if (tableType.equals("ProjectList")) {
            Project proj = projectList.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return proj.getID();
                case 1:
                    return "$" + proj.getPrice();
                case 2:
                    return proj.getDuration() + " Weeks";

                default:
                    return null;
            }
        }
        return null;
    }
}
