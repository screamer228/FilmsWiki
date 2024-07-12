package lilianisoft.test_task.filmswiki.presentation.detail_fragment.uievent

sealed class DetailUiEvent {
    data class ShowToast(val message: String) : DetailUiEvent()
}