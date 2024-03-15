package java12.repository;

import jakarta.transaction.Transactional;
import java12.dto.response.InfoInstructorsResponse;
import java12.dto.response.InstructorResponse;
import java12.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {


    @Query("SELECT new java12.dto.response.InstructorResponse(i.id,i.firstName,i.lastName,i.phoneNumber,i.specialization) " +
            "FROM Instructor i " +
            "JOIN i.companies c " +
            "WHERE c.id = :companyId")
    List<InstructorResponse> findAllId(Long companyId);


    @Query("select count(s) from Instructor i join i.courses c join c.groups g join g.students s where i.id = :inId")
    Integer countOfById(Long inId);


    @Query("""
            select new java12.dto.response.InfoInstructorsResponse(i.id,i.firstName,i.lastName,i.phoneNumber,i.specialization) from Instructor i where i.id =:instructorId
            """)
    InfoInstructorsResponse fullInstructorInfos(Long instructorId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE courses SET instructor_id = NULL WHERE instructor_id = :instructorId", nativeQuery = true)
    void deleteByInstructorId(@Param("instructorId") Long instructorId);

}