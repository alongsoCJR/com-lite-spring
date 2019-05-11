package org.litespring.service.v1.impl;

import org.litespring.dao.v2.AccountDao;
import org.litespring.dao.v2.ItemDao;
import org.litespring.service.v1.IPetStoreService;

/**
 * @author chenjianrong-lhq 2019年02月27日 22:51:36
 * @Description:
 * @ClassName: PetStoreServiceImpl
 */
public class PetStoreServiceImpl implements IPetStoreService {

    private AccountDao accountDao;

    private ItemDao itemDao;

    private String owner;

    private Integer version;

    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void sayHello() {
        System.out.println("Hello,world!");
    }
}
