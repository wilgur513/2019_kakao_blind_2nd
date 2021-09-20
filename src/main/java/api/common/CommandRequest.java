package api.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.util.List;

@Getter @Setter
public class CommandRequest {
    @JsonProperty("elevator_id")
    private int elevatorId;
    private String command;
    @JsonProperty("call_ids")
    private List<Integer> callIds;

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }
}

