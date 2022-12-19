package by.ilyin.web.feign.decoder;

import by.ilyin.web.exception.http.CustomFeignException;
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
        CustomFeignException customFeignException;
        try {
            InputStream responseBodyInputStream = response.body().asInputStream();
            ObjectMapper mapper = new ObjectMapper();
            customFeignException = mapper.readValue(responseBodyInputStream, CustomFeignException.class);
        } catch (IOException e) {
            //todo log
            return errorDecoder.decode(methodKey, response);
        }
        return customFeignException;
    }

}
