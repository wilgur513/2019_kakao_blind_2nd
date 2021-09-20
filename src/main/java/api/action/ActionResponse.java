package api.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import api.common.ElevatorResponse;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class ActionResponse {
    private String token;
    private int timestamp;
    private List<ElevatorResponse> elevators;
    @JsonProperty("is_end")
    private boolean isEnd;
}
