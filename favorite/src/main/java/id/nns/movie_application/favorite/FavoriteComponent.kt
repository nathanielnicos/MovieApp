package id.nns.movie_application.favorite

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import id.nns.movie_application.di.FavoriteModuleDependencies

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModulDependecies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }

}