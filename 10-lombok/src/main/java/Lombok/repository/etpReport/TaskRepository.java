package Lombok.repository.etpReport;

import Lombok.model.taskModel.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByComplexityLabel(String str);

    List<Task> findAllByTypeLabel(String str);


    List<Task> findAllByOrderByIdAsc();
    List<Task> findAllById(Long lng);



}
