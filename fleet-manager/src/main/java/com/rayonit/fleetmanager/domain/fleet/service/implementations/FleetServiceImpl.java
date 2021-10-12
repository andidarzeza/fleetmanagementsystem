package com.rayonit.fleetmanager.domain.fleet.service.implementations;

import com.rayonit.fleetmanager.domain.company.service.interfaces.CompanyService;
import com.rayonit.fleetmanager.domain.fleet.dto.FleetRequest;
import com.rayonit.fleetmanager.domain.fleet.dto.FleetResponse;
import com.rayonit.fleetmanager.domain.fleet.model.Fleet;
import com.rayonit.fleetmanager.domain.fleet.repository.FleetRepository;
import com.rayonit.fleetmanager.domain.fleet.service.interfaces.FleetService;
import com.rayonit.fleetmanager.domain.history.dto.HistoryRequest;
import com.rayonit.fleetmanager.domain.history.model.enums.Action;
import com.rayonit.fleetmanager.domain.history.model.enums.Departments;
import com.rayonit.fleetmanager.domain.history.service.interfaces.HistoryService;
import com.rayonit.fleetmanager.domain.vehicle.service.interfaces.VehicleService;
import com.rayonit.fleetmanager.general.classes.Message;
import com.rayonit.fleetmanager.general.interfaces.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FleetServiceImpl implements FleetService {
    @Autowired
    private FleetRepository fleetRepository;

    @Autowired
    private Converter<Fleet, FleetRequest, FleetResponse> converter;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public ResponseEntity<Object> getAllFleetsByCompanyID(String companyID) {
        return new ResponseEntity<>(converter.convertToResponseList(fleetRepository.findAllByCompanyID(companyID)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addFleet(FleetRequest fleetRequest) {
        if(fleetRequest != null){
            Fleet fleet = converter.convertToModel(fleetRequest);
            fleetRepository.save(fleet);
            historyService.addHistory(new HistoryRequest(
                companyService.getCompany(fleet.getCompanyID()).getUserID(),
                    Departments.FLEETS,
                    new Date(),
                    Action.CREATED_FLEET
            ));
            return new ResponseEntity<>(converter.convertToResponse(fleet), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("No Object of Fleet was provided!"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> updateFleet(FleetRequest fleetRequest, String fleetID) {
        if (fleetRequest != null){
            Optional<Fleet> fleetOptional = fleetRepository.findById(fleetID);
            if (fleetOptional.isPresent()){
                Fleet fleet = converter.convertToModel(fleetRequest);
                fleet.setFleetID(fleetID);
                fleetRepository.save(fleet);
                historyService.addHistory(new HistoryRequest(
                        companyService.getCompany(fleet.getCompanyID()).getUserID(),
                        Departments.FLEETS,
                        new Date(),
                        Action.UPDATED_FLEET
                ));
                return new ResponseEntity<>(converter.convertToResponse(fleet), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new Message("Could not find Fleet with the given ID: " + fleetID), HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(new Message("No Object of Fleet was provided!"), HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<Object> deleteFleet(String fleetID) {
        Optional<Fleet> fleetOptional = fleetRepository.findById(fleetID);
        if (fleetOptional.isPresent()){
            Fleet fleet = fleetOptional.get();
            fleetRepository.delete(fleet);
            this.deleteAllFleetResources(fleet.getFleetID());
            historyService.addHistory(new HistoryRequest(
                    companyService.getCompany(fleet.getCompanyID()).getUserID(),
                    Departments.FLEETS,
                    new Date(),
                    Action.DELETED_FLEET
            ));
            return new ResponseEntity<>(converter.convertToResponse(fleet), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("Could not find Fleet with the given ID: " + fleetID), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<Object> deleteFleetsByCompanyID(String companyID) {
        fleetRepository.deleteAllByCompanyID(companyID);
        return new ResponseEntity<>(new Message("Fleets deleted successfully!"), HttpStatus.OK);
    }

    @Override
    public void deleteFleetByCompanyID(String companyID){
        List<Fleet> fleetsToBeDeleted = fleetRepository.findAllByCompanyID(companyID);
        fleetsToBeDeleted.forEach(fleet -> {
            this.vehicleService.deleteVehicleByFleetID(fleet.getFleetID());
            fleetRepository.delete(fleet);
        });
    }

    void deleteAllFleetResources(String fleetID) {
        this.vehicleService.deleteVehicleByFleetID(fleetID);
    }
}
