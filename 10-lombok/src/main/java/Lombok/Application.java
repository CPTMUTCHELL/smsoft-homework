package Lombok;

import Lombok.model.taskModel.Task;
import Lombok.repository.etpReport.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    private final TaskRepository service;
    Task taslModel;

    public Application(TaskRepository service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).close();
    }

    @Override
    public void run(String... strings)  {

        List<Task> taskModels = service.findAllByOrderByIdAsc();

        log.info("Testtest {}", taskModels );



        for (int i = 0; i <taskModels.size(); i++) {
            System.out.println(taskModels.get(i).getId());
        }
    }

}