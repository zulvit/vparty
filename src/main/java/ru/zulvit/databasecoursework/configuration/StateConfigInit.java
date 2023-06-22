package ru.zulvit.databasecoursework.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.zulvit.databasecoursework.model.State;
import ru.zulvit.databasecoursework.repository.StateRepository;

@Component
public class StateConfigInit implements ApplicationRunner {

    private final StateRepository stateRepository;

    @Autowired
    public StateConfigInit(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (stateRepository.findAll().isEmpty()) {
            State state = new State();
            state.setDescription("INVITED");
            stateRepository.save(state);
            State state1 = new State();
            state.setDescription("ACCEPTED");
            stateRepository.save(state1);
        }
    }
}
