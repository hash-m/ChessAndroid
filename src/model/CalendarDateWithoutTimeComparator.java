/**
 * @version 1.0
 */
package model;

import java.util.Calendar;
import java.util.Comparator;

/**
 * @author Jasmine Feng and Risham Chokshi
 */
public class CalendarDateWithoutTimeComparator implements Comparator<ReplayData> {
	/**
	 * @param cal for the photo
	 * @param calsecond for the photo
	 * @return int for comparision
	 * */
	public int compare(ReplayData cal, ReplayData calsecond) {
       Calendar cal1 = cal.getTimestamp();
       Calendar cal2 = calsecond.getTimestamp();
       int temp;
		if(cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
            temp =  cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        } else if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
            temp = cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
        }
        else 
        	temp = cal1.get(Calendar.DAY_OF_MONTH) - cal2.get(Calendar.DAY_OF_MONTH);
		//System.out.println(temp);
		if(temp > 0) return 1;
		else if (temp<0) return -1;
		else{
			//everything is the same, check the time
			if(cal1.get(Calendar.HOUR)!=cal2.get(Calendar.HOUR))
				temp = cal1.get(Calendar.HOUR) - cal2.get(Calendar.HOUR);
			else if(cal1.get(Calendar.MINUTE)!=cal2.get(Calendar.MINUTE))
				temp = cal1.get(Calendar.MINUTE)-cal2.get(Calendar.MINUTE);
			else
				temp = cal1.get(Calendar.SECOND)-cal2.get(Calendar.SECOND);
			
			return temp;
		}
		
    }
	
	/**
	 * @param cal1 for calendar
	 * @param cal2 for calendar
	 * @return int for comparison 
	 * */
	public static int comparedate(Calendar cal1, Calendar cal2) {
	      int temp;
			if(cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
	            temp =  cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
	        } else if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
	            temp = cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
	        }
	        else 
	        	temp = cal1.get(Calendar.DAY_OF_MONTH) - cal2.get(Calendar.DAY_OF_MONTH);
			
			if(temp > 0) return 1;
			else if (temp<0) return -1;
			else{
				//everything is the same, check the time
				if(cal1.get(Calendar.HOUR)!=cal2.get(Calendar.HOUR))
					temp = cal1.get(Calendar.HOUR) - cal2.get(Calendar.HOUR);
				else if(cal1.get(Calendar.MINUTE)!=cal2.get(Calendar.MINUTE))
					temp = cal1.get(Calendar.MINUTE)-cal2.get(Calendar.MINUTE);
				else
					temp = cal1.get(Calendar.SECOND)-cal2.get(Calendar.SECOND);
				
				return temp;
			}
	    }
	
	/**
	 * @param cal1 for the calendar
	 * @return int for errors
	 * */

	public static int sanityCheck(Calendar cal1){
		if(cal1.get(Calendar.MONTH)==Calendar.JANUARY || cal1.get(Calendar.MONTH)==Calendar.MARCH || cal1.get(Calendar.MONTH)==Calendar.MAY || cal1.get(Calendar.MONTH)==Calendar.JULY || cal1.get(Calendar.MONTH)==Calendar.AUGUST || cal1.get(Calendar.MONTH)==Calendar.OCTOBER || cal1.get(Calendar.MONTH)==Calendar.DECEMBER ){
			return 31 - cal1.get(Calendar.DAY_OF_MONTH);
		}
		else if(cal1.get(Calendar.MONTH)==Calendar.FEBRUARY){
			//CHECK IF ITS A LEAP YEAR OR NOT
			if((cal1.get(Calendar.YEAR)%4==0)){
				if((cal1.get(Calendar.YEAR)%100 == 0)){
					if((cal1.get(Calendar.YEAR)%400==0)){
						//leap year
						return 29 - cal1.get(Calendar.DAY_OF_MONTH);
					}
					else
						//not a leap year
						return 28 - cal1.get(Calendar.DAY_OF_MONTH);
				}
				else
					//leap year
					return 29 - cal1.get(Calendar.DAY_OF_MONTH);
				
			}
			else
				//not a leap year
				 return 28 - cal1.get(Calendar.DAY_OF_MONTH);
		}
		
		else if(cal1.get(Calendar.MONTH)==Calendar.APRIL || cal1.get(Calendar.MONTH)==Calendar.JUNE || cal1.get(Calendar.MONTH)==Calendar.SEPTEMBER ||cal1.get(Calendar.MONTH)==Calendar.NOVEMBER ){
			return 30 - cal1.get(Calendar.DAY_OF_MONTH);
		}
		
		
	return -1;
	}


}
