package java12.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java12.dto.request.CompanyRequest;
import java12.dto.response.CompanyInfoResponse;
import java12.dto.response.CompanyResponse;
import java12.entity.Company;
import java12.entity.Course;
import java12.entity.Group;
import java12.entity.Instructor;
import java12.entity.exception.MyException;
import java12.repository.CompanyRepository;
import java12.repository.GroupRepository;
import java12.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final GroupRepository groupRepository;

    @Override
    public CompanyResponse save(CompanyRequest companyRequest) throws MyException {
        try {
            boolean exist = companyRepository.existsByName(companyRequest.getName());
            if (exist) throw  new MyException("Name :" + companyRequest.getName() +" already exist !");
            Company company = new Company();
            company.setName(companyRequest.getName());
            company.setCountry(companyRequest.getCountry());
            company.setAddress(companyRequest.getAddress());
            company.setPhoneNumber(companyRequest.getPhoneNumber());
            companyRepository.save(company);
            return CompanyResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully saved Company")
                    .build();
        }catch (MyException e) {
            return CompanyResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CompanyRequest findById(Long companyId) {
        try {
            Company company = companyRepository.findById(companyId).orElseThrow();
            CompanyRequest companyRequest = new CompanyRequest();
            companyRequest.setName(company.getName());
            companyRequest.setAddress(company.getAddress());
            companyRequest.setCountry(company.getCountry());
            companyRequest.setPhoneNumber(company.getPhoneNumber());
            return companyRequest;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }return null;

    }

    @Override
    public List<CompanyRequest> findAll() {
        try {
            List<Company> all = companyRepository.findAll();
            List<CompanyRequest> companyRequests = new ArrayList<>();
            for (Company company : all) {
                companyRequests.add(new CompanyRequest(company.getName(), company.getCountry(), company.getAddress(), company.getPhoneNumber()));
            }
            return companyRequests;
        }catch (Exception e){
            throw new NoSuchElementException();
         }
    }

    @Override @Transactional
    public CompanyResponse updateById(Long companyId, CompanyRequest companyRequest) {
        try {
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Company with " + companyId + "not found"));
            company.setName(companyRequest.getName());
            company.setCountry(companyRequest.getCountry());
            company.setAddress(companyRequest.getAddress());
            company.setPhoneNumber(companyRequest.getPhoneNumber());
            return CompanyResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully updated !")
                    .build();
        }catch (Exception e){
            return CompanyResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CompanyResponse deleteById(Long companyId) {
        try {
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company with id" + companyId + "not found"));

            for (Instructor instructor : company.getInstructors()) {
                instructor.getCompanies().remove(company);
            }
            for (Course cours : company.getCourses()) {
                groupRepository.deleteAll(cours.getGroups());
            }
            companyRepository.delete(company);
            return CompanyResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully deleted !")
                    .build();

        }catch (Exception e){
            return CompanyResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CompanyInfoResponse findInfos(Long companyId) {
        try {
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company with id" + companyId + "not found"));
            CompanyInfoResponse companyInfoResponse = companyRepository.fullCompanyInfo(companyId);
            for (Instructor instructor : company.getInstructors()) {
                companyInfoResponse.addInstructorName(instructor.getLastName());
            }
            int count = 0;
            for (Course course : company.getCourses()) {
                companyInfoResponse.addCourseName(course.getCourseName());
                for (Group group : course.getGroups()) {
                    companyInfoResponse.addGroupName(group.getGroupName());
                    count += group.getStudents().size();
                }
            }
            companyInfoResponse.setCountStudents(count);
            return companyInfoResponse;
        }catch (Exception e){
            return CompanyInfoResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }
}
