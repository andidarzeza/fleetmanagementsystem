package com.rayonit.fleetmanager.domain.fleet.converter;

import com.rayonit.fleetmanager.domain.fleet.dto.FleetRequest;
import com.rayonit.fleetmanager.domain.fleet.dto.FleetResponse;
import com.rayonit.fleetmanager.domain.fleet.model.Fleet;
import com.rayonit.fleetmanager.general.interfaces.Converter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FleetConverter implements Converter<Fleet, FleetRequest, FleetResponse> {
    @Override
    public Fleet convertToModel(FleetRequest request) {
        return new Fleet(
                null,
                request.getCompanyID(),
                request.getFleetName()
        );
    }

    @Override
    public FleetResponse convertToResponse(Fleet model) {
        return new FleetResponse(
                model.getFleetID(),
                model.getCompanyID(),
                model.getFleetName()
        );
    }

    @Override
    public List<FleetResponse> convertToResponseList(Collection<Fleet> list) {
        return list.stream().map(this::convertToResponse).collect(Collectors.toList());
    }
}
