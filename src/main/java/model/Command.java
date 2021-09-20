package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Command {
    private int elevatorId;
    private String command;
    private List<Integer> callIds;
}
