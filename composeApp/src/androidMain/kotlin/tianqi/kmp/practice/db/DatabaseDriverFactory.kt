package tianqi.kmp.practice.db


import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import tianqi.kmp.practice.MyApp

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "test.db")
    }
}

actual fun provideDatabaseDriverFactory(): DatabaseDriverFactory {
    return DatabaseDriverFactory(MyApp.instance)
}