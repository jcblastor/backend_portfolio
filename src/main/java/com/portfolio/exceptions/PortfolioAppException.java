package com.portfolio.exceptions;

import org.springframework.http.HttpStatus;

public class PortfolioAppException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private HttpStatus state;
  private String message;

  public PortfolioAppException(HttpStatus state, String message) {
    super();
    this.state = state;
    this.message = message;
  }

  public PortfolioAppException(HttpStatus state, String message, String message1) {
    super();
    this.state = state;
    this.message = message;
    this.message = message1;
  }

  public HttpStatus getState() {
    return state;
  }

  public void setState(HttpStatus state) {
    this.state = state;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
