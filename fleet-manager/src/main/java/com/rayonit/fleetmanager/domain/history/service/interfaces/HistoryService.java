package com.rayonit.fleetmanager.domain.history.service.interfaces;


import com.rayonit.fleetmanager.domain.history.dto.HistoryRequest;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface HistoryService {
    ResponseEntity<Object> getAllHistories();
    ResponseEntity<Object> getAllByUserID(String userID);
    void addHistory(HistoryRequest historyRequest);
    ResponseEntity<Object> deleteHistory(String historyID);
}
