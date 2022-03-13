package br.com.lab.impacta.investment.application.dto.request;

import lombok.Data;

@Data
public class DebitAccountRequest {
    private Double valueOfDebited;

    public DebitAccountRequest(Double valueOfDebited) {
        this.valueOfDebited = valueOfDebited;
    }
}
