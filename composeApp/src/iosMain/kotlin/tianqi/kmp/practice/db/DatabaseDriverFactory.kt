package tianqi.kmp.practice.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver


actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "test.db")
    }
}

actual fun provideDatabaseDriverFactory(): DatabaseDriverFactory {
    return DatabaseDriverFactory()
}