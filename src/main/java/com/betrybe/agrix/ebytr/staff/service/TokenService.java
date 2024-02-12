package com.betrybe.agrix.ebytr.staff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Token Service.
 */
@Service
public class TokenService {
  private  final Algorithm algorithm;

  public TokenService(@Value("${api.security.token.secret}") String jwtSecret) {
    this.algorithm = Algorithm.HMAC256(jwtSecret);
  }

  /**
   * Generate token.
   *
   * @param person person.
   * @return return.
   */
  public String generateToken(UserDetails person) {
    return JWT.create().withSubject(person.getUsername())
        .withExpiresAt(generateExpiration()).sign(algorithm);
  }

  /**
   * Validate token.
   *
   * @param token token.
   * @return return.
   */
  public String validateToken(String token) {
    return JWT.require(algorithm).build().verify(token).getSubject();
  }

  private Instant generateExpiration() {
    return Instant.now().plus(2, ChronoUnit.HOURS);
  }
}
