/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.utility;

import devfortress.model.*;
import devfortress.model.facade.DevFortressMain;
import java.util.*;

/**
 *
 * @author Mr Dat
 */
public class GenerateIDSystem {

    private String type;
    private List<String> developerID = new ArrayList<>();
    private List<String> projectID = new ArrayList<>();

    public GenerateIDSystem(String type) {
        this.type = type;

        //Import the list from the main program and get the last 4 numbers of ID
        List<Developer> developerList = DevFortressMain.model.getDeveloperList();
        for (int i = 0; i < developerList.size(); i++) {
            int strLength = developerList.get(i).getID().length();
            developerID.add(developerList.get(i).getID().substring(1, strLength - 1));
        }

        List<SignedProject> projectList = DevFortressMain.model.getAcceptedProjectList();
        for (int i = 0; i < projectList.size(); i++) {
            int strLength = projectList.get(i).getID().length();
            projectID.add(projectList.get(i).getID().substring(1, strLength - 1));
        }
    }

    public String GenerateID(List<String> list) {
        List<String> listID = list;
        Random random = new Random();
        String idNumber = null;
        boolean duplicate = true;
        int number = 0;

        //Check if the id is duplicate or not
        while (duplicate) {
            duplicate = false;
            number = random.nextInt(9999);
            idNumber = Integer.toString(number);

            for (String x : listID) {
                //Duplicate
                if (idNumber.equals(x)) {
                    duplicate = true;
                    break;
                }
            }
        }

        if (number < 10) {
            return "000" + idNumber;
        }
        if (number < 100) {
            return "00" + idNumber;
        }
        if (number < 1000) {
            return "0" + idNumber;
        }
        return idNumber;
    }

    public String GetID() {
        //Return the corresponding ID based on the type
        switch (type) {
            case "Developer":
                return "d" + GenerateID(developerID);
            case "Project":
                return "p" + GenerateID(developerID);
            default:
                return "";
        }
    }
}
