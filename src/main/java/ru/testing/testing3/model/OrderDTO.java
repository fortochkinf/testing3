package ru.testing.testing3.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDTO {

    private Long id;

    @Size(max = 255)
    private String comment;

    private Long creator;

}
