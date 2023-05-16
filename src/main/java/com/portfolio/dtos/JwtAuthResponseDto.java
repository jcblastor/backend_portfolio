package com.portfolio.dtos;

public class JwtAuthResponseDto {
  private String accessToken;
  private String typeToken = "Bearer";
  
  public JwtAuthResponseDto(String accessToken, String typeToken) {
    super();
    this.accessToken = accessToken;
    this.typeToken = typeToken;
  }

  public JwtAuthResponseDto(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getTypeToken() {
    return typeToken;
  }

  public void setTypeToken(String typeToken) {
    this.typeToken = typeToken;
  }
}
