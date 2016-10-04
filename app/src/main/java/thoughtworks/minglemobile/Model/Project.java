package thoughtworks.minglemobile.Model;

import lombok.Getter;
import lombok.experimental.Builder;

@Getter
@Builder
public class Project {
    String name;
    String identifier;

    public Project(String name, String identifier) {
        this.name = name;
        this.identifier = identifier;
    }

}
