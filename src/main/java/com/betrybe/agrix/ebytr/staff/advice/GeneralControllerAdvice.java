package com.betrybe.agrix.ebytr.staff.advice;

import com.betrybe.agrix.ebytr.staff.exception.CropException;
import com.betrybe.agrix.ebytr.staff.exception.FarmException;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller Advices.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  @ExceptionHandler(FarmException.class)
  public ResponseEntity<String> handleFarmNotFound(FarmException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }

  @ExceptionHandler(CropException.class)
  public ResponseEntity<String> handleCropNotFound(CropException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
  }

  @ExceptionHandler(FertilizerException.class)
  public ResponseEntity<String> handleFertilizerNotFound(FertilizerException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
  }

  @ExceptionHandler
  public ResponseEntity<String> handleGenericException(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno!");
  }
}
