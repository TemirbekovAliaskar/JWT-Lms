package java12.repository;

import java12.dto.response.GroupResponse;
import java12.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select new java12.dto.response.GroupResponse(c.id, c.groupName, c.imageLink, c.description) from Group c")
    List<GroupResponse> findAllGroups();

    @Query("select s.fullName, count(s) from Group i join i.students s where i.id = :groupId group by s.fullName")
    List <String> countById(Long groupId);

    @Query("select new java12.dto.response.GroupResponse(c.id, c.groupName, c.imageLink, c.description) from Group c where c.id =:groupId")
    GroupResponse findByIdGroup(Long groupId);
}