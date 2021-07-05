package br.com.rbh.springwebflux.resource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.rbh.springwebflux.entity.Todo;
import br.com.rbh.springwebflux.repository.TodoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(TodoResource.class)
class TodoResourceTest {
	
	@Autowired
    WebTestClient webTestClient;

    @MockBean
    private TodoRepository todoRepository;
    
    @Test
    void testGetAll() {
    	String todoDescription = "test todo";
		
		Todo todo = new Todo(todoDescription);
		
		Flux<Todo> todoMono = Flux.just(todo);

        when(todoRepository.findAll()).thenReturn(todoMono);

        webTestClient.get()
                .uri("/todos")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Todo.class)
                .contains(todo);
    }

	@Test
	void testGetTodoById() {
		String todoDescription = "test todo";
		
		Todo todo = new Todo(todoDescription);
		
		Mono<Todo> todoMono = Mono.just(todo);

        when(todoRepository.findById(1L)).thenReturn(todoMono);

        webTestClient.get()
                .uri("/todos/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Todo.class)
                .value(todoResponse -> todoResponse.getDescription(), equalTo(todoDescription));
	}

	@Test
    public void testDeleteById() {

        when(todoRepository.deleteById(1L)).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/todos/1")
                .exchange()
                .expectStatus().isOk()
                ;

    }
	
	@Test
    public void testCreate() {
        Todo todo = new Todo("test todo");
        Mono<Todo> todoMono = Mono.just(todo);
        when(todoRepository.save(todo)).thenReturn(todoMono);

        webTestClient.post()
                .uri("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(todo), Todo.class)
                .exchange()
                .expectStatus().isCreated();

    }
}
