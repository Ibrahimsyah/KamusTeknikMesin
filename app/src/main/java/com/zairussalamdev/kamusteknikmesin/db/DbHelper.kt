package com.zairussalamdev.kamusteknikmesin.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.zairussalamdev.kamusteknikmesin.model.Kategori
import com.zairussalamdev.kamusteknikmesin.model.Materi
import org.jetbrains.anko.db.*

class DbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "DBKamus", null, 1) {
    companion object {
        private var instance: DbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DbHelper {
            if (instance == null) {
                instance = DbHelper(ctx.applicationContext)
            }
            return instance as DbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            Kategori.TABLE_KATEGORI, true,
            Kategori.ID_KATEGORI to INTEGER + PRIMARY_KEY,
            Kategori.NAME to TEXT
        )
        db?.createTable(
            Materi.TABLE_MATERI, true,
            Materi.ID_MATERI to INTEGER + PRIMARY_KEY,
            Materi.ID_KATEGORI to INTEGER,
            Materi.TITLE to TEXT,
            Materi.CONTENT to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable(Kategori.TABLE_KATEGORI, true)
        db?.dropTable(Materi.TABLE_MATERI, true)

    }
}

val Context.db: DbHelper
    get() = DbHelper.getInstance(applicationContext)