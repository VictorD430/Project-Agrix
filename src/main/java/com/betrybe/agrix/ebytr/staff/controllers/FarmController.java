package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controllers.dto.FarmDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;
  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * new farm.
   *
   * @param farmDto farmDto.
   * @return return.
   */
  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.createFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(FarmDto.toDto(newFarm));
  }

  /**
   * get Farms.
   *
   * @return return.
   */
  @GetMapping
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();
    List<FarmDto> allFarmsDto = allFarms.stream().map(farm -> FarmDto.toDto(farm)).toList();
    return ResponseEntity.ok(allFarmsDto);
  }

  /**
   * get Farms by id.
   *
   * @param farmId farmId.
   * @return return.
   */
  @GetMapping("/{farmId}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long farmId) {
    Farm farm = farmService.getFarmById(farmId);
    return ResponseEntity.ok(farm);
  }

  /**
   * post farmId/crops.
   *
   * @param farmId farmId.
   * @param cropDto cropDto.
   * @return return.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(@PathVariable Long farmId,
      @RequestBody CropDto cropDto) {
    Crop newCrop = farmService.createCrop(farmId, cropDto.toCrop());
    return ResponseEntity.status(HttpStatus.CREATED).body(CropDto.toDto(newCrop));
  }

  /**
   * get farmId/crops.
   *
   * @param farmId farmId.
   * @return return.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getCropsByFarmId(@PathVariable Long farmId) {
    List<Crop> crops = farmService.getCropsByFarmId(farmId);
    List<CropDto> cropsDto = crops.stream().map(crop -> CropDto.toDto(crop)).toList();
    return ResponseEntity.ok(cropsDto);
  }
}
