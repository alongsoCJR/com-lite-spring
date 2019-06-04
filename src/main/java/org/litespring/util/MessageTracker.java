package org.litespring.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjianrong-lhq 2019年06月02日 21:46:07
 * @Description:
 * @ClassName: MessageTracker
 */
public class MessageTracker {

    private static List<String> MESSAGES = new ArrayList<String>();

    public static void addMsg(String msg) {
        MESSAGES.add(msg);
    }

    public static void clearMsgs() {
        MESSAGES.clear();
    }

    public static List<String> getMsgs() {
        return MESSAGES;
    }
}