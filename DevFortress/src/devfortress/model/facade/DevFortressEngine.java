/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.model.facade;

import devfortress.model.*;
import devfortress.model.exception.*;
import devfortress.utility.*;
import java.io.*;
import java.util.*;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Mr Dat
 */
public class DevFortressEngine extends Observable implements DevFortressModel {

    private Timer timer;
    private int currentWeek, numOfNewDevShow, numOfNewProjectShow;
    private String currentMonth;
    private List<Developer> developerList;
    private List<Developer> newDeveloperList;
    private List<Project> newProjectList;
    private Map<Integer, String> developerNameDatabase;
    private Map<Integer, Skill> developerSkillDatabase;
    private List<SignedProject> acceptedProjects;
    private double budget;
    private double itemCost, hardwareCost;
    private Map<String, Item> itemList;
    private Map<String, Hardware> hardwareList;

    public DevFortressEngine() {
        developerList = new ArrayList<>();
        newDeveloperList = new ArrayList<>();
        newProjectList = new ArrayList<>();
        currentWeek = 0;
        currentMonth = "January";
        numOfNewDevShow = 10;
        numOfNewProjectShow = 5;
        //ProjectTimer();

        developerNameDatabase = new HashMap<>();
        ImportDeveloperNames();

        developerSkillDatabase = new HashMap<>();
        ImportDeveloperSkills();

        acceptedProjects = new ArrayList<SignedProject>();
        budget = 10000;

        itemList = new HashMap<>();
        hardwareList = new HashMap<>();



        /*
         * Set theme for program
         */
        try {
            for (UIManager.LookAndFeelInfo info :
                    UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(
                            DevFortressMain.mainView);
                    break;
                }
            }
        } catch (Exception e) {
        }
    }

    public void test() {
        //Test
        generateNewDevList();
        generateNewProjectList();
        SignedProject sign;
        for (Project pro : newProjectList) {
            sign = new SignedProject(pro, newDeveloperList);
            acceptedProjects.add(sign);
        }
    }

    /*
     * Initial developer name database
     */
    public void ImportDeveloperNames() {
        try {
            Scanner fileReader = new Scanner(new File("src/data/DeveloperNames.txt"));
            String name;
            int count = 0;
            while (fileReader.hasNext()) {
                name = fileReader.nextLine();
                developerNameDatabase.put(count, name);
                count++;
            }
        } catch (IOException ex) {
            System.out.println("File not found");
        }
    }

    /*
     * Initial developer skill database
     */
    public void ImportDeveloperSkills() {
        try {
            Scanner fileReader = new Scanner(new File("src/data/DeveloperSkills.csv"));
            String skillName, line, skillType;
            int skillNum = 0, skillCost;
            boolean firstLine = true;
            Map<Integer, Integer> levelStartPoint = new HashMap<>();

            while (fileReader.hasNext()) {
                if (firstLine) {
                    fileReader.nextLine();
                    firstLine = false;
                }

                line = fileReader.nextLine();
                StringTokenizer token = new StringTokenizer(line, ",");

                skillName = token.nextToken();
                for (int i = 1; i < 11; i++) {
                    levelStartPoint.put(i, Integer.parseInt(token.nextToken()));
                }
                skillCost = Integer.parseInt(token.nextToken());
                skillType = token.nextToken();

                Skill newSkill = new Skill(skillName, 1, skillCost, skillType, levelStartPoint);

                developerSkillDatabase.put(skillNum, newSkill);
                skillNum++;
            }
        } catch (IOException ex) {
            System.out.println("File not found");
        }
    }

    public List<Developer> getDeveloperList() {
        return developerList;
    }

    public List<Developer> getNewDeveloperList() {
        return newDeveloperList;
    }

    public List<Project> getNewProjectList() {
        return newProjectList;
    }

    public void ProjectTimer() {
        if (currentWeek != 4) {
            timer = new Timer();

            timer.schedule(new TimerTask() {
                public void run() {
                    System.out.println("5 giay da troi qua");
                    for (SignedProject n : acceptedProjects) {
                        n.increaseProcess();
                    }
                    timer.cancel();
                    currentWeek++;
                    System.out.println("Current week: " + currentWeek);
                    ProjectTimer();
                }
            }, 5000);
        } else {
            System.out.println("\nGame is paused!\nDo you want to continue the game(Y/N)?");
            System.out.print("Enter your choice: ");
            String input;
            Scanner reader = new Scanner(System.in);
            input = reader.next();
            if (input.equalsIgnoreCase("Y")) {
                System.out.println("Game is resumed!\n");
                currentWeek = 0;
                ProjectTimer();
            } else {
                System.out.println("Exit game");
            }
        }
    }

    public void generateNewDevList() {
        if (newDeveloperList.size() < numOfNewDevShow) {
            for (int i = numOfNewDevShow; i > 0; i--) {
                newDeveloperList.add(generateDeveloper());
            }
        }
    }

    public void generateNewProjectList() {
        if (newProjectList.size() < numOfNewProjectShow) {
            for (int i = numOfNewProjectShow; i > 0; i--) {
                newProjectList.add(generateProject());
            }
        }
    }

    public Developer generateDeveloper() {
        Random randomGenerator = new Random();
        int randomNumber;

        //Generate random ID
        GenerateIDSystem IDGenerator = new GenerateIDSystem("Developer");
        String ID = IDGenerator.GetID();

        //Generate a developer name
        randomNumber = randomGenerator.nextInt(2000);
        String name = developerNameDatabase.get(randomNumber);

        /**
         * Random skill *
         */
        Map<String, Skill> skillMap = new HashMap<>();

        //Random number of skills will be assigned to this developer
        do {
            randomNumber = randomGenerator.nextInt(35);
        } while (randomNumber == 0);

        for (int i = 1; i <= randomNumber; i++) {
            Skill newSkill;

            //Random a skill
            do {
                randomNumber = randomGenerator.nextInt(34);
                newSkill = developerSkillDatabase.get(randomNumber);
            } while (skillMap.containsKey(newSkill.getSkillName()));

            //Random skill level
            do {
                randomNumber = randomGenerator.nextInt(11);
                newSkill.setCurrentLevel(randomNumber);
            } while (randomNumber == 0);

            skillMap.put(newSkill.getSkillName(), newSkill);
        }
        //

        //Salary
        double salary = 0;
        for (Skill skill : skillMap.values()) {
            salary += skill.getCurrentLevel() * 100;
        }

        Developer newDev = new Developer(ID, name, salary, skillMap);
        return newDev;
    }

    public Project generateProject() {
        Random randomGenerator = new Random();
        int randomNumber;

        //Generate random ID
        GenerateIDSystem IDGenerator = new GenerateIDSystem("Project");
        String ID = IDGenerator.GetID();

        //Random project level
        int projectLevel;
        do {
            randomNumber = randomGenerator.nextInt(7);
            projectLevel = randomNumber;
        } while (randomNumber == 0 || randomNumber == 5);

        //Project duration,max function points, functional ares based on project level
        int duration = 0, maxFunctionPoint = 0, numOfFunctionalAreas = 0, numOfUnkown = 0, numOfUnkownCreated = 0;
        Map<String, Integer> normalFunctionalAreas = new HashMap<>();
        Map<String, Integer> hiddenFunctionalAreas = new HashMap<>();

        if (projectLevel == 1) {
            maxFunctionPoint = 100;
            numOfUnkown = 1;

            //Random duration
            do {
                randomNumber = randomGenerator.nextInt(5);
                duration = randomNumber;
            } while (randomNumber == 0);

            //Random number of functional areas
            do {
                randomNumber = randomGenerator.nextInt(5);
                numOfFunctionalAreas = randomNumber;
            } while (randomNumber == 0);
        } else if (projectLevel == 2) {
            maxFunctionPoint = 100;
            numOfUnkown = 2;

            //Random duration
            do {
                randomNumber = randomGenerator.nextInt(9);
                duration = randomNumber;
            } while (randomNumber == 0);

            //Random number of functional areas
            do {
                randomNumber = randomGenerator.nextInt(9);
                numOfFunctionalAreas = randomNumber;
            } while (randomNumber == 0);
        } else if (projectLevel == 3) {
            maxFunctionPoint = 200;
            numOfUnkown = 3;

            //Random duration
            do {
                randomNumber = randomGenerator.nextInt(13);
                duration = randomNumber;
            } while (randomNumber < 6);

            //Random number of functional areas
            do {
                randomNumber = randomGenerator.nextInt(13);
                numOfFunctionalAreas = randomNumber;
            } while (randomNumber < 6);
        } else if (projectLevel == 4) {
            maxFunctionPoint = 200;
            numOfUnkown = 4;

            //Random duration
            do {
                randomNumber = randomGenerator.nextInt(25);
                duration = randomNumber;
            } while (randomNumber < 12);

            //Random number of functional areas
            do {
                randomNumber = randomGenerator.nextInt(25);
                numOfFunctionalAreas = randomNumber;
            } while (randomNumber < 12);
        } else if (projectLevel == 6) {
            maxFunctionPoint = 400;

            //Random duration
            do {
                randomNumber = randomGenerator.nextInt(25);
                duration = randomNumber;
            } while (randomNumber == 0);

            //Random number of functional areas
            do {
                randomNumber = randomGenerator.nextInt(25);
                numOfFunctionalAreas = randomNumber;
                numOfUnkown = randomNumber;
            } while (randomNumber == 0);
        }

        for (int i = 1; i <= numOfFunctionalAreas; i++) {
            //Random a skill
            String newArea;
            int areaFunctionPoint;
            do {
                randomNumber = randomGenerator.nextInt(34);
                newArea = developerSkillDatabase.get(randomNumber).getSkillName();
            } while (normalFunctionalAreas.containsKey(newArea)
                    || hiddenFunctionalAreas.containsKey(newArea));

            //Random area function point
            do {
                randomNumber = randomGenerator.nextInt(101);
                areaFunctionPoint = randomNumber;
            } while (randomNumber < 20);

            //Check whether hidden skills reached number of unknown
            if (numOfUnkownCreated != numOfUnkown) {
                hiddenFunctionalAreas.put(newArea, areaFunctionPoint);
                numOfUnkownCreated++;
            } else {
                normalFunctionalAreas.put(newArea, areaFunctionPoint);
            }
        }

        int projectPrice = (projectLevel * 100000) + (24 / duration * 10000) + (maxFunctionPoint * 10000);

        Project newProject = new Project(ID, duration, projectLevel,
                maxFunctionPoint, projectPrice, normalFunctionalAreas, hiddenFunctionalAreas);
//        System.out.println("ID: " + ID + "\nDuration: " + duration + "\nLevel: "
//                + projectLevel + "\nMaxFP: " + maxFunctionPoint + "\nPrice: " +
//                projectPrice + "\nHidden: " + hiddenFunctionalAreas.size());
        return newProject;
    }

    /*Hung Code*/
    /*Method:
     * hireDeveloper: hire developer ( may be from the random generate list of
     * developer before this method).
     * listCurrentDeveloper: conatins all the current developer in the company
     * FireDeveloper: fire the developer from the company
     * CheckExistDeveloper: checking the current Developer in the company
     * addDeveloperToProject: add the developer to work on the signed project
     * removeDeveloperFromProject: remove the developers from the signed project.
     */
    public boolean hireDeveloper(Developer dev) {

        if (!checkExistDeveloper(dev)) {
            newDeveloperList.add(dev);
            return true;
        }
        return false;
    }

    public boolean fireDeveloper(Developer dev) {

        if (checkExistDeveloper(dev)) {
            developerList.remove(dev);
            return true;
        }

        return false;
    }

    public boolean addDeveloperToProject(List<Developer> listDev, SignedProject signedProject) {

        if (signedProject.addListDeveloper(listDev)) {

            return true;
        }
        return false;

    }

    public boolean removeDeveloperFromProject(List<Developer> listRemoveDev, SignedProject signedProject) {
        List<Developer> listDev = signedProject.getDevelopers();

        for (Developer n : listRemoveDev) {
            listDev.remove(n);
            Project nullProject = null;
            n.setCurrentProject(nullProject);
            signedProject.setDeveloperList(listDev);
            return true;
        }

        return false;
    }

    public boolean checkExistDeveloper(Developer dev) {

        if (developerList.contains(dev)) {
            return true;
        }

        return false;
    }

    /*Thien Code*/
    public List<SignedProject> getAcceptedProjectList() {
        return acceptedProjects;
    }

    public void acceptProject(Project newProject, List<Developer> developerList)
            throws ProjectException {
        for (int i = 0; i < acceptedProjects.size(); i++) {
            //check the project was signed before
            if (acceptedProjects.get(i).getID() == newProject.getID()) {
                throw new ProjectException("Project aldready signed!");
            }
            // else{
            // List<Developer> developerInSystem = acceptedProjects.get(i).getDevelopers();
            // //check the developer is signed to other project
            // for(int t = 0; t<developerInSystem.size();t++){
            // for(int j = 0; j<developerList.size();j++){
            // if(developerInSystem.get(t).getCurrentProject() != null ||
            // developerList.get(j).getID() == developerInSystem.get(t).getID() ){
            // throw new ProjectException(developerList.get(j).getName()
            // +" is busy in "+acceptedProjects.get(i).getName()+"!");
            // }
            // }
            // }
            // }
        }
        //set the developer's status
        for (int i = 0; i < developerList.size(); i++) {
            developerList.get(i).setCurrentProject(newProject);
        }
        //add project and increase the budget
        SignedProject newSignedProject = new SignedProject(newProject, developerList);
        acceptedProjects.add(newSignedProject);
        budget = budget + calculateBudget(newSignedProject, 25);

    }

    public double calculateBudget(Project pro, double percentage) throws ProjectException {
        double budget = 0;
        if (percentage <= 0) {
            throw new ProjectException("The percentage must be greater than 0");
        }
        budget = (percentage * pro.getPrice()) / 100;
        return budget;
    }

    public boolean cancelProject(SignedProject currentProject) throws ProjectException {
        for (int i = 0; i < acceptedProjects.size(); i++) {
            if (acceptedProjects.get(i).getID() == currentProject.getID()) {
                if (acceptedProjects.get(i).getDevelopers().size() != 0) {
                    List<Developer> temp = acceptedProjects.get(i).getDevelopers();
                    //set developer status, reduce the budget when cancel project
                    for (int d = 0; d < temp.size(); d++) {
                        temp.get(d).setCurrentProject(null);
                    }
                    budget = budget - calculateBudget(acceptedProjects.get(i), 25);
                    acceptedProjects.remove(currentProject);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean determineProcess(SignedProject currentProject, double timeLeft) {
        double result;
        result = currentProject.getDuration() - timeLeft;
        if (result <= 0) {
            //project out of time
            return true;
        } else {
            //project still has time
            return false;
        }
    }

    /*Tai Code*/
    //create items: pizza, coffee, red bull and beer
    public void createItem() {
        Item coffee = new Item("Coffee", 5, 10, "Coffee will increase the energy of developers 5 points");
        Item pizza = new Item("Pizza", 10, 20, "Pizza will increase the energy of developers 10 points");
        Item redbull = new Item("Red Bull", 15, 30, "Red bull will increase the energy of developers 15 points");
        Item beer = new Item("Beer", 0, 25, "Beer will make the developers happy but the productivity will be halved");

        itemList.put("Coffee", coffee);
        itemList.put("Pizza", pizza);
        itemList.put("Red Bull", redbull);
        itemList.put("Beer", beer);
    }

    public void createHardware() {
        Hardware lowHardware = new Hardware("Low Specification", 250, "This meets the minimum requirements for developing software.");
        Hardware medHardware = new Hardware("Medium Hardware", 350, "Medium hardware can increase 10% of developers' productivity");
        Hardware highHardware = new Hardware("High Hardware", 500, "High hardware can increase 25% of developers' productivity");

        hardwareList.put("Low Hardware", lowHardware);
        hardwareList.put("Med Hardware", medHardware);
        hardwareList.put("High Hardware", highHardware);
    }

    public void buyItem(HashMap<String, Integer> itemBought) {
        for (String itemName : itemBought.keySet()) {
            if (itemName.equals("Beer")) {
                //halve the productivity
            } else {
                //calculate item cost
                itemCost = itemList.get(itemName).getPrice() * itemBought.get(itemName);

                //set value to developer list
                Developer dev;
                for (int i = 0; i < developerList.size(); i++) {
                    dev = developerList.get(i);
                    if (dev.getEnergyPoint() <= 100 - (itemList.get(itemName).getValue() * itemBought.get(itemName))) {
                        dev.setEnergyPoint(dev.getEnergyPoint() + (itemList.get(itemName).getValue() * itemBought.get(itemName)));
                    } else {
                        dev.setEnergyPoint(100);
                    }
                }
            }
        }
    }

    //calculate hardware cost
    public void buyHardware(String hName) {
        hardwareCost = hardwareList.get(hName).getPrice();
        //affect productivity
    }

    //calculate salary of staff
    public double calculateTotalSalary() {
        Developer dev;
        double salary = 0;
        for (int i = 0; i < developerList.size(); i++) {
            dev = developerList.get(i);
            salary += dev.getSalary();
        }
        return salary;
    }

    //calculate remain budget
    public void calculateBudget() {
        budget = budget - calculateTotalSalary() - itemCost - hardwareCost;
        //plus 25% project price
    }
}
