package com.portfolio.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "proyects", uniqueConstraints = { @UniqueConstraint(columnNames = {"name"} ) })
public class Proyect {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "image", nullable = true)
  private String image;

  @Column(name = "link_production", nullable = true)
  private String link_production;

  @Column(name = "link_github", nullable = false)
  private String link_github;

  // Constructor
  public Proyect() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
