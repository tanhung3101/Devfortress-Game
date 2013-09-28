/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.model;

import java.util.Map;

/**
 *
 * @author HP ENVY 15
 */
public class Project {

    private String ID;
    private int duration;
    private int level;
    private double price;
    private int maxFunctionPoint;
    private Map<String, Integer> normalFunctionalAreas;
    private Map<String, Integer> hiddenFunctionalAreas;

    public Project(String ID, int Duration, int Level, int MaxFunctionPoint, double Price, Map<String, Integer> normalFunctionalAreas, Map<String, Integer> hiddenFunctionalAreas) {
        this.ID = ID;
        this.duration = Duration;
        this.level = Level;
        this.maxFunctionPoint = MaxFunctionPoint;
        this.price = Price;
        this.normalFunctionalAreas = normalFunctionalAreas;
        this.hiddenFunctionalAreas = hiddenFunctionalAreas;
    }

    public Project() {
        
    }

    public String getID() {
        return this.ID;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getLevel() {
        return this.level;
    }

    public int getMaxFunctionPoint() {
        return this.maxFunctionPoint;
    }

    public double getPrice() {
        return this.price;
    }

    public Map<String, Integer> getNormalFunctionPoints() {
        return this.normalFunctionalAreas;
    }

    public Map<String, Integer> getHiddenFunctionalAreas() {
        return this.hiddenFunctionalAreas;
    }

    public void setID(String id) {
        this.ID = id;
    }

    public void setDuration(int Duration) {
        this.duration = Duration;
    }

    public void setLevel(int Level) {
        this.level = Level;
    }

    public void setFunctionPoint(int MaxFunctionPoint) {
        this.maxFunctionPoint = MaxFunctionPoint;
    }

    public void setPrice(double Price) {
        this.price = Price;
    }

    public void setNormalFunctionPoints(Map<String, Integer> normalFunctionalAreas) {
        this.normalFunctionalAreas = normalFunctionalAreas;
    }

    public void setHiddenFunctionalAreas(Map<String, Integer> hiddenFunctionalAreas) {
        this.hiddenFunctionalAreas = hiddenFunctionalAreas;
    }
}
