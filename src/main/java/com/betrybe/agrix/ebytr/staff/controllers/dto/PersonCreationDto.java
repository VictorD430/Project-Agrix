package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Person creation Dto.
 *
 * @param username username.
 * @param password password.
 * @param role role.
 */
public record PersonCreationDto(String username, String password, Role role) {

  /**
   * Person.
   *
   * @return return.
   */
  public Person toPerson() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);
    return person;
  }
}
