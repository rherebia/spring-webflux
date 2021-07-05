package br.com.rbh.springwebflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.rbh.springwebflux.entity.Todo;

public interface TodoRepository extends ReactiveMongoRepository<Todo, Long> {

}
