package com.portfolio.services;


import java.util.List;

import com.portfolio.dtos.UserDto;

public interface UserService {
  public UserDto createUser(UserDto userDto);
  public List<UserDto> getAllUsers();
  public UserDto getUserById(long id);
  public UserDto updateUser(UserDto userDto, long id);
  public void deleteUser(long id);
}
