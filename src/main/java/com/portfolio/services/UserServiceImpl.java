package com.portfolio.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.dtos.UserDto;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.models.User;
import com.portfolio.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private UserRepository userRepository;

  // metodo para canvertir de modelo a Dto
  private UserDto convertDto(User user) {
    UserDto userDto = modelMapper.map(user, UserDto.class);
    return userDto;
  }

  // metodo para convertir de dto a modelo
  private User convertModel(UserDto userDto) {
    User user = modelMapper.map(userDto, User.class);
    return user;
  }

  @Override
  public UserDto createUser(UserDto userDto) {
    // Convertidos de Dto a modelo
    User user = convertModel(userDto);

    User newUser = userRepository.save(user);
    
    // convertimos de modelo a Dto
    UserDto userResp = convertDto(newUser);

    // retornamos la respuesta que necesitemos al front
    return userResp;
  }

  @Override
  public List<UserDto> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream().map(user -> convertDto(user)).collect(Collectors.toList());
  }

  @Override
  public UserDto getUserById(long id) {
    User user = userRepository.findById(id).orElseThrow( ()  -> new ResourceNotFoundException("user", "id", id));
    return convertDto(user);
  }

  @Override
  public UserDto updateUser(UserDto userDto, long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("usuario", "id", id));
    user.setName(userDto.getName());
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    user.setCargo(userDto.getCargo());
    user.setImage(userDto.getImage());

    User userUpdated = userRepository.save(user);

    return convertDto(userUpdated);
  }

  @Override
  public void deleteUser(long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("usuario", "id", id));
    userRepository.delete(user);
  }
}
