package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;

/**
 * Fertilizer Dto.
 *
 * @param id id.
 * @param name name.
 * @param brand brand.
 * @param composition composition.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {
  public Fertilizer toFertilizer() {
    return new Fertilizer(id, name, brand, composition, null);
  }

  public static FertilizerDto toDto(Fertilizer fertilizer) {
    return new FertilizerDto(fertilizer.getId(), fertilizer.getName(),
        fertilizer.getBrand(), fertilizer.getComposition());
  }
}
