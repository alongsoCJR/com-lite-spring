package com.focus.spring.service.v4.impl;

import com.focus.spring.dao.v4.AccountDao;
import com.focus.spring.dao.v4.ItemDao;
import com.focus.spring.service.v4.IPetStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author chenjianrong-lhq 2019年02月27日 22:51:36
 * @Description:
 * @ClassName: PetStoreServiceImpl
 */
@Component(value = "petStore")
public class PetStoreServiceImpl implements IPetStoreService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void sayHello() {
        System.out.println("Hello,world!");
    }
}
