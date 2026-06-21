package com.example.portfolioapi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class JwtTokenService {

    private final JwtEncoder jwtEncoder;
    private final String issuer;
    private final long accessTokenMinutes;

    public JwtTokenService(
            JwtEncoder jwtEncoder,

            @Value("${app.jwt.issuer}")
            String issuer,

            @Value("${app.jwt.access-token-minutes}")
            long accessTokenMinutes
    ) {
        this.jwtEncoder = jwtEncoder;
        this.issuer = issuer;
        this.accessTokenMinutes = accessTokenMinutes;
    }

    public String createAccessToken(
            UUID userId,
            String email,
            String name
    ) {

        Instant now = Instant.now();

        JwtClaimsSet claims =
                JwtClaimsSet.builder()

                        .issuer(issuer)

                        .issuedAt(now)

                        .expiresAt(
                                now.plus(
                                        accessTokenMinutes,
                                        ChronoUnit.MINUTES
                                )
                        )

                        // JWTのsubjectはStringなので、
                        // Service内でUUIDをStringへ変換する
                        .subject(
                                userId.toString()
                        )

                        .claim(
                                "email",
                                email
                        )

                        .claim(
                                "name",
                                name
                        )

                        .build();

        JwsHeader header =
                JwsHeader
                        .with(
                                MacAlgorithm.HS256
                        )
                        .build();

        return jwtEncoder
                .encode(
                        JwtEncoderParameters.from(
                                header,
                                claims
                        )
                )
                .getTokenValue();
    }

    public long getExpiresInSeconds() {
        return accessTokenMinutes * 60;
    }
}