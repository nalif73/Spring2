package com.geekbrains.ru.springproduct.endpoint;


import com.geekbrains.ru.springproduct.service.CategoryServiceSoap;
import com.geekbrains.ru.springproduct.soap.categories.GetCategoryByNameRequest;
import com.geekbrains.ru.springproduct.soap.categories.GetCategoryByNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class CategoryEndpoint {
    private static final String NAMESPACE_URI = "http://www.andfil.com/spring/ws/categories";
    private final CategoryServiceSoap categoryService;

    /*
        Пример запроса: POST http://localhost:8080/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.andfil.com/spring/ws/categories">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getGroupByNameRequest>
                    <f:title>Одежда</f:title>
                </f:getGroupByNameRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGroupByNameRequest")
    @ResponsePayload
    @Transactional
    public GetCategoryByNameResponse getCategoryByName(@RequestPayload GetCategoryByNameRequest request) {
        GetCategoryByNameResponse response = new GetCategoryByNameResponse();
        response.setCategory(categoryService.getCategoryByName(request.getName()));
        return response;
    }
}
