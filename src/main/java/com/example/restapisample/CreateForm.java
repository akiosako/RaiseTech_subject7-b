package com.example.restapisample;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateForm {
    @NotEmpty(message = "入力してください。")
    @Size(max = 20)
    private String name;

}
