package dev.leonlatsch.photok.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.leonlatsch.photok.model.database.PhotokDatabase
import dev.leonlatsch.photok.model.database.PhotokDatabase.Companion.DATABASE_NAME
import dev.leonlatsch.photok.other.PrefManager
import dev.leonlatsch.photok.security.EncryptionManager
import javax.inject.Singleton

/**
 * Hilt Module for [ApplicationComponent].
 *
 * @since 1.0.0
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePhotokDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        PhotokDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providePhotoDao(database: PhotokDatabase) = database.getPhotoDao()

    @Provides
    @Singleton
    fun providePasswordDao(database: PhotokDatabase) = database.getPasswordDao()

    @Provides
    @Singleton
    fun providePrefManager(@ApplicationContext app: Context) = PrefManager(app)

    @Provides
    @Singleton
    fun providePasswordManager() = EncryptionManager()
}