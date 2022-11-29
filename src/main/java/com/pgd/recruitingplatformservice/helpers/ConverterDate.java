package com.pgd.recruitingplatformservice.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public interface ConverterDate {

    final Logger logger =  LoggerFactory.getLogger(ConverterDate.class);
    /**
     * Convert date to use in sorting data.
     * @param postDate
     * @return long
     */
    default long getPostDateValueFromParse(
            String postDate
    ) {
        SimpleDateFormat formatter =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        long postDateParsed = 0;
        try {
            Date originDate =
                    formatter
                            .parse(postDate);
            postDateParsed =
                    originDate
                            .getTime();
        } catch (ParseException ex) {
            logger.error(Arrays.toString(ex.getStackTrace()));
        }
        return postDateParsed;
    }
}
