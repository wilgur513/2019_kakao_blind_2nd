import api.RestApi;
import api.action.ActionRequest;
import api.action.ActionResponse;
import api.common.CommandRequest;
import api.oncall.OnCallResponse;
import api.start.StartResponse;
import lombok.extern.slf4j.Slf4j;
import model.Call;
import model.Command;
import model.Elevator;
import model.OnCall;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Application {
    private static final ModelMapper modelMapper = new ModelMapper();
    private static Map<Integer, Integer> taken = new HashMap<>();

    public static void main(String[] args) {
        StartResponse start = RestApi.startApi(1, 4);

        while(true) {
            OnCallResponse onCallResponse = RestApi.onCallApi(start.getToken());
            OnCall onCall = modelMapper.map(onCallResponse, OnCall.class);
            log.debug("OnCall: {}", onCall);

            List<Call> calls = onCall.getCalls();
            List<Elevator> elevators = onCall.getElevators();

            for(Call call : calls) {
                if(taken.get(call.getId()) != null) {
                    Integer elevatorId = taken.get(call.getId());
                    Elevator elevator = findById(elevators, elevatorId);
                    elevator.takeCall(call);
                }
            }

            for(Call call : calls) {
                if(taken.get(call.getId()) == null) {
                    for(Elevator e : elevators) {
                        if(e.canTake(call)) {
                            e.takeCall(call);
                            taken.put(call.getId(), e.getId());
                            break;
                        }
                    }
                }
            }

            log.debug("elevators : {}", elevators);

            List<CommandRequest> commandRequests = new ArrayList<>();
            for(Elevator elevator : elevators) {
                Command command = elevator.nextCommand();
                CommandRequest request = modelMapper.map(command, CommandRequest.class);
                commandRequests.add(request);
            }
            log.debug("commands : {}", commandRequests);

            ActionRequest actionRequest = new ActionRequest(commandRequests);
            ActionResponse response = RestApi.actionApi(actionRequest, start.getToken());

            if(response.isEnd()) {
                log.info("actionResponse : {}", response);
                break;
            }
        }
    }

    private static Elevator findById(List<Elevator> elevators, int id) {
        for(Elevator elevator : elevators) {
            if(elevator.getId() == id) {
                return elevator;
            }
        }
        throw new AssertionError();
    }
}
