/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.model.facade;

import devfortress.model.*;
import java.util.*;

/**
 *
 * @author Mr Dat
 */
public interface DevFortressModel {

    List<Developer> getDeveloperList();

    List<Project> getNewProjectList();

    Developer generateDeveloper();

    Project generateProject();

    void ImportDeveloperNames();

    void ImportDeveloperSkills();

    void ProjectTimer();

    List<Developer> getNewDeveloperList();

    void generateNewDevList();

    void generateNewProjectList();

    List<SignedProject> getAcceptedProjectList();
}
