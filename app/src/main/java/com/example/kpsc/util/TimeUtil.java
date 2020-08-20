package com.example.kpsc.util;

public class TimeUtil {
    public static String converttimeToString(int time){

        String timeString = null;
        return timeString;}
    public static Integer convertStingTimeToInteger(String stringTime){
        Integer time =0;
        int hh = 0;
        int mm = 0;
        int ss = 0;

        String []  arrayTime = stringTime.split(":");


        if (arrayTime.length<3) {
            mm =  Integer.parseInt(arrayTime[0]);
            ss =  Integer.parseInt(arrayTime[1]);
        }
        else{
            hh =  Integer.parseInt(arrayTime[0]);
            mm =  Integer.parseInt(arrayTime[1]);
            ss =  Integer.parseInt(arrayTime[2]);
        }


    time = (hh*3600)+mm*60 +ss;


        return time;}
}
