package org.litespring.tx.manager;

import org.litespring.tx.util.MessageTrack;

/**
 * @author chenjianrong-lhq 2019年06月02日 21:18:19
 * @Description: 事务管理器
 * @ClassName: TransactionManager
 */
public class TransactionManager {

    public void start() {
        System.out.println("tx start!");
        MessageTrack.addMsg("tx start!");
    }

    public void commit() {
        System.out.println("tx commit!");
        MessageTrack.addMsg("tx commit!");
    }

    public void rollback() {
        System.out.println("tx rollback!");
        MessageTrack.addMsg("tx rollback!");

    }
}
