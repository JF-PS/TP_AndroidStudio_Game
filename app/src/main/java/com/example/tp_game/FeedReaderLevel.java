package com.example.tp_game;

import android.provider.BaseColumns;

public final class FeedReaderLevel {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderLevel() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "level_table";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ROWS = "rows";
        public static final String COLUMN_COLS = "cols";
        public static final String COLUMN_RECORD_TIME = "recordTime";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_ACHIEVED = "achieved";
    }
}
