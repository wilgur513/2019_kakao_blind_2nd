package api.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class ElevatorResponse {
    private int id;
    private int floor;
    private List<CallResponse> passengers;
    private String status;
}
