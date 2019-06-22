package org.litespring.service.v6.impl;

import org.litespring.dao.v4.AccountDao;
import org.litespring.dao.v4.ItemDao;
import org.litespring.service.v6.IPetStoreService;
import org.litespring.stereotype.Autowired;
import org.litespring.stereotype.Component;
import org.litespring.util.MessageTracker;

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

    public void placeOrder() {
        System.out.println("PetStoreServiceImpl.placeOrder");
        MessageTracker.addMsg("PetStoreServiceImpl.placeOrder");
    }


    public void placeOrderWithException() throws NullPointerException {
        throw new NullPointerException();
    }
}
