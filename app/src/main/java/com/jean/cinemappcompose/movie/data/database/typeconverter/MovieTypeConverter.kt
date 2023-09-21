package com.jean.cinemappcompose.movie.data.database.typeconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.util.joinIntoString
import androidx.room.util.splitToIntList
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.movie.domain.models.MovieType

@ProvidedTypeConverter
class MovieTypeConverter {

    @TypeConverter
    fun toEnumType(value: String) = enumValueOf<MovieType>(value)

    @TypeConverter
    fun fromEnumType(value: MovieType) = value.name

    @TypeConverter
    fun fromGenreList(genres: List<Int>): String {
        return joinIntoString(genres) ?: EMPTY_STRING
    }

    @TypeConverter
    fun toGenreList(value: String): List<Int> {
        return splitToIntList(value) ?: emptyList()
    }

}