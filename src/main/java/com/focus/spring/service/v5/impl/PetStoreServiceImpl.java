package com.focus.spring.service.v5.impl;

import com.focus.spring.dao.v4.AccountDao;
import com.focus.spring.dao.v4.ItemDao;
import com.focus.spring.service.v4.IPetStoreService;


/**
 * @author chenjianrong-lhq 2019年02月27日 22:51:36
 * @Description:
 * @ClassName: PetStoreServiceImpl
 */
public class PetStoreServiceImpl implements IPetStoreService {

    public PetStoreServiceImpl(AccountDao accountDao, ItemDao itemDao) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
    }

    private AccountDao accountDao;

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

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
}
