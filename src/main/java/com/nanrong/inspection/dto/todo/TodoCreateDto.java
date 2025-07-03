package com.nanrong.inspection.dto.todo;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class TodoCreateDto {
  @NotBlank(message = "Description cannot be empty")
  private String description;

  @NotNull(message = "Completion status cannot be null")
  private Boolean isCompleted;
}
