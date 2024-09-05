package com.herupray.favorite.di

import android.content.Context
import com.herupray.favorite.ui.favorite.FavoriteFragment
import com.herupray.myapplication.di.FavoriteModule
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModule::class])
interface FavoriteComponent{
    fun inject(activity: FavoriteFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context:Context): Builder
        fun appDependencies(favoriteModule:FavoriteModule): Builder
        fun build(): FavoriteComponent
    }
}