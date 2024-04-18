package com.personal.todo.dao;

import com.personal.todo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoDaoRepository extends CrudRepository<Todo, Integer> {

}
