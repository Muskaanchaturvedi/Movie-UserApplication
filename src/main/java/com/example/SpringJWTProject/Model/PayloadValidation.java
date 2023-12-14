package com.example.SpringJWTProject.Model;

public class PayloadValidation {
    public static boolean payloadValidation(Movie movie){
        if(movie.get_id()!=null){
            return false;
        }
        return true;
    }
}
