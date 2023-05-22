package br.com.csi.musician_plataform.config;

import br.com.csi.musician_plataform.model.ErrorResponse;
import br.com.csi.musician_plataform.model.FieldError;
import br.com.csi.musician_plataform.util.NotFoundException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(final NotFoundException exception) {
        // Criação de uma instância de ErrorResponse para a exceção NotFound
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND.value()); // Define o código de status HTTP
        errorResponse.setException(exception.getClass().getSimpleName()); // Define o nome da exceção
        errorResponse.setMessage(exception.getMessage()); // Define a mensagem de erro
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); // Retorna uma resposta HTTP com o ErrorResponse e o código de status NOT_FOUND
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException exception) {
        // Obtém o BindingResult contendo os erros de validação
        final BindingResult bindingResult = exception.getBindingResult();
        // Mapeia cada FieldError para um objeto FieldError personalizado
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors()
                .stream()
                .map(error -> {
                    final FieldError fieldError = new FieldError();
                    fieldError.setErrorCode(error.getCode()); // Define o código do erro
                    fieldError.setField(error.getField()); // Define o nome do campo com erro
                    return fieldError;
                })
                .toList();
        // Criação de uma instância de ErrorResponse para a exceção MethodArgumentNotValid
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value()); // Define o código de status HTTP
        errorResponse.setException(exception.getClass().getSimpleName()); // Define o nome da exceção
        errorResponse.setFieldErrors(fieldErrors); // Define os erros de campo
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); // Retorna uma resposta HTTP com o ErrorResponse e o código de status BAD_REQUEST
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatus(
            final ResponseStatusException exception) {
        // Criação de uma instância de ErrorResponse para a exceção ResponseStatus
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(exception.getStatusCode().value()); // Define o código de status HTTP
        errorResponse.setException(exception.getClass().getSimpleName()); // Define o nome da exceção
        errorResponse.setMessage(exception.getMessage()); // Define a mensagem de erro
        return new ResponseEntity<>(errorResponse, exception.getStatusCode()); // Retorna uma resposta HTTP com o ErrorResponse e o código de status da exceção
    }
    @ExceptionHandler(Throwable.class)
    @ApiResponse(responseCode = "4xx/5xx", description = "Error")
    public ResponseEntity<ErrorResponse> handleThrowable(final Throwable exception) {
        // Imprime o stack trace da exceção
        exception.printStackTrace();
        // Criação de uma instância de ErrorResponse genérico para outras exceções
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); // Define o código de status HTTP
        errorResponse.setException(exception.getClass().getSimpleName()); // Define o nome da exceção
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Retorna uma resposta HTTP com o ErrorResponse e o código de status INTERNAL_SERVER_ERROR
    }
}


