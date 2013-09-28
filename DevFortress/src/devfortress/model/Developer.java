/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.model;

import java.util.*;

/**
 *
 * @author Mr Dat
 */
public class Developer {

    private String ID;
    private String name;
    private int emotionPoint;
    private int energyPoint;
    private boolean happyStatus;
    private Map<String, Skill> skillMap;
    private String masterSkill;
    private double salary;
    private Project currentProject;

    public Developer(String ID, String name, double salary, Map<String, Skill> skillMap) {
        this.ID = ID;
        this.name = name;
        emotionPoint = 10;
        energyPoint = 10;
        happyStatus = true;
        this.skillMap = skillMap;
        this.salary = salary;
        currentProject = null;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmotionPoint() {
        return emotionPoint;
    }

    public void setEmotionPoint(int emotionPoint) {
        this.emotionPoint = emotionPoint;
    }

    public int getEnergyPoint() {
        return energyPoint;
    }

    public void setEnergyPoint(int energyPoint) {
        this.energyPoint = energyPoint;
    }

    public boolean isHappyStatus() {
        return happyStatus;
    }

    public void setHappyStatus(boolean happyStatus) {
        this.happyStatus = happyStatus;
    }

    public Map<String, Skill> getSkillMap() {
        return skillMap;
    }

    public void setSkillMap(Map<String, Skill> skillMap) {
        this.skillMap = skillMap;
    }

    public String getMasterSkill() {
        return masterSkill;
    }

    public void setMasterSkill(String masterSkill) {
        this.masterSkill = masterSkill;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    public Project getCurrentProject() {
        return currentProject;
    }
}
