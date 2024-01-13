package com.products.service.elevatemartproductsservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice()
public final class ElevateMartExceptionController {

    @ExceptionHandler({AttributeNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ProductErrorMessage> attributeExceptionHandlerNot(AttributeNotFoundException anfe, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(anfe.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.NOT_FOUND.value());
        productErrMsg.setStatusDescription(HttpStatus.NOT_FOUND.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AttributeNullObjectException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ProductErrorMessage> attributeExceptionHandlerNull(AttributeNullObjectException aone, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(aone.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.NOT_ACCEPTABLE.value());
        productErrMsg.setStatusDescription(HttpStatus.NOT_ACCEPTABLE.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler({AttributeUnknownServerErrorException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ProductErrorMessage> attributeExceptionHandlerUnknown(AttributeUnknownServerErrorException ausee, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(ausee.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        productErrMsg.setStatusDescription(HttpStatus.INTERNAL_SERVER_ERROR.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({CategoryNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ProductErrorMessage> categoryExceptionHandlerNot(CategoryNotFoundException cnfe, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(cnfe.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.NOT_FOUND.value());
        productErrMsg.setStatusDescription(HttpStatus.NOT_FOUND.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({CategoryNullObjectException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ProductErrorMessage> categoryExceptionHandlerNull(CategoryNullObjectException cnoe, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(cnoe.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.NOT_ACCEPTABLE.value());
        productErrMsg.setStatusDescription(HttpStatus.NOT_ACCEPTABLE.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler({CategoryUnknownServerErrorException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ProductErrorMessage> categoryExceptionHandlerUnknown(CategoryUnknownServerErrorException cusee, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(cusee.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        productErrMsg.setStatusDescription(HttpStatus.INTERNAL_SERVER_ERROR.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ProductErrorMessage> productExceptionHandlerNot(ProductNotFoundException pnfe, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(pnfe.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.NOT_FOUND.value());
        productErrMsg.setStatusDescription(HttpStatus.NOT_FOUND.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({ProductNullObjectException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ProductErrorMessage> productExceptionHandlerNull(ProductNullObjectException pnoe, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(pnoe.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.NOT_ACCEPTABLE.value());
        productErrMsg.setStatusDescription(HttpStatus.NOT_ACCEPTABLE.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler({ProductUnknownServerErrorException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ProductErrorMessage> productExceptionHandlerUnknown(ProductUnknownServerErrorException pusee, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(pusee.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        productErrMsg.setStatusDescription(HttpStatus.INTERNAL_SERVER_ERROR.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({TaxNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ProductErrorMessage> taxExceptionHandlerNot(TaxNotFoundException tnfe, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(tnfe.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.NOT_FOUND.value());
        productErrMsg.setStatusDescription(HttpStatus.NOT_FOUND.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({TaxNullObjectException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ProductErrorMessage> taxExceptionHandlerNull(TaxNullObjectException tnoe, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(tnoe.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.NOT_ACCEPTABLE.value());
        productErrMsg.setStatusDescription(HttpStatus.NOT_ACCEPTABLE.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler({TaxUnknownServerErrorException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ProductErrorMessage> taxExceptionHandlerUnknown(TaxUnknownServerErrorException tusee, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(tusee.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        productErrMsg.setStatusDescription(HttpStatus.INTERNAL_SERVER_ERROR.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ProductErrorMessage> exceptionHandlerUnknown(Exception tusee, HttpServletRequest handler){
        ProductErrorMessage productErrMsg =getProductErrMsgObj();
        productErrMsg.setMessage(tusee.getMessage());
        productErrMsg.setUrlPath(handler.getRequestURI());
        productErrMsg.setHttpStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        productErrMsg.setStatusDescription(HttpStatus.METHOD_NOT_ALLOWED.name());
        return new  ResponseEntity<ProductErrorMessage>(productErrMsg,HttpStatus.METHOD_NOT_ALLOWED);
    }

    private ProductErrorMessage getProductErrMsgObj(){
        ProductErrorMessage pem =ProductErrorMessage.getInstance();
        pem.setLocalDateTime(LocalDateTime.now());
        return pem;
    }

}
