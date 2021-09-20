package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter @Setter
public class Elevator {
    private int id;
    private int floor;
    private List<Call> taken;
    private List<Call> passengers;
    private String status;

    public Elevator() {
        taken = new ArrayList<>();
    }

    public void takeCall(Call call) {
        taken.add(call);
    }

    public boolean canTake(Call call) {
        if(isIdle()) {
            return true;
        }

        if(isOverPerson()) {
            return false;
        }

        if(nearCall().isUpward() && floor <= call.getStart() && call.isUpward()) {
            return true;
        }

        if(nearCall().isDownward() && floor >= call.getStart() && call.isDownward()) {
            return true;
        }

        return false;
    }

    public Command nextCommand() {
        Command command = new Command();
        command.setElevatorId(id);
        command.setCommand(command());
        command.setCallIds(callIds());
        return command;
    }

    private String command() {
        if(status.equals("STOPPED")) {
            if(hasEnterOrExitCall()) {
                return "OPEN";
            }
            if(isIdle()) {
                return "STOP";
            }

            int nearFloor = nearCall().getEnd();
            if(taken.contains(nearCall())) {
                nearFloor = nearCall().getStart();
            }
            if(nearFloor > floor) {
                return "UP";
            }
            if(nearFloor < floor) {
                return "DOWN";
            }
        }

        if(status.equals("OPENED")) {
            if(hasExitCall()) {
                return "EXIT";
            }
            if(hasEnterCall()) {
                return "ENTER";
            }
            return "CLOSE";
        }

        if(hasEnterOrExitCall()) {
            return "STOP";
        }
        if(status.equals("UPWARD")) {
            return "UP";
        }
        return "DOWN";
    }

    private List<Integer> callIds() {
        if(status.equals("OPENED") && hasExitCall()) {
            return passengers.stream().filter(p -> p.getEnd() == floor).map(Call::getId).collect(Collectors.toList());
        }
        if(status.equals("OPENED") && hasEnterCall()) {
            return taken.stream().filter(t -> t.getStart() == floor).map(Call::getId).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private boolean isIdle() {
        return taken.size() == 0 && passengers.size() == 0;
    }

    private boolean isOverPerson() {
        int count = taken.size() + passengers.size();
        return count >= 8;
    }

    private Call nearCall() {
        int high = -1;
        int low = 987654321;
        Call highCall = null;
        Call lowCall = null;

        for(Call call : taken) {
            if(high < call.getStart()) {
                high = call.getStart();
                highCall = call;
            }
            if(low > call.getStart()) {
                low = call.getStart();
                lowCall = call;
            }
        }
        for(Call call : passengers) {
            if(high < call.getEnd()) {
                high = call.getEnd();
                highCall = call;
            }
            if(low > call.getEnd()) {
                low = call.getEnd();
                lowCall = call;
            }
        }

        if(floor < low) {
            return lowCall;
        }
        return highCall;
    }

    private boolean hasEnterOrExitCall() {
        return hasEnterCall() || hasExitCall();
    }

    private boolean hasEnterCall() {
        for(Call call : taken) {
            if(call.getStart() == floor) {
                return true;
            }
        }
        return false;
    }

    private boolean hasExitCall() {
        for(Call call : passengers) {
            if(call.getEnd() == floor) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elevator elevator = (Elevator) o;
        return id == elevator.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }
}
