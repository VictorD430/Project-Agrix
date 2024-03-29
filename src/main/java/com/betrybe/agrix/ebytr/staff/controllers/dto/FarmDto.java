package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.entity.Farm;

/**
 * Farm dto.
 *
 * @param id id.
 * @param name name.
 * @param size size.
 */
public record FarmDto(Long id, String name, Double size) {
  public Farm toFarm() {
    return new Farm(id, name, size, null);
  }

  public static FarmDto toDto(Farm farm) {
    return new FarmDto(farm.getId(), farm.getName(), farm.getSize());
  }
}
