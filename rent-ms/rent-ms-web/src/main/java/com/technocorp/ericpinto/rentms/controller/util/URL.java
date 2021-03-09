package com.technocorp.ericpinto.rentms.controller.util;

import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
@NoArgsConstructor
public class URL {

    public static String decodeParam(String text){
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e){
            return "";
        }
    }
}
