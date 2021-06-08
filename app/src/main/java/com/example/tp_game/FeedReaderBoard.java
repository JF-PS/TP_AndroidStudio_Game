package com.example.tp_game;

import android.provider.BaseColumns;

public class FeedReaderBoard {
    private FeedReaderBoard() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "board_table";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_ROWS = "rows";
    }
}
