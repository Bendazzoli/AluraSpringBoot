package br.com.alura.forum.config.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CampoNaoEncontradoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class})
    public ErroFormularioDTO handleEntityNotFound(EntityNotFoundException ex){
        return new ErroFormularioDTO("id", "Id informado não encontrado.");
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ErroFormularioDTO handleEmptyResultDataAccess(EmptyResultDataAccessException ex){
        return new ErroFormularioDTO("id", "Id informado não encontrado.");
    }
}
