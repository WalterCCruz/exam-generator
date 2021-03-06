package br.com.devdojo.exam.generator.security.filter;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class constants {

    public static final String SECRET = "secre";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 864000000L;//1 day


    //Só p imprimir a conversao de milliseconds para dias.
    public static void main (String[] args){
        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
        System.out.println(new BCryptPasswordEncoder().encode("devdojo"));
    }



}
