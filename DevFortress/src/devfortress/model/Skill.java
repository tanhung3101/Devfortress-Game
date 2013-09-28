/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.model;

import java.util.*;

/**
 *
 * @author Nop
 */
public class Skill {

    private String skillName;
    private int currentLevel;
    private int skillCost;
    private Map<Integer, Integer> levelStartPoint;
    private String skillType;

    public Skill(String skillName, int currentLevel, int skillCost, String skillType, Map<Integer, Integer> levelStartPoint) {
        this.skillName = skillName;
        this.currentLevel = currentLevel;
        this.skillCost = skillCost;
        this.skillType = skillType;
        this.levelStartPoint = levelStartPoint;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Map<Integer, Integer> getLevelStartPoint() {
        return levelStartPoint;
    }

    public void setLevelStartPoint(Map<Integer, Integer> levelStartPoint) {
        this.levelStartPoint = levelStartPoint;
    }

    public int getSkillCost() {
        return skillCost;
    }

    public void setSkillCost(int skillCost) {
        this.skillCost = skillCost;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }
}
