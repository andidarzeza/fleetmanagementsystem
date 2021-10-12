package com.rayonit.fleetmanager.domain.vehicle.converter;

import com.rayonit.fleetmanager.domain.vehicle.dto.VehicleRequest;
import com.rayonit.fleetmanager.domain.vehicle.dto.VehicleResponse;
import com.rayonit.fleetmanager.domain.vehicle.model.Vehicle;
import com.rayonit.fleetmanager.general.interfaces.Converter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleConverter implements Converter<Vehicle, VehicleRequest, VehicleResponse> {
    @Override
    public Vehicle convertToModel(VehicleRequest request) {
        return new Vehicle(
                null,
                request.getUserID(),
                request.getCompanyInfo(),
                request.getFleetInfo(),
                request.getDriverID(),
                request.getVehicleName(),
                request.getType(),
                request.getFuel(),
                request.getSpeed(),
                request.getPosition()
        );
    }

    @Override
    public VehicleResponse convertToResponse(Vehicle model) {
        return new VehicleResponse(
                model.getVehicleID(),
                model.getUserID(),
                model.getCompanyInfo(),
                model.getFleetInfo(),
                model.getDriverID(),
                model.getVehicleName(),
                model.getType(),
                model.getFuel(),
                model.getSpeed(),
                model.getPosition()
        );
    }

    @Override
    public List<VehicleResponse> convertToResponseList(Collection<Vehicle> list) {
        return list.stream().map(this::convertToResponse).collect(Collectors.toList());
    }
}
