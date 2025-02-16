package academy.devdojo.springboot2.handler;

import academy.devdojo.springboot2.Exception.BadRequestException;
import academy.devdojo.springboot2.Exception.BadRequestExceptionDetails;
import academy.devdojo.springboot2.Exception.ValidationExceptionDetails;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
@Order(1)
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(
            BadRequestException bre){
        System.out.println("Handler chamado para BadRequestException");
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("bad request Exception, check documentation")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exeptionMAN){

        // Capta os dados violados
        List<FieldError> fieldErrorList = exeptionMAN.getBindingResult().getFieldErrors();

        // Percorre os dados extraidos tranformando-os em string e os separa por virgula
        String fildes = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining(", "));

        String fildesMessage = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("bad request Exception, Invalid Fileds")
                        .details("Check the fileds errors")
                        .developerMessage(exeptionMAN.getClass().getName())
                        .fields(fildes)
                        .fieldsMessage(fildesMessage)
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerConstraintViolationException(
            ConstraintViolationException exceptionCVE){

        // Captura as violações de validação (Set de ConstraintViolation)
        Set<ConstraintViolation<?>> exception = exceptionCVE.getConstraintViolations();

        // Extrai os campos que falharam na validação
        String fields = exception.stream()
                .map(violation -> violation.getMessage().toString())  // Pega o nome do campo violado
                .collect(Collectors.joining(", ")); // Junta todos os nomes dos campos separados por vírgula

        // Extrai as mensagens de erro de cada campo violado
        String fieldMessages = exception.stream()
                .map(violation -> violation.getMessage()) // Pega a mensagem de erro da violação
                .collect(Collectors.joining(", ")); // Junta todas as mensagens separadas por vírgula


        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("bad request Exception, Conferences data")
                        .details("Check the fields errors")
                        .developerMessage(exceptionCVE.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldMessages)
                        .build(), HttpStatus.BAD_REQUEST
        );
    }
}
