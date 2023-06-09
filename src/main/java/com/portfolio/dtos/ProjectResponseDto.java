package com.portfolio.dtos;

import java.util.List;

public class ProjectResponseDto {
  private List<ProjectDto> content;
  private int numberPage;
  private int limitPage;
  private long totalProyects;
  private int totalPage;
  private boolean isLast;

  // constructor
  public ProjectResponseDto() {
  }

  public List<ProjectDto> getContent() {
    return content;
  }

  public void setContent(List<ProjectDto> content) {
    this.content = content;
  }

  public int getNumberPage() {
    return numberPage;
  }

  public void setNumberPage(int numberPage) {
    this.numberPage = numberPage;
  }

  public int getLimitPage() {
    return limitPage;
  }

  public void setLimitPage(int limitPage) {
    this.limitPage = limitPage;
  }

  public long getTotalProjects() {
    return totalProyects;
  }

  public void setTotalProjects(long totalProyects) {
    this.totalProyects = totalProyects;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public boolean isLast() {
    return isLast;
  }

  public void setLast(boolean isLast) {
    this.isLast = isLast;
  }
  
  
}
