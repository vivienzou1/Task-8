package com.task7.leo.service.Imp;

import com.task7.leo.domain.Fund;
import com.task7.leo.dto.FundCreateForm;
import com.task7.leo.repositories.FundRepository;
import com.task7.leo.service.CreateFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation  = Isolation.SERIALIZABLE)
public class CreateFundServiceImpl implements CreateFundService {

    private final FundRepository fundRepository;

    @Autowired
    public CreateFundServiceImpl(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    @Override
    public void createFund(FundCreateForm fundCreateForm) {
        Fund fund = new Fund(fundCreateForm);
        fundRepository.save(fund);
    }



}
