package com.rayonit.fleetmanager.domain.history.converter;

import com.rayonit.fleetmanager.domain.history.dto.HistoryRequest;
import com.rayonit.fleetmanager.domain.history.dto.HistoryResponse;
import com.rayonit.fleetmanager.domain.history.model.History;
import com.rayonit.fleetmanager.general.interfaces.Converter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryConverter implements Converter<History, HistoryRequest, HistoryResponse> {
    @Override
    public History convertToModel(HistoryRequest request) {
        History history = new History(
                null,
                request.getUserID(),
                request.getWhere(),
                request.getDate(),
                request.getAction()
        );
        return history;
    }

    @Override
    public HistoryResponse convertToResponse(History model) {
        HistoryResponse historyResponse = new HistoryResponse(
                model.getHistoryID(),
                model.getUserID(),
                model.getWhere(),
                model.getDate(),
                model.getAction()
        );
        return historyResponse;
    }

    @Override
    public List<HistoryResponse> convertToResponseList(Collection<History> list) {
        return list.stream().map(this::convertToResponse).collect(Collectors.toList());
    }
}
