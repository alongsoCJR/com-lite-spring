package org.litespring.service.v4.impl;

import org.litespring.dao.v4.AccountDao;
import org.litespring.dao.v4.ItemDao;
import org.litespring.service.v4.IPetStoreService;
import org.litespring.stereotype.Autowired;
import org.litespring.stereotype.Component;

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
