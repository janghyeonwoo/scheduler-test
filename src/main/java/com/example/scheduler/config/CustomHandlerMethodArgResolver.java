package com.example.scheduler.config;

import com.example.scheduler.annotation.CustomPerson;
import com.example.scheduler.annotation.Person;
import com.example.scheduler.util.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
public class CustomHandlerMethodArgResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginAnnotation = parameter.getParameterAnnotation(CustomPerson.class) != null;
        boolean isLoginClass = Person.class.equals(parameter.getParameterType());
        return isLoginAnnotation && isLoginClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        JSONObject jsonObject = JsonUtils.readJSONStringFromRequestBody(request);
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(jsonObject.get("persons").toString(),Person.class);
        return person;
    }
}
