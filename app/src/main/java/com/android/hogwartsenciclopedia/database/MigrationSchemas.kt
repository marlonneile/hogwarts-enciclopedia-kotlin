package com.android.hogwartsenciclopedia.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN species TEXT DEFAULT '' NOT NULL""")
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN gender TEXT DEFAULT '' NOT NULL""")
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN house TEXT""")
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN date_of_birth TEXT""")
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN year_of_birth INTEGER""")
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN ancestry TEXT""")
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN eye_color TEXT""")
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN hair_color TEXT DEFAULT '' NOT NULL""")
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN patronus TEXT""")
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN hogwarts_student INTEGER DEFAULT 0 NOT NULL""")
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN hogwarts_staff INTEGER DEFAULT 0 NOT NULL""")
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN actor TEXT DEFAULT '' NOT NULL""")
        database.execSQL("""ALTER TABLE CharacterEntity ADD COLUMN alive INTEGER DEFAULT 0 NOT NULL""")
    }

}