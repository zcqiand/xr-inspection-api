package com.nanrong.inspection.dto.todo;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class TodoUpdateDto {
  @NotBlank(message = "Description cannot be empty")
  @Size(max = 255, message = "Description cannot exceed 255 characters")
  private String description;

  @NotNull(message = "Completion status cannot be null")
  private Boolean isCompleted;
}
