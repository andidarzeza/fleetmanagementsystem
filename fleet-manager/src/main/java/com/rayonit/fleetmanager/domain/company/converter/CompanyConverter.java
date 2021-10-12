package com.rayonit.fleetmanager.domain.company.converter;

import com.rayonit.fleetmanager.domain.company.dto.CompanyRequest;
import com.rayonit.fleetmanager.domain.company.dto.CompanyResponse;
import com.rayonit.fleetmanager.domain.company.model.Company;
import com.rayonit.fleetmanager.general.interfaces.Converter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyConverter implements Converter<Company, CompanyRequest, CompanyResponse> {
    @Override
    public Company convertToModel(CompanyRequest request) {
        Company company = new Company(
                null,
                request.getUserID(),
                request.getCompanyName()
        );
        return company;
    }

    @Override
    public CompanyResponse convertToResponse(Company model) {
        CompanyResponse companyResponse = new CompanyResponse(
                model.getCompanyID(),
                model.getUserID(),
                model.getCompanyName()
        );
        return companyResponse;
    }

    @Override
    public List<CompanyResponse> convertToResponseList(Collection<Company> list) {
        return list.stream().map(this::convertToResponse).collect(Collectors.toList());
    }
}
