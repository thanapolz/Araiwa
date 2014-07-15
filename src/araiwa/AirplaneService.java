/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package araiwa;

import araiwa.exceptions.NonexistentEntityException;
import com.sun.management.VMOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.internal.dynalink.DefaultBootstrapper;

/**
 *
 * @author dramkuy
 */
public class AirplaneService {

    AirplaneJpaController controller;

    public AirplaneService(AirplaneJpaController controller) {
        this.controller = controller;
    }

    public Airplane Save(Airplane airPlane) {

        if (airPlane.getId() != 0) {
            try {
                //edit
                controller.edit(airPlane);
            } catch (Exception ex) {
                Logger.getLogger(AirplaneService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //create ( new entitiy)
            controller.create(airPlane);
        }
        return airPlane;

    }

    public void DeleteAirPlane(Airplane airplane) {
        try {
            controller.destroy(airplane.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AirplaneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Airplane> getAll() {
        return controller.findAirplaneEntities();
    }
    
    public Airplane findAirPlaneByName(String name){
        List<Airplane> airplanes = controller.findAirplaneEntities();
        for (Airplane airplane : airplanes) {
            if (airplane.getName().equalsIgnoreCase(name)) {
                return airplane;
            }
        }
        return null;
        
    }

}
