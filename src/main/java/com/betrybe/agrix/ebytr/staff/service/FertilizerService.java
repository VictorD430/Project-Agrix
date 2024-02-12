package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.exception.FertilizerException;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Fertilizer service.
 */
@Service
public class FertilizerService {
  private FertilizerRepository fertilizerRepository;

  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  /**
   * get Fertilizer by Id.
   *
   * @param fertilizerId fertilizerId.
   * @return return.
   */
  public Fertilizer getFertilizerById(Long fertilizerId) {
    Optional<Fertilizer> fertilizer = fertilizerRepository.findById(fertilizerId);
    if (fertilizer.isEmpty()) {
      throw new FertilizerException();
    }
    return fertilizer.get();
  }
}
