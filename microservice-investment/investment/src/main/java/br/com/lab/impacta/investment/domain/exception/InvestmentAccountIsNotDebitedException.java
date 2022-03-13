package br.com.lab.impacta.investment.domain.exception;

public class InvestmentAccountIsNotDebitedException extends RuntimeException{

    private String description;

    public String getDescription(){
        return this.description;
    }

    public InvestmentAccountIsNotDebitedException(){
        super();
    }


    public InvestmentAccountIsNotDebitedException(String message, String description){
        super(message);
        this.description = description;
    }
}
