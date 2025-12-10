package com.mana.app.entity;

import com.mana.app.params.PlayerParams;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Player {
  private String name;
  private Integer age;
  private String category;

  private LocalDateTime createdAt;

  public Player(String name, Integer age, String category) {
    this.name = name;
    this.age = age;
    this.category = category;
  }
  public Player(PlayerParams params) {
    this.name = params.getName();
    this.age = params.getAge();
    if(this.age < 18) {
      this.category = "Junior";
    } else {
      this.category = "Senior";
    }
  }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

  public Integer getAge() {
    return age;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
