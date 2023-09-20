package edu.mirea.onebeattrue.hangoutapp.di.auth

import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import edu.mirea.onebeattrue.hangoutapp.data.auth.AuthRepositoryImpl
import edu.mirea.onebeattrue.hangoutapp.domain.auth.AuthRepository

@DisableInstallInCheck
@Module
interface AuthModule {
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    companion object {
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    }
}