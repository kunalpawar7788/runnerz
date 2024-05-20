package dev.kunal.runnerz.run;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Component
public class JdbcRunRepository {
    private static final Logger log = LoggerFactory.getLogger(JdbcRunRepository.class);
    private final JdbcClient jdbcClient;
    public JdbcRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("Select * from run")
                .query(Run.class)
                .list();
    }
    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT id,title,started_on,completed_on,miles,location FROM Run WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }
    public void create(Run run) {
        var updated =  jdbcClient.sql("INSERT INTO Run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(),run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString()))
                .update();

        Assert.state(updated == 1, "Failed to create run " + run.title());
    }

}
