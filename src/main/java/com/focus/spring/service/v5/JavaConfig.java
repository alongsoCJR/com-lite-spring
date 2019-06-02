package com.focus.spring.service.v5;

import com.focus.spring.dao.v4.AccountDao;
import com.focus.spring.dao.v4.ItemDao;
import com.focus.spring.service.v5.impl.PetStoreServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean(name = "petStore")
    public PetStoreServiceImpl getPetStoreServiceImpl(AccountDao accountDao, ItemDao itemDao) {
        PetStoreServiceImpl petStoreService = new PetStoreServiceImpl(accountDao, itemDao);
        petStoreService.setAccountDao(accountDao);
        petStoreService.setItemDao(itemDao);
        return petStoreService;
    }

    @Bean
    public ItemDao getItemDao() {
        return new ItemDao();
    }

    @Bean
    public AccountDao getAccountDao() {
        return new AccountDao();
    }
}
