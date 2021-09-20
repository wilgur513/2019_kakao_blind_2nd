package api.start;

import api.common.ElevatorResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class StartResponse {
    private String token;
    private int timestamp;
    private List<ElevatorResponse> elevators;
    @JsonProperty("is_end")
    private boolean isEnd;
}
