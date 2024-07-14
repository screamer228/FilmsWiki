package lilianisoft.test_task.filmswiki.presentation.mapper

import lilianisoft.test_task.filmswiki.domain.entity.MovieEntity
import lilianisoft.test_task.filmswiki.domain.entity.MoviesPageEntity
import lilianisoft.test_task.filmswiki.presentation.model.Movie
import lilianisoft.test_task.filmswiki.presentation.model.MoviesPage
import lilianisoft.test_task.filmswiki.presentation.utils.StringUtils

class MoviesMapper {

    fun mapDtoToUiPage(entity: MoviesPageEntity): MoviesPage {
        return MoviesPage(
            page = entity.page,
            movieList = entity.movieList.map {
                mapDtoToUi(it)
            },
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    fun mapDtoToUi(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            releaseYear = StringUtils.getYearFromDate(entity.releaseDate),
            overview = entity.overview,
            posterUrl = StringUtils.getFullPosterUrl(entity.posterPath)
        )
    }
}