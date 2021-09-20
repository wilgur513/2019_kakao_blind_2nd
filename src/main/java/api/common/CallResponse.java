package api.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CallResponse {
    private int id;
    private int timestamp;
    private int start;
    private int end;
}
