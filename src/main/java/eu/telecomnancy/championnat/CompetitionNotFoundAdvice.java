package eu.telecomnancy.championnat;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CompetitionNotFoundAdvice {


    @ResponseBody
    @ExceptionHandler(CompetitionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String competitionNotFoundHandler(CompetitionNotFoundException ex){
        return ex.getMessage();
    }
}
