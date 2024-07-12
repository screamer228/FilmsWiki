package lilianisoft.test_task.filmswiki.presentation.popular_fragment.uievent

sealed class PopularUiEvent {
    data class ShowToast(val message: String) : PopularUiEvent()
}