package by.ilyin.web.feign.decoder;

import by.ilyin.web.exception.http.ErrorResponseWrapperException;
import by.ilyin.web.util.error.FeignErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    public Exception decode(String methodKey, Response response) {
        FeignErrorResponse feignErrorResponse;
        try {
            InputStream bodyIs = response.body().asInputStream();
            ObjectMapper mapper = new ObjectMapper();
            feignErrorResponse = mapper.readValue(bodyIs, FeignErrorResponse.class);
        } catch (IOException e) {
            //todo log
            return errorDecoder.decode(methodKey, response);
        }
        ErrorResponseWrapperException currentWrapper = new ErrorResponseWrapperException();
        currentWrapper.setFeignErrorResponse(feignErrorResponse);
        return currentWrapper;
    }

}
