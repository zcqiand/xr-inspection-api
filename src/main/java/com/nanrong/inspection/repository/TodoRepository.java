package com.nanrong.inspection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nanrong.inspection.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
