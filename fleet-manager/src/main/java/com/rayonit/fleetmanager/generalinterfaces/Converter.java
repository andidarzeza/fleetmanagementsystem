package com.rayonit.fleetmanager.generalinterfaces;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

//T represents the actual Model, G the RequestObject and H the ResponseObject
@Service
public interface Converter<T, G, H> {
    T convertToModel(G request);
    H convertToResponse(T model);
    List<H> convertToResponseList(Collection<T> list);
}
