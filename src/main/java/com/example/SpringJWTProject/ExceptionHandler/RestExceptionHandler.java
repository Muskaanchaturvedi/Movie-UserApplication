package com.example.SpringJWTProject.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    //NOT FOUND EXCEPTION
    @ExceptionHandler(MovieException.class)
    public ResponseEntity<ErrorResponse> ExceptionMovieHandler(Exception e){
         ErrorResponse errorResponse=new ErrorResponse();
         errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());

         errorResponse.setMsg(e.getMessage());
         return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }

    //ID NOT FOUND EXCEPTION
    @ExceptionHandler(IdException.class)
    public ResponseEntity<ErrorResponse> ExceptionIdNotPresent(Exception e){
        ErrorResponse errorResponse= new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());

        errorResponse.setMsg(e.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    //BAD REQUEST EXCEPTION
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> BadRequestException(Exception e){
        ErrorResponse errorResponse= new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());

        errorResponse.setMsg("Request can not be handled due to some mal function in the syntax");
        return  new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }



}
