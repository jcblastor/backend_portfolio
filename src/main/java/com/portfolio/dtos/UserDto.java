package com.portfolio.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDto {

  private Long id;

  @NotEmpty
  @Size(min = 3, message = "El nombre de usuario debe tener al menos 3 caracteres")
  private String username;

  @NotEmpty
  @Email(message = "Debe ingresar un correo valido")
  private String email;

  @NotEmpty
  @Size(min = 8, message = "La contraseña tiene que tener al menos 8 caracteres")
  private String password;
  
  private String cargo;
  private String image;

  // contructor
  public UserDto() {
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getCargo() {
    return cargo;
  }
  public void setCargo(String cargo) {
    this.cargo = cargo;
  }
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }
}
