package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * Crop Dto.
 *
 * @param id id.
 * @param name name.
 * @param plantedArea plantedArea.
 * @param farmId farmId.
 * @param harvestDate harvestDate.
 * @param plantedDate plantedDate.
 */
public record CropDto(Long id, String name, Double plantedArea,
                      LocalDate plantedDate, LocalDate harvestDate, Long farmId) {
  public Crop toCrop() {
    return new Crop(id, name, plantedArea, plantedDate,
        harvestDate, null, null);
  }

  public static CropDto toDto(Crop crop) {
    return new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
        crop.getPlantedDate(), crop.getHarvestDate(), crop.getFarm().getId());
  }
}
