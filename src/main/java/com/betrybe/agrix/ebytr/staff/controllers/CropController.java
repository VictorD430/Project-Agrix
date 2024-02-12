package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controllers.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * get all crops.
   *
   * @return return.
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> allCrops = cropService.getAllCrops();
    List<CropDto> allCropsDto = allCrops.stream().map(crop -> CropDto.toDto(crop)).toList();
    return ResponseEntity.ok(allCropsDto);
  }

  /**
   * get crop by id.
   *
   * @param cropId cropId.
   * @return return.
   */
  @GetMapping("/{cropId}")
  public  ResponseEntity<CropDto> getCropById(@PathVariable Long cropId) {
    Crop crop = cropService.getCropById(cropId);
    return ResponseEntity.ok(CropDto.toDto(crop));
  }

  /**
   * get crop by harvest date.
   *
   * @param start start.
   * @param end end.
   * @return return.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropByHarvestDate(@RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    List<Crop> crops = cropService.getByHarvestDate(start, end);
    List<CropDto> cropDtos = crops.stream().map(crop -> CropDto.toDto(crop)).toList();
    return ResponseEntity.ok(cropDtos);
  }

  /**
   * Post fertilizer to crop.
   *
   * @param cropId cropId.
   * @param fertilizerId fertilizerId.
   * @return return.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> fertilizerToCrop(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    cropService.fertilizerToCrop(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Get crop/fertilizers.
   *
   * @param cropId cropId.
   * @return return.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizersByCropId(@PathVariable Long cropId) {
    List<Fertilizer> fertilizers = cropService.getFertilizersByCropId(cropId);
    List<FertilizerDto> fertilizerDtos = fertilizers
        .stream().map(fertilizer -> FertilizerDto.toDto(fertilizer)).toList();
    return ResponseEntity.ok(fertilizerDtos);
  }
}
