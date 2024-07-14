package lilianisoft.test_task.filmswiki.data.mapper

import lilianisoft.test_task.filmswiki.data.dto.MovieDto
import lilianisoft.test_task.filmswiki.data.dto.MoviesPageDto
import lilianisoft.test_task.filmswiki.domain.entity.MovieEntity
import lilianisoft.test_task.filmswiki.domain.entity.MoviesPageEntity

class MoviesEntityMapper {

    fun mapDtoToUiPage(dto: MoviesPageDto): MoviesPageEntity {
        return MoviesPageEntity(
            page = dto.page,
            movieList = dto.movieList.map {
                mapDtoToUi(it)
            },
            totalPages = dto.totalPages,
            totalResults = dto.totalResults
        )
    }

    fun mapDtoToUi(dto: MovieDto): MovieEntity {
        return MovieEntity(
            id = dto.id,
            title = dto.title,
            releaseDate = dto.releaseDate,
            overview = dto.overview,
            posterPath = dto.posterPath
        )
    }
}