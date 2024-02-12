package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dto.PersonCreationDto;
import com.betrybe.agrix.ebytr.staff.controllers.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping
  public ResponseEntity<PersonDto> createPerson(@RequestBody PersonCreationDto personCreationDto) {
    Person newPerson = personService.create(personCreationDto.toPerson());
    return ResponseEntity.status(HttpStatus.CREATED).body((PersonDto.toPersonDto(newPerson)));
  }
}
