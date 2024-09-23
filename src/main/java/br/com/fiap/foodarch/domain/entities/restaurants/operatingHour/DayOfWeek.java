package br.com.fiap.foodarch.domain.entities.restaurants.operatingHour;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DayOfWeek {
  SUNDAY("SUNDAY"),
  MONDAY("MONDAY"),
  TUESDAY("TUESDAY"),
  WEDNESDAY("WEDNESDAY"),
  THURSDAY("THURSDAY"),
  FRIDAY("FRIDAY"),
  SATURDAY("SATURDAY");

  private final String value;

}