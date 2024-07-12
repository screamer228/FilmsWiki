package lilianisoft.test_task.filmswiki.presentation.mapper

import lilianisoft.test_task.filmswiki.data.dto.MovieDto
import lilianisoft.test_task.filmswiki.data.dto.MoviesPageDto
import lilianisoft.test_task.filmswiki.presentation.model.Movie
import lilianisoft.test_task.filmswiki.presentation.model.MoviesPage
import lilianisoft.test_task.filmswiki.presentation.utils.StringUtils

class MoviesMapper {

    fun mapDtoToUiPage(entity: MoviesPageDto): MoviesPage {
        return MoviesPage(
            page = entity.page,
            movieList = entity.movieList.map{
                mapDtoToUi(it)
            },
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    fun mapDtoToUi(entity: MovieDto): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            releaseDate = StringUtils.getYearFromDate(entity.releaseDate),
            overview = entity.overview,
            posterUrl = StringUtils.getFullPosterUrl(entity.posterPath)
        )
    }
}