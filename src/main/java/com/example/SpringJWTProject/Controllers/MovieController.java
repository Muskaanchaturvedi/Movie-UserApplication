package com.example.SpringJWTProject.Controllers;

import com.example.SpringJWTProject.Configuration.SuccessResponse;
import com.example.SpringJWTProject.ExceptionHandler.IdException;
import com.example.SpringJWTProject.ExceptionHandler.MovieException;
import com.example.SpringJWTProject.Model.Movie;
import com.example.SpringJWTProject.Model.PayloadValidation;
import com.example.SpringJWTProject.Services.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService){
        this.movieService=movieService;
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse> saveMovie(@RequestBody Movie movie)throws IdException{
        if(!PayloadValidation.payloadValidation(movie)){
            throw new IdException("Undefined ObjectId");
        }
        System.out.println(movie);
        return movieService.insertMovie(movie);
    }

    @GetMapping("/get-movies")
    public List<Movie> getMovie(){
        return movieService.getAllMovies();
    }

    @GetMapping("/get/{id}")
    public Movie getById(@PathVariable String id)throws MovieException {
        ObjectId _id= new ObjectId(id);
        return movieService.getMovieById(_id);
    }

    @DeleteMapping("/del/{id}")
    public void deleteById(@PathVariable String id){
        ObjectId _id= new ObjectId(id);
        movieService.deleteMovie(_id);
    }

    @PutMapping("/update/{id}")
    public Movie update(@PathVariable String id, @RequestBody Movie movie){
        ObjectId _id= new ObjectId(id);
        return movieService.updateMovie(_id,movie) ;
    }
}
