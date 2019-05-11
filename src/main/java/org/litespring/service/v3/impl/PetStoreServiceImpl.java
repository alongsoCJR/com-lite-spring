package org.litespring.service.v3.impl;

import org.litespring.dao.v2.AccountDao;
import org.litespring.dao.v2.ItemDao;
import org.litespring.service.v3.IPetStoreService;

/**
 * @author chenjianrong-lhq 2019年02月27日 22:51:36
 * @Description:
 * @ClassName: PetStoreServiceImpl
 */
public class PetStoreServiceImpl implements IPetStoreService {

    private AccountDao accountDao;

    private ItemDao itemDao;

    private int value;

    public PetStoreServiceImpl(AccountDao accountDao, ItemDao itemDao) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.value=-1;
    }

    public PetStoreServiceImpl() {
    }

    public PetStoreServiceImpl(AccountDao accountDao, ItemDao itemDao, int value) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.value = value;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public int getValue() {
        return value;
    }

    public void sayHello() {
        System.out.println("Hello,world!");
    }
}
