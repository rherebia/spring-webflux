package br.com.rbh.springwebflux.resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rbh.springwebflux.entity.Todo;
import br.com.rbh.springwebflux.repository.TodoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("todos")
public class TodoResource {

	private TodoRepository todoRepository;  

    public TodoResource(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @PostMapping()  
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody  
    Mono<Todo> add(@RequestBody Todo todo) {  
        return todoRepository.save(todo);  
    }  

    @GetMapping
    public @ResponseBody  
    Flux<Todo> getAll() {  
        return todoRepository.findAll();  
    }
    
    @GetMapping("/{id}")
    public Mono<Todo> getById(@PathVariable("id") Long id) {
        return todoRepository.findById(id);
    }
    
    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable("id") Long id) {
        return todoRepository.deleteById(id);
    }
}
