/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.model;

import java.util.List;

/**
 *
 * @author HP ENVY 15
 */
public class SignedProject extends Project {

    private List<Developer> developerList;
    private int process;

    public SignedProject(Project Project, List<Developer> DeveloperList) {
        super(Project.getID(), Project.getDuration(), Project.getLevel(),
                Project.getMaxFunctionPoint(), Project.getPrice(),
                Project.getNormalFunctionPoints(), Project.getHiddenFunctionalAreas());
        this.developerList = DeveloperList;
        this.process = 0;
    }

    public int getProcess() {
        return this.process;
    }

    public void setProcess(int Process) {
        this.process = Process;
    }

    public void increaseProcess() {
        process++;
    }

    public List<Developer> getDevelopers() {
        return this.developerList;
    }

    public void setDeveloperList(List<Developer> DeveloperList) {
        this.developerList = DeveloperList;
    }

    public void addDeveloper(Developer newDeveloper) {
//        for(int i = 0;i < developerList.size(); i++){
//            if(developerList.get(i).getID() == newDeveloper.getID()){
//                return false;
//            }
//        }
        developerList.add(newDeveloper);

    }

    public void removeDeveloper(Developer removeDeveloper) {
//        for(int i = 0; i<developerList.size();i++){
//            if(developerList.get(i).getID() == removeDeveloper.getID()){
        developerList.remove(removeDeveloper);
//                return true;
//            }
//        }
//        return false;
    }

    public boolean addListDeveloper(List<Developer> listDev) {

        for (Developer dev : listDev) {
            if (!developerList.contains(dev)) {
                if (dev.getCurrentProject() == null) {
                    developerList.add(dev);
                    dev.setCurrentProject(this);
                    return true;
                }
            }
        }
        return false;
    }
}
