/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.model.*;
import devfortress.model.facade.DevFortressMain;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Mr Dat
 */
public class MainView extends JFrame implements Observer {

    private JTabbedPane tabPane = new JTabbedPane();
    private JPanel devListPanel = new NewDevListPanel();
    private JPanel signedProjectListPanel = new SignedProjectListPanel();
    private JPanel hiredDevListPanel = new HiredDevListPanel();
    private JPanel projectListPanel =new ProjectAvailableListPanel();

    public MainView() {
        setTitle("DevFortress");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabPane.add("New Developers", devListPanel);
        tabPane.add("Hired Developers", hiredDevListPanel);
        tabPane.add("Project List", projectListPanel);
        //tabPane.add("Signed Projects", signedProjectListPanel);

        setLayout(new BorderLayout());
        add(tabPane, BorderLayout.CENTER);

        //Test
        JButton a = new JButton("Test");
        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DevFortressMain.model.test();
                tabPane.add("Signed Projects", signedProjectListPanel);
            }
        });
        tabPane.add("Test", a);

        setVisible(true);
    }

    public void update(Observable o, Object arg) {
    }
}
