package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Person Dto.
 *
 * @param id id.
 * @param username username.
 * @param role role.
 */
public record PersonDto(Long id, String username, Role role) {

  public static PersonDto toPersonDto(Person person) {
    return new PersonDto(person.getId(), person.getUsername(), person.getRole());
  }
}
