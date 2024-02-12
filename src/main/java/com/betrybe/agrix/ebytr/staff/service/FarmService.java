package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.exception.FarmException;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
import com.betrybe.agrix.ebytr.staff.repository.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Farm service.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Get farm by id.
   *
   * @param farmId farmId.
   * @return return.
   */
  public Farm getFarmById(Long farmId) {
    Optional<Farm> farmOptional = farmRepository.findById(farmId);
    if (farmOptional.isEmpty()) {
      throw new FarmException();
    }
    return farmOptional.get();
  }

  /**
   * new crop.
   *
   * @param farmId farmId.
   * @param crop crop.
   * @return return.
   */
  public Crop createCrop(Long farmId, Crop crop) {
    Optional<Farm> farmOptional = farmRepository.findById(farmId);
    if (farmOptional.isEmpty()) {
      throw new FarmException();
    }

    Farm farm = farmOptional.get();
    crop.setFarm(farm);

    return cropRepository.save(crop);
  }

  /**
   * get farmId/Crops.
   *
   * @param farmId farmId.
   * @return return.
   */
  public List<Crop> getCropsByFarmId(Long farmId) {
    Optional<Farm> farmOptional = farmRepository.findById(farmId);
    if (farmOptional.isEmpty()) {
      throw new FarmException();
    }

    Farm farm = farmOptional.get();
    return farm.getCrops();
  }
}
