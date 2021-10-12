package com.rayonit.fleetmanager.domain.history.service.implementations;

import com.rayonit.fleetmanager.domain.history.dto.HistoryRequest;
import com.rayonit.fleetmanager.domain.history.dto.HistoryResponse;
import com.rayonit.fleetmanager.domain.history.model.History;
import com.rayonit.fleetmanager.domain.history.repository.HistoryRepository;
import com.rayonit.fleetmanager.domain.history.service.interfaces.HistoryService;
import com.rayonit.fleetmanager.general.classes.Message;
import com.rayonit.fleetmanager.general.interfaces.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private Converter<History, HistoryRequest, HistoryResponse> converter;

    @Override
    public ResponseEntity<Object> getAllHistories() {
        return new ResponseEntity<>(converter.convertToResponseList(historyRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public void addHistory(HistoryRequest historyRequest) {
        if (historyRequest != null){
            History history = converter.convertToModel(historyRequest);
            historyRepository.save(history);
        }
    }

    @Override
    public ResponseEntity<Object> deleteHistory(String historyID) {
        History history = historyRepository.findById(historyID).orElse(null);
        if (history == null) return new ResponseEntity<>(new Message("Could not find history with given ID: " + historyID), HttpStatus.NOT_FOUND);
        historyRepository.delete(history);
        return new ResponseEntity<>(converter.convertToResponse(history), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllByUserID(String userID) {
        return new ResponseEntity<>(converter.convertToResponseList(historyRepository.getAllByUserID(userID)), HttpStatus.OK);
    }
}
