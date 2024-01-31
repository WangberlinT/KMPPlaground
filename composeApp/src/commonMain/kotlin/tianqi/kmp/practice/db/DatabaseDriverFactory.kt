package tianqi.kmp.practice.db

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

expect fun provideDatabaseDriverFactory(): DatabaseDriverFactory