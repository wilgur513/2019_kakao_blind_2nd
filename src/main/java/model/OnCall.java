package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.util.List;

@Getter @Setter
public class OnCall {
    private List<Elevator> elevators;
    private List<Call> calls;

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }
}
