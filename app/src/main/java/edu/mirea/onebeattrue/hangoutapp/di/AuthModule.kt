package edu.mirea.onebeattrue.hangoutapp.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import edu.mirea.onebeattrue.hangoutapp.data.AuthRepositoryImpl
import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository

@DisableInstallInCheck
@Module
class AuthModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}