package lilianisoft.test_task.filmswiki.presentation.navigation

sealed class NavigationEvent {
    data class Back(val destination: Int? = null) : NavigationEvent()
    data class ToMovieDetail(val movieId: Int) : NavigationEvent()
    // Другие события навигации, если необходимо
}