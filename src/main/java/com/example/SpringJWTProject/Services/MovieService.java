package com.example.SpringJWTProject.Services;

import com.example.SpringJWTProject.Configuration.SuccessResponse;
import com.example.SpringJWTProject.ExceptionHandler.MovieException;
import com.example.SpringJWTProject.Model.Movie;
import com.example.SpringJWTProject.Repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    @Autowired
    public MovieService( MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }

//    //Get Movie
//    public Movie getMovie(ObjectId id){
//        Optional<Movie> optionalMovie=movieRepository.findById(id);
//        return optionalMovie.orElseGet(optionalMovie ::get);
//    };



    //get all movie
    public List<Movie> getAllMovies(){
        List<Movie> allMovies =movieRepository.findAll();
        return allMovies;
    }
    //save the user
    public ResponseEntity<SuccessResponse> insertMovie(Movie movie){
        movieRepository.save(movie);
        SuccessResponse successResponse=new SuccessResponse();
        successResponse.setStatusCode(200);
        successResponse.setResponseMsg("Movie created Successfully!");
        successResponse.setData(movie);

        return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.ACCEPTED);
    }

    public Movie getMovieById (ObjectId id) throws MovieException {
        Optional<Movie> foundMovie=movieRepository.findById(id);
        if(foundMovie.isEmpty() || foundMovie==null){
            throw new MovieException("movie not exist");
        }
        return foundMovie.get();
    }

    public void deleteMovie(ObjectId id){
        movieRepository.deleteById(id);
    }

    //update
    public Movie updateMovie(ObjectId id,Movie updatedMovie){
        Optional <Movie> tempMovie=movieRepository.findById(id);
        if(tempMovie.isEmpty())return null;
        Movie foundmovie=tempMovie.get();
        if(updatedMovie.getName()!=null && !updatedMovie.getName().isEmpty()){
            foundmovie.setName(updatedMovie.getName());
        }
        if(updatedMovie.getReleaseDate()!=null && !updatedMovie.getReleaseDate().isEmpty()){
            foundmovie.setReleaseDate(updatedMovie.getReleaseDate());
        }
        movieRepository.save(foundmovie);
        return foundmovie;

    }
}
