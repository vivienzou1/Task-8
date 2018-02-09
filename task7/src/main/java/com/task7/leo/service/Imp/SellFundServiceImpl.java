package com.task7.leo.service.Imp;

import com.task7.leo.domain.*;
import com.task7.leo.dto.SellForm;
import com.task7.leo.repositories.*;
import com.task7.leo.service.SellFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation  = Isolation.SERIALIZABLE)
public class SellFundServiceImpl implements SellFundService {

    private final CustomerRepository customerRepository;
    private final FundRepository fundRepository;
    private final FundHoldRepository fundHoldRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public SellFundServiceImpl(CustomerRepository customerRepository, FundRepository fundRepository, TransactionRepository transactionRepository, FundHoldRepository fundHoldRepository) {
        this.customerRepository = customerRepository;
        this.fundRepository = fundRepository;
        this.transactionRepository = transactionRepository;
        this.fundHoldRepository = fundHoldRepository;
    }

    @Override
    public void sellFund(SellForm sellForm, String username) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        Fund fund = fundRepository.findByFundSymbol(sellForm.getFundSymbol());

        FundHold fundHold = fundHoldRepository.findByCustomerAndFund(customer, fund);
        if (fundHold == null) {
            throw new RuntimeException("no.fund");
        }

        double sellNumber = Double.parseDouble(sellForm.getShare());

        if (fundHold.getShare() < sellNumber) {
            throw new RuntimeException("fund.not.enough");
        }
        Transaction transaction = new Transaction(customer, fund, 0, sellNumber, 0,"sell");
        transactionRepository.save(transaction);

        double remainShare = fundHold.getShare() - sellNumber;
        if (remainShare == 0) {
            fund.getFundHolds().remove(fundHold);
            customer.getFundHolds().remove(fundHold);
            fundHoldRepository.delete(fundHold.getId());
        } else {
            fundHoldRepository.updateShareById(remainShare, fundHold.getId());
        }
    }
}
