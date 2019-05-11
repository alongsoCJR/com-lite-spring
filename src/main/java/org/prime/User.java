package org.prime;

/**
 * @author chenjianrong-lhq 2019年04月07日 09:50:11
 * @Description:
 * @ClassName: User
 */
public class User {

    private int age;

    private String name;

    private Long userId;

    public User(Integer age, String name, Long userId) {
        this.age = age;
        this.name = name;
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
