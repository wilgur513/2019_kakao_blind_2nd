package api.action;

import lombok.*;
import api.common.CommandRequest;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ActionRequest {
    private List<CommandRequest> commands;
}
