package com.paperless.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    public static Date getExpirationTimeFromCurrentTime(Long expirationInMinutes){
        LocalDateTime localDateTime=new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime expirationLocalDateTime=localDateTime.plusMinutes(expirationInMinutes);
        Date otpExpiryTime=Date.from(expirationLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return otpExpiryTime;
    }
}
