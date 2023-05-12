package com.portfolio.dtos;

public class ProyectDto {
  private Long id;
  private Long user_id;
  private String name;
  private String description;
  private String image;
  private String link_production;
  private String link_github;
  
  // constructor
  public ProyectDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getLink_production() {
    return link_production;
  }

  public void setLink_production(String link_production) {
    this.link_production = link_production;
  }

  public String getLink_github() {
    return link_github;
  }

  public void setLink_github(String link_github) {
    this.link_github = link_github;
  }
}
