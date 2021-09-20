package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.Objects;

@Getter @Setter
public class Call {
    private int id;
    private int timestamp;
    private int start;
    private int end;

    public boolean isUpward() {
        return end > start;
    }

    public boolean isDownward() {
        return !isUpward();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Call call = (Call) o;
        return id == call.id;
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
