package com.nanrong.inspection.dto.todo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {
  private Long id;
  private String description;
  private boolean isCompleted;
  private LocalDateTime creationDate;
}
