package com.portfolio.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.dtos.UserDto;
import com.portfolio.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserService userService;

  // @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
    return new ResponseEntity<UserDto>(userService.createUser(userDto), HttpStatus.CREATED);
  }

  @GetMapping
  public List<UserDto> getUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") long id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  // @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable(name = "id") long id) {
    UserDto userResp = userService.updateUser(userDto, id);
    return new ResponseEntity<UserDto>(userResp, HttpStatus.OK);
  }

  // @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable(name = "id") long id) {
    userService.deleteUser(id);
    return new ResponseEntity<String>("Usuario eliminado con exito.", HttpStatus.OK);
  }
}
