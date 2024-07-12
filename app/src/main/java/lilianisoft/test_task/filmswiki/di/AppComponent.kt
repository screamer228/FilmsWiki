package lilianisoft.test_task.filmswiki.di

import dagger.Component
import lilianisoft.test_task.filmswiki.host.HostActivity
import lilianisoft.test_task.filmswiki.presentation.detail_fragment.DetailMovieFragment
import lilianisoft.test_task.filmswiki.presentation.favorites_fragment.FavoriteMoviesFragment
import lilianisoft.test_task.filmswiki.presentation.popular_fragment.PopularMoviesFragment
import lilianisoft.test_task.filmswiki.presentation.search_fragment.SearchMoviesFragment

@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {

    fun injectHostActivity(hostActivity: HostActivity)
    fun injectPopularMoviesFragment(popularMoviesFragment: PopularMoviesFragment)
    fun injectFavoriteMoviesFragment(favoriteMoviesFragment: FavoriteMoviesFragment)
    fun injectSearchMoviesFragment(searchMoviesFragment: SearchMoviesFragment)
    fun injectDetailMovieFragment(detailMovieFragment: DetailMovieFragment)
}