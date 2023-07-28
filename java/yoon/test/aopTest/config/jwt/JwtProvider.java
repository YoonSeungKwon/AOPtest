package yoon.test.aopTest.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import yoon.test.aopTest.domain.Members;
import yoon.test.aopTest.repository.MemberRepository;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final MemberRepository memberRepository;
    private final String SECRET = "yoonseungkwon12sdjkfnweomlcmewlmclt17";
    final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    private final long exp = 30 * 60 * 1000L;

    public String createToken(Map<String, Object> claim){
        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp));

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setClaims(claims)
                .addClaims(claim)
                .signWith(secretKey)
                .compact();
    }

    public String get(String token, String key){
        return (String)Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get(key);
    }

    public Authentication getAuthentication(String token){
        Members member = memberRepository.findMembersByEmail(get(token, "email"));
        return new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
    }

    public boolean validateToken(String token){
        try{
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build()
                    .parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer")){
            return token.substring(7);
        }
        return null;
    }
}
