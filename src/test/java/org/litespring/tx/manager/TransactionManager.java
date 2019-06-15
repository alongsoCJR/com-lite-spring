package org.litespring.tx.manager;

import org.litespring.util.MessageTracker;

/**
 * @author chenjianrong-lhq 2019年06月02日 21:18:19
 * @Description: 事务管理器
 * @ClassName: TransactionManager
 */
public class TransactionManager {

    public void start() {
        System.out.println("tx start!");
        MessageTracker.addMsg("tx start!");
    }

    public void commit() {
        System.out.println("tx commit!");
        MessageTracker.addMsg("tx commit!");
    }

    public void rollback() {
        System.out.println("tx rollback!");
        MessageTracker.addMsg("tx rollback!");

    }
}
