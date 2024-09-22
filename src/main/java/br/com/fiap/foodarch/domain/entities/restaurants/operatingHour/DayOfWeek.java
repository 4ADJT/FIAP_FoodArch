package br.com.fiap.foodarch.domain.entities.restaurants.operatingHour;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DayOfWeek {
  Sunday("Sunday"),
  Monday("Monday"),
  Tuesday("Tuesday"),
  Wednesday("Wednesday"),
  Thursday("Thursday"),
  Friday("Friday"),
  Saturday("Saturday");

  private final String value;

}