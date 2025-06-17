package ru.netology.springbootrest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import ru.netology.springbootrest.exceptions.InvalidCredentials;
import ru.netology.springbootrest.exceptions.UnauthorizedUser;
/*
Требования к обработчикам ошибок:
на InvalidCredentials он должен отсылать обратно клиенту HTTP-статус с кодом 400 и телом в виде сообщения из exception;
на UnauthorizedUser он должен отсылать обратно клиенту HTTP-статус с кодом 401 и телом в виде сообщения из exception и писать в консоль сообщение из exception.
*/
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> invalidCredentialsHandler(InvalidCredentials e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> unauthorizedUserHandler(UnauthorizedUser e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
