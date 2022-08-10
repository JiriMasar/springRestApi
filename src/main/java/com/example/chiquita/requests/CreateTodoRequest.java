package com.example.chiquita.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record CreateTodoRequest(
        @NotEmpty
        @Size(min = 2, max = 255, message = "Todo name must between 2 and 255 characters long")
        String label
) {
}
