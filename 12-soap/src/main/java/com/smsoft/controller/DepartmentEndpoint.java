package com.smsoft.controller;

import com.smsoft.repository.DepartmentRepo;
import io.spring.guides.gs_producing_web_service.GetDepartmentRequest;
import io.spring.guides.gs_producing_web_service.GetDepartmentResponse;
import io.spring.guides.gs_producing_web_service.GetOldestInDepartmentRequest;
import io.spring.guides.gs_producing_web_service.GetOldestInDepartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class DepartmentEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    @Autowired
    private DepartmentRepo departmentRepo;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDepartmentRequest")
    @ResponsePayload
    public GetDepartmentResponse getDepartmentResponse(@RequestPayload GetDepartmentRequest request) {
        GetDepartmentResponse response = new GetDepartmentResponse();
        response.setDepartment(departmentRepo.findDepartment(request.getName()));

        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOldestInDepartmentRequest")
    @ResponsePayload
    public GetOldestInDepartmentResponse getOldestInDepartmentResponse(@RequestPayload GetOldestInDepartmentRequest request) {
        GetOldestInDepartmentResponse response = new GetOldestInDepartmentResponse();
        response.setEmployee(departmentRepo.findOldestInDepartment(request.getName()));

        return response;
    }
}
