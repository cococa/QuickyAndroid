package com.commroid.util;

import java.math.BigDecimal;

/**
 *cocoaSJ
 */
public class FileSizeUtil {

	public static String getMB(Long bytes){
        BigDecimal filesize  =   new  BigDecimal(bytes);
        BigDecimal megabyte  =   new  BigDecimal( 1024 * 1024 );
         float  returnValue  =  filesize.divide(megabyte,  2 , BigDecimal.ROUND_UP).floatValue();
         if  (returnValue  >   1 )
             return (returnValue  +   "  MB " );
        BigDecimal kilobyte  =   new  BigDecimal( 1024 );
        returnValue  =  filesize.divide(kilobyte,  2 , BigDecimal.ROUND_UP).floatValue();
         return (returnValue  +   "  KB " );
		
	}
	
	
}
