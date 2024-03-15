package java12.service;

import java12.dto.request.CompanyRequest;
import java12.dto.response.CompanyInfoResponse;
import java12.dto.response.CompanyResponse;
import java12.entity.exception.MyException;

import java.util.List;

public interface CompanyService {

    CompanyResponse save(CompanyRequest companyRequest) throws MyException;
    CompanyRequest findById(Long companyId);


    List<CompanyRequest> findAll();

    CompanyResponse updateById(Long companyId, CompanyRequest companyRequest);

    CompanyResponse deleteById(Long companyId);

    CompanyInfoResponse findInfos(Long companyId);
}
