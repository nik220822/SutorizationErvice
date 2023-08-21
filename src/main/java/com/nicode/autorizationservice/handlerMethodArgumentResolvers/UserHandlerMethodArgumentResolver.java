package com.nicode.autorizationservice.handlerMethodArgumentResolvers;

import com.nicode.autorizationservice.authorities.Authorities;
import com.nicode.autorizationservice.model.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;

public final class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String name = webRequest.getParameter("user");
        String password = webRequest.getParameter("password");

        if (isNotSet(name)) {
            name = "defaultName";
        }

        if (isNotSet(password)) {
            password = "defaultPassword";
        }

        return new User(name, password, List.of(Authorities.READ));
    }

    private boolean isNotSet(String value) {
        return value == null;
    }
}