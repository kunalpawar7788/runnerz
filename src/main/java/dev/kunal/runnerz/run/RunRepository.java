package dev.kunal.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RunRepository {

    private List<Run> runs = new ArrayList<>();
    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    void create(Run run) {
        runs.add(run);
    }
    @PostConstruct
    private void List() {
        runs.add(new Run(1,"Monday morning Run", LocalDateTime.now(),
                LocalDateTime.now().plus(20,ChronoUnit.MINUTES),5, Location.INDOOR));
        runs.add(new Run(2,"Sunday morning Run", LocalDateTime.now(),
                LocalDateTime.now().plus(40,ChronoUnit.MINUTES),5, Location.OUTDOOR));
    }

    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(id);
        if(existingRun.isPresent()){
            runs.set(runs.indexOf(existingRun.get()),run);
        }
    }

    void delete(Integer id){
        runs.removeIf(run -> run.id().equals(id));
    }
}
