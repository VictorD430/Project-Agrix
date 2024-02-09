package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.CropException;
import com.betrybe.agrix.exception.FertilizerException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Crop service.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * get crop by id.
   *
   * @param cropId cropId.
   * @return return.
   */
  public Crop getCropById(Long cropId) {
    Optional<Crop> cropOptional = cropRepository.findById(cropId);
    if (cropOptional.isEmpty()) {
      throw  new CropException();
    }
    return cropOptional.get();
  }

  public List<Crop> getByHarvestDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Insert fertilizer to crop.
   *
   * @param cropId cropId.
   * @param fertilizerId fertilizerId.
   */
  public void fertilizerToCrop(Long cropId, Long fertilizerId) {
    Optional<Crop> cropOptional = cropRepository.findById(cropId);
    if (cropOptional.isEmpty()) {
      throw new CropException();
    }

    Optional<Fertilizer> fertilizerOptional = fertilizerRepository.findById(fertilizerId);
    if (fertilizerOptional.isEmpty()) {
      throw new FertilizerException();
    }

    Crop crop = cropOptional.get();
    Fertilizer fertilizer = fertilizerOptional.get();
    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
  }

  public List<Fertilizer> getFertilizersByCropId(Long cropId) {
    Crop crop = cropRepository.findById(cropId).orElseThrow(CropException::new);
    return crop.getFertilizers();
  }
}
