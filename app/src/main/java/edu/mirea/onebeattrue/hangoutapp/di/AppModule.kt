package edu.mirea.onebeattrue.hangoutapp.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.mirea.onebeattrue.hangoutapp.data.AuthRepositoryImpl
import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository

//@InstallIn(SingletonComponent::class)
//@Module
//class AppModule {
//    @Provides
//    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
//
//    @Provides
//    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
//}