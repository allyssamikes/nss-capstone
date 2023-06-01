package utils;

import java.util.UUID;

public class UniqueId {

    public String generate() {
        return UUID.randomUUID().toString();
    }
}

