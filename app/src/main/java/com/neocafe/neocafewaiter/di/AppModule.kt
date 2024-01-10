package com.neocafe.neocafewaiter.di


import android.content.Context
import com.neocafe.neocafewaiter.model.api.connections.AuthApi
import com.neocafe.neocafewaiter.model.api.connections.MenuApi
import com.neocafe.neocafewaiter.model.api.connections.NewOrderApi
import com.neocafe.neocafewaiter.model.api.connections.ProfileApi
import com.neocafe.neocafewaiter.model.api.retrofit.SessionManager
import com.neocafe.neocafewaiter.model.api.retrofit.TokenInterceptor
import com.neocafe.neocafewaiter.model.repositories.AuthRepository
import com.neocafe.neocafewaiter.model.repositories.MenuRepository
import com.neocafe.neocafewaiter.model.repositories.NewOrderRepository
import com.neocafe.neocafewaiter.model.repositories.ProfileRepository
import com.neocafe.neocafewaiter.utils.Constants
import com.neocafe.neocafewaiter.viewModels.AuthViewModel
import com.neocafe.neocafewaiter.viewModels.MenuViewModel
import com.neocafe.neocafewaiter.viewModels.NewOrderViewModel
import com.neocafe.neocafewaiter.viewModels.ProfileViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val retrofitModule = module {
    single { getSessionManager(context = androidContext()) }
    single { getInterceptor(get()) }
    single { getRetrofit(get()) }
    single { getOkHttpClient(get()) }
    single { getAuthApi(get()) }
    single { AuthRepository(get()) }
    single { getMenuApi(get()) }
    single { MenuRepository(get()) }
    single { getProfileApi(get()) }
    single { ProfileRepository(get()) }
    single { getNewOrderApi(get()) }
    single { NewOrderRepository(get()) }

}

val viewModule = module {
    viewModel { AuthViewModel(get(), get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { NewOrderViewModel(get()) }
}

fun getRetrofit(okHttpClient: OkHttpClient): Retrofit{
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient{
    return OkHttpClient.Builder()
        .addInterceptor(tokenInterceptor)
        .build()
}

fun getInterceptor(sessionManager: SessionManager): TokenInterceptor{
    return TokenInterceptor(sessionManager)
}

fun getSessionManager(context: Context): SessionManager{
    return SessionManager(context)
}

fun getAuthApi(retrofit: Retrofit): AuthApi{
    return retrofit.create(AuthApi::class.java)
}

fun getMenuApi(retrofit: Retrofit): MenuApi{
    return retrofit.create(MenuApi::class.java)
}

fun getProfileApi(retrofit: Retrofit): ProfileApi{
    return retrofit.create(ProfileApi::class.java)
}

fun getNewOrderApi(retrofit: Retrofit): NewOrderApi{
    return retrofit.create(NewOrderApi::class.java)
}