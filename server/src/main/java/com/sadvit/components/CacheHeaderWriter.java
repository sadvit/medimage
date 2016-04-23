package com.sadvit.components;

import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sadvit on 4/23/16.
 */
public class CacheHeaderWriter extends StaticHeadersWriter {

    public CacheHeaderWriter() {
        super(createHeaders());
    }

    private static List<Header> createHeaders() {
        List<Header> headers = new ArrayList<Header>(2);
        headers.add(new Header("Cache-Control", "max-age=86400"));
        return headers;
    }

}
