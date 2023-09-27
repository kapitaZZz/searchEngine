package searchengine.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import searchengine.dto.exception.CurrentRuntimeException;
import searchengine.dto.response.ResultDTO;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NullPointerException.class)
    public ResultDTO nullPointerException(NullPointerException exception) {
        return new ResultDTO(true, exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CurrentRuntimeException.class)
    public ResultDTO handlerInterruptedException(CurrentRuntimeException exception) {
        return new ResultDTO(true, exception.getMessage(), HttpStatus.OK);
    }
}
