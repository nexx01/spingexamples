package com.example.demo.mail;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class EmailOrder {
  
  private final String email;
  private List<SomeOrder> tacos = new ArrayList<>();

  public void addTaco(SomeOrder taco) {
    this.tacos.add(taco);
  }
  
}
