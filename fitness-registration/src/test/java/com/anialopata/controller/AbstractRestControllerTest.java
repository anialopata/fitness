package com.anialopata.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Ania on 2018-08-01.
 */
public class AbstractRestControllerTest {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}