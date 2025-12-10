package com.mana.app.params;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PlayerParams(@NotNull(message="cannot be null") String name,
                           @Min(13) Integer age
) {
    public String getName() {
        return this.name;
    }
    public Integer getAge() {
        return this.age;
    }
}
