package br.com.devdojo.exam.generator.security.filter;

import br.com.devdojo.exam.generator.persistence.model.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static br.com.devdojo.exam.generator.security.filter.constants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            //Aqui que deserializo Json
            ApplicationUser user = new ObjectMapper().readValue(request.getInputStream(),ApplicationUser.class);
            return  authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        ZonedDateTime expTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(EXPIRATION_TIME, ChronoUnit.MILLIS);
        String token = Jwts.builder().setSubject(((ApplicationUser) authResult.getPrincipal()).getUserName()).setExpiration
                (Date.from(expTimeUTC.toInstant())).signWith(SignatureAlgorithm.HS256,SECRET).compact();
        token = TOKEN_PREFIX+token;
        String tokenJson = "{\"token\":"+addQuotes(token) + ",\"exp\":"+addQuotes(expTimeUTC.toString())+"}";
        response.getWriter().write(tokenJson);
        response.addHeader("content-Type", "application/json;charset=UTF-8");
        response.addHeader(HEADER_STRING, token);


    }



    private String addQuotes (String value){//metodo somente que insere aspas p evitar concatenacao
        return new StringBuilder(300).append("\"").append("\"").toString();
    }

}
