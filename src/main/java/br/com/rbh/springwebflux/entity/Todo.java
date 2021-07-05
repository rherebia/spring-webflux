package br.com.rbh.springwebflux.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data  
@AllArgsConstructor  
@NoArgsConstructor
public class Todo {

	private String description;
}
