package com.example.atry;

import java.util.Arrays;
import java.util.List;

public class FilterItemLlist {


     static List<String>L= Arrays.asList("Location", "Money", "Date","Agency");
    static List<String>L2= Arrays.asList("Tourloc", "Tourtk", "Tourtime","Agency");
    static List<String>L3= Arrays.asList("Agency","TourName","Tourdetails","Tourloc", "Tourtk", "Tourtime");


    public  static List<String> getList(){

          return L;
     }
     public  static String getItemName( int i){

         return  L.get(i);
     }
     public  static String getItemName2( int i){

         return  L2.get(i);
     }

    public static int KMP( String pat,String str){


        pat=pat.trim().toLowerCase().trim();
        str=str.trim().toLowerCase().trim();

        System.out.print ("LLLL3"+ pat+ ": "+str);
        int i=1, len=0;
        int[] lps = new int[pat.length()+5];
        lps[0]=0;
        while(i<pat.length()){
            if(pat.charAt(i)==pat.charAt(len)){
                lps[i]=len+1;
                len++;
                i++;
            }
            else{
                if(len>0) len=lps[len-1];
                else { lps[i]=0;i++;}
            }
        }

        i=0 ;
        int j=0 ; int ret=0;
        while(i<str.length() && j<pat.length() ){

            if(str.charAt(i)==pat.charAt(j) ) {i++;j++;

            ret=Math.max(j,ret);
            }
            else{
                if(j>0) j=lps[j-1];
                else i++;
            }
            ///if(j==strlen(pat)) return j;
        }

        System.out.println(" +++ "+ret);
        return ret;
    }






}
