package br.com.lab.impacta.investment.api.handler;

import br.com.lab.impacta.investment.application.dto.response.ErrorMessageResponse;
import br.com.lab.impacta.investment.domain.exception.InvestmentAccountIsNotDebitedException;
import br.com.lab.impacta.investment.domain.exception.InvestmentAccountWithoutBalanceException;
import br.com.lab.impacta.investment.domain.exception.InvestmentAccountWithoutBalanceForProductPrivateException;
import br.com.lab.impacta.investment.domain.exception.InvestmentProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @Value("${lab.investment.exceptions.message-error-default}")
    private String MESSAGE_ERROR_DEFAULT;

    @ExceptionHandler(InvestmentProductNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> errorProductNotFound(InvestmentProductNotFoundException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvestmentAccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalance(InvestmentAccountWithoutBalanceException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvestmentAccountWithoutBalanceForProductPrivateException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalanceForPrivate(InvestmentAccountWithoutBalanceForProductPrivateException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvestmentAccountIsNotDebitedException.class)
    public ResponseEntity<ErrorMessageResponse> errorIsNotDebited(InvestmentAccountIsNotDebitedException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> errorGeneral(RuntimeException exception){
        return getErrorMessageResponse(exception.getMessage(), MESSAGE_ERROR_DEFAULT, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessageResponse(String message, String description, HttpStatus statusCode) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                statusCode.value(),
                new Date(),
                message,
                description
        );

        return new ResponseEntity<>(errorMessageResponse, statusCode);
    }
}
