package br.com.devdojo.exam.generator.security.filter;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class constants {

    public static final String SECRET = "secre";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 864000000L;//1 day


    public static void main (String[] args){
        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }



}
