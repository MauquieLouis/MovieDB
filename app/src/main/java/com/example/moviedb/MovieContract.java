package com.example.moviedb;

import android.provider.BaseColumns;

public class MovieContract {

    private MovieContract(){}

    public static class MovieEntry implements BaseColumns{
        public static final String TABLE_NAME = "favoris";
        public static final String COLUMN_NAME_ID_FILM= "idfilm";
        public static final String COLUMN_NAME_TITRE_FILM= "titrefilm";
        public static final String COLUMN_NAME_IMG_FILM= "imgfilm";
        public static final String COLUMN_NAME_IMGBCK_FILM= "imgbckfilm";
        //public static final String COLUMN_NAME_TITLE = "title";
    }

}
