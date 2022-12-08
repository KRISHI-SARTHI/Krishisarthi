package com.krishi_sarthi.springmongodb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.krishi_sarthi.springmongodb.model.TodoDTO;
import com.krishi_sarthi.springmongodb.repository.TodoRepository;

@RestController
public class TodoController {

	@Autowired
	private TodoRepository todoRepo;
	
	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos(){
		List<TodoDTO> todos = todoRepo.findAll();
		if(todos.size() > 0) {
			return new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No todos available", HttpStatus.NOT_FOUND);
		}
	}

 @PostMapping("/todos")
 public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo){
	 try {
		 todo.setcreatedAt(new Date(System.currentTimeMillis()));
		 todoRepo.save(todo);
		 return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
	 }
	 catch (Exception e) {
		 return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	 }
 }
 
 @GetMapping("/todos/{id}")
 public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id){
	 Optional<TodoDTO> todoOptional = todoRepo.findById(id);
	 if (todoOptional.isPresent()) {
		 return new ResponseEntity<>(todoOptional.get()HttpStatus.OK);
	 }else {
		 return new ResponseEntity("Todo not found with id"+id, HttpStatus.Not_Found);
	 }
 }
 
 @PutMapping("/todos/{id}")
 public ResponseEntity<?> updatedById(@PathVariable("id") String id, @RequestBody TodoDTO todo){
	 Optional<TodoDTO> todoOptional = todoRepo.findById(id);
	 if (todoOptional.isPresent()) {
		 todoDTO todoSave = todoOptional.get();
		 todoToSave.setCompleted(todo.getCompleted() != null ? todo.getCompleted() : todoToSave.getCompleted());
		 todoToSave.setTodo(todo.getTodo() != null ? todo.getTodo() : todoToSave.getTodo());
		 todoToSave.setDescription(todo.getDescription() != null ? todo.getDescription() : todoToSave.getDescription());
		 todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
		 todoRepo.save(todoToSave);
		 return new ResponseEntity<>(todoToSave, HttpStatus.OK);
	 }else {
		 return new ResponseEntity<>("Todo not found with id "+id, HttpStatus.NOT_FOUND);
	 }
	 
	 }else {
		 return new ResponseEntity<>("Todo not found with id"+id, HttpStatus.NOT_FOUND);
	 }
  }

 public ResponseEntity<T> deletedById(@PathVariable("id") String id){
	 public ResponseEntity<T> deletedById(@PathVariable("id") String id){
		 try {
			 todoRepo.deletedById(id);
			 return new ResponseEntity<>("Successfully deleted with id " +id, HttpStatus.OK);
		 } catch (Exception e) {
			 return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		 }
	 }
 
 
 
 
 
 
 
 
 
 
}