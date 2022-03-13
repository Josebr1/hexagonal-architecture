package br.com.lab.impacta.investment.domain.service;

import br.com.lab.impacta.investment.domain.model.Investment;

public interface InvestmentService {
    Investment Invest(Long productId, Long accountId, Double valueInvestment);
}
