package com.example.dexiesyncbackend.dto.sync;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum DatabaseChangeTypeEnum {
    CREATE(1), UPDATE(2), DELETE(3);

    private static final Map<Integer, DatabaseChangeTypeEnum> map = new HashMap<>();
    private final Integer value;

    static {
        for (DatabaseChangeTypeEnum type : values()) {
            map.put(type.value, type);
        }
    }

    DatabaseChangeTypeEnum(Integer value) {
        this.value = value;
    }

    @JsonValue
    public Integer getValue() {
        return value;
    }

    @JsonCreator
    public static DatabaseChangeTypeEnum valueOf(final Integer value) {
        return map.get(value);
    }
}
