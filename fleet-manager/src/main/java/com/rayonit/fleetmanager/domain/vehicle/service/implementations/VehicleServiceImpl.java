package com.rayonit.fleetmanager.domain.vehicle.service.implementations;

import com.rayonit.fleetmanager.domain.company.service.interfaces.CompanyService;
import com.rayonit.fleetmanager.domain.driver.dto.DriverRequest;
import com.rayonit.fleetmanager.domain.event.model.Event;
import com.rayonit.fleetmanager.domain.event.repository.EventRepository;
import com.rayonit.fleetmanager.domain.history.dto.HistoryRequest;
import com.rayonit.fleetmanager.domain.history.model.enums.Action;
import com.rayonit.fleetmanager.domain.history.model.enums.Departments;
import com.rayonit.fleetmanager.domain.history.service.interfaces.HistoryService;
import com.rayonit.fleetmanager.domain.notification.model.Notification;
import com.rayonit.fleetmanager.domain.notification.service.interfaces.NotificationService;
import com.rayonit.fleetmanager.domain.vehicle.dto.VehicleRequest;
import com.rayonit.fleetmanager.domain.vehicle.dto.VehicleResponse;
import com.rayonit.fleetmanager.general.classes.Position;
import com.rayonit.fleetmanager.general.classes.PositionWrapper;
import com.rayonit.fleetmanager.domain.vehicle.model.Vehicle;
import com.rayonit.fleetmanager.domain.vehicle.repository.VehicleRepository;
import com.rayonit.fleetmanager.domain.vehicle.service.interfaces.VehicleService;
import com.rayonit.fleetmanager.general.classes.Message;
import com.rayonit.fleetmanager.general.classes.Route;
import com.rayonit.fleetmanager.general.classes.RouteGenerator;
import com.rayonit.fleetmanager.general.interfaces.Converter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final Double fuelConsumption = 0.3;
    private List<Route> routes;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private Converter<Vehicle, VehicleRequest, VehicleResponse> converter;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private NotificationService notificationService;

    public VehicleServiceImpl() {
        this.routes = RouteGenerator.getRoutes();
        Collections.shuffle(routes);
    }

    //Throws exception !
//    @PostConstruct
//    private void getAssignedVehicles() {
//        this.assignedVehicles = this.vehicleRepository.findAllByDriverIDExists();
//    }

    @Scheduled(fixedDelay = 2000)
    public void doSmth() {
        List<Vehicle> vehicles = this.vehicleRepository.findAll();
        List<Event> events = new ArrayList<>();
        vehicles.forEach(vehicle -> {
            if(vehicle.isEligible()){
                int index = vehicles.indexOf(vehicle);
                Position position = new Position(routes.get(index).getRoute().get(0).getX(),routes.get(index).getRoute().get(0).getY());
                vehicle.setPosition(position);
                Double fuelRemaining = vehicle.getFuel() - fuelConsumption;
                vehicle.setFuel(fuelRemaining);
                if(Math.random() >=0.99){
                    vehicle.setBroken(true);
                    Notification notification = new Notification(
                            null,
                            vehicle.getUserID(),
                            "Vehicle got crashed!",
                            vehicle.getCompanyInfo(),
                            vehicle.getFleetInfo(),
                            vehicle.getVehicleID(),
                            vehicle.getVehicleName()
                    );
                    notificationService.sendNotification(notification);
                    simpMessagingTemplate.convertAndSendToUser(vehicle.getUserID(), "/queue/notifications", notification);
                }
                if(vehicle.lowFuel()){
                    Notification notification = new Notification(
                            null,
                            vehicle.getUserID(),
                            "Low Fuel",
                            vehicle.getCompanyInfo(),
                            vehicle.getFleetInfo(),
                            vehicle.getVehicleID(),
                            vehicle.getVehicleName()
                    );
                    notificationService.sendNotification(notification);
                    simpMessagingTemplate.convertAndSendToUser(vehicle.getUserID(), "/queue/notifications", notification);
                }
                routes.get(index).getRoute().remove(0);
                routes.get(index).getRoute().add(position);
                vehicleRepository.save(vehicle);
                simpMessagingTemplate.convertAndSendToUser(vehicle.getUserID(), "/queue/vv", vehicle);
            }
        });

    }

    @Scheduled(fixedDelay = 10000000)
    public void shuffle() {
        Collections.shuffle(routes);
    }

    @Override
    public ResponseEntity<Object> getAllVehicles() {
        return new ResponseEntity<>(converter.convertToResponseList(vehicleRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllVehiclesByFleetID(String fleetID) {
        return new ResponseEntity<>(converter.convertToResponseList(vehicleRepository.findAllByFleetInfoFleetID(fleetID)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addVehicle(VehicleRequest vehicleRequest) {
        if (vehicleRequest != null) {
            Vehicle vehicle = converter.convertToModel(vehicleRequest);
            vehicleRepository.save(vehicle);
            historyService.addHistory(new HistoryRequest(
                    companyService.getCompany(vehicleRequest.getCompanyInfo().getCompanyID()).getUserID(),
                    Departments.VEHICLES,
                    new Date(),
                    Action.CREATED_VEHICLE
            ));
            return new ResponseEntity<>(converter.convertToResponse(vehicle), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("No  Object of type Vehicle was provided!"), HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<Object> updateVehicle(VehicleRequest vehicleRequest, String vehicleID) {
        if (vehicleRequest != null) {
            Vehicle vehicle = vehicleRepository.findById(vehicleID).orElse(null);
            if (vehicle == null) {
                return new ResponseEntity<>(new Message("Could not find Vehicle with ID: " + vehicleID), HttpStatus.NOT_FOUND);
            } else {
                Vehicle vehicle1 = converter.convertToModel(vehicleRequest);
                vehicle1.setVehicleID(vehicleID);
                vehicleRepository.save(vehicle1);
                historyService.addHistory(new HistoryRequest(
                        companyService.getCompany(vehicle1.getCompanyInfo().getCompanyID()).getUserID(),
                        Departments.VEHICLES,
                        new Date(),
                        Action.UPDATED_VEHICLE
                ));
                return new ResponseEntity<>(converter.convertToResponse(vehicle), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(new Message("No Object of type Vehicle was provided!"), HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<Object> deleteVehicle(String vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        if (vehicle == null)
            return new ResponseEntity<>(new Message("No Vehicle was found for the given ID: " + vehicleId), HttpStatus.NOT_FOUND);
            vehicleRepository.delete(vehicle);
            List<Vehicle> vehicleList = new ArrayList<>();
            vehicleList.add(vehicle);
            this.notificationService.deleteNotificationsForEachVehicle(vehicleList);
            historyService.addHistory(new HistoryRequest(
                companyService.getCompany(vehicle.getCompanyInfo().getCompanyID()).getUserID(),
                Departments.VEHICLES,
                new Date(),
                Action.DELETED_VEHICLE
        ));
        return new ResponseEntity<>(converter.convertToResponse(vehicle), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllVehiclesByUserID(String userID) {
        return new ResponseEntity<>(converter.convertToResponseList(vehicleRepository.findAllByUserID(userID)), HttpStatus.OK);
    }

    @Override
    public List<Vehicle> getAllVehiclesByCompanyID(String companyID) {
        return vehicleRepository.findAllByCompanyInfoCompanyID(companyID);
    }

    @Override
    public ResponseEntity<Object> assignDriver(String driverID, String vehicleID) {
        Vehicle vehicle = vehicleRepository.findById(vehicleID).orElse(null);
        if(vehicle == null) return new ResponseEntity<>(new Message("Could't find Vehicle with id: " + vehicleID), HttpStatus.NOT_FOUND);
        vehicle.setDriverID(driverID);
        vehicleRepository.save(vehicle);
        return new ResponseEntity<>(converter.convertToResponse(vehicle), HttpStatus.OK);
    }

    @Override
    public void deleteVehicleByFleetID(String fleetID){
        List<Vehicle> vehiclesToBeDeleted = vehicleRepository.findAllByFleetInfoFleetID(fleetID);
        this.notificationService.deleteNotificationsForEachVehicle(vehiclesToBeDeleted);
        vehicleRepository.deleteAll(vehiclesToBeDeleted);
    }

    @Override
    public ResponseEntity<Object> fixCar(String vehicleID) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleID);
        if(vehicle.isPresent()){
            Vehicle vehicle1 = vehicle.get();
            vehicle1.setBroken(false);
            vehicleRepository.save(vehicle1);
            notificationService.deleteNotification(vehicleID, "Vehicle got crashed!");
            return new ResponseEntity<>(converter.convertToResponse(vehicle1), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("No Vehicle with given id was found"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> refillCar(String vehicleID) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleID);
        if(vehicle.isPresent()){
            Vehicle vehicle1 = vehicle.get();
            vehicle1.setFuel(15.0);
            vehicleRepository.save(vehicle1);
            notificationService.deleteNotification(vehicleID, "Low Fuel");
            return new ResponseEntity<>(converter.convertToResponse(vehicle1), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("No Vehicle with given id was found"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void removeDriver(String driverID) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByDriverID(driverID);
        if (vehicleOptional.isPresent()){
            Vehicle vehicle = vehicleOptional.get();
            vehicle.setDriverID("");
            vehicleRepository.save(vehicle);
        }
    }

    @Override
    public ResponseEntity<Object> unAssignDriver(String vehicleID) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleID);
        if (vehicleOptional.isPresent()){
            Vehicle vehicle = vehicleOptional.get();
            vehicle.setDriverID("");
            vehicleRepository.save(vehicle);
            return new ResponseEntity<>(new Message("Changes successfully"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("Could not find vehicle"), HttpStatus.NOT_FOUND);
        }
    }
}
