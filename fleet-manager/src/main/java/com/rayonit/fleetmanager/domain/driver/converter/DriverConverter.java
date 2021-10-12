package com.rayonit.fleetmanager.domain.driver.converter;

import com.rayonit.fleetmanager.domain.driver.dto.DriverRequest;
import com.rayonit.fleetmanager.domain.driver.dto.DriverResponse;
import com.rayonit.fleetmanager.domain.driver.model.Driver;
import com.rayonit.fleetmanager.general.interfaces.Converter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverConverter implements Converter<Driver, DriverRequest, DriverResponse> {

    @Override
    public Driver convertToModel(DriverRequest request) {
        Driver driver = new Driver(
                null,
                request.getCompanyID(),
                request.getFirstName(),
                request.getLastName(),
                request.getDriverAge(),
                request.getDriverAddress(),
                request.getVehicleAssignedFrom(),
                request.getDriverAddress()
        );
        return driver;
    }

    @Override
    public DriverResponse convertToResponse(Driver model) {
        DriverResponse driverResponse = new DriverResponse(
                model.getDriverID(),
                model.getCompanyID(),
                model.getFirstName(),
                model.getLastName(),
                model.getDriverAge(),
                model.getDriverAddress(),
                model.getVehicleAssignedFrom(),
                model.getProfilePhoto()
        );
        return driverResponse;
    }

    @Override
    public List<DriverResponse> convertToResponseList(Collection<Driver> list) {
        return list.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

}
