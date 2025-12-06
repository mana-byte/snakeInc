package org.snakeinc.snake.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntegerWrapper {
    public Integer value;
    public IntegerWrapper(Integer value) {
        this.value = value;
    }
}
