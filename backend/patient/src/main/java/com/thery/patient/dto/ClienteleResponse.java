package com.thery.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ClienteleResponse {
    List<ClienteleIdentityDto> clienteleIdentity;

    public ClienteleResponse() {
        //lombok constructor
    }
}
