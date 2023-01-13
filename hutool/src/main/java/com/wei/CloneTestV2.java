package com.wei;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class PersonV2 implements Cloneable{
    private String name;
    private Integer age;
    private String home;
    private FriendV2 friend;

    @Override
    public Object clone() throws CloneNotSupportedException {
        PersonV2 person = (PersonV2) super.clone();
        person.friend = (FriendV2) friend.clone();
        return person;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class FriendV2 implements Cloneable{
    private String name;
    private Integer age;
    private String home;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


public class CloneTestV2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        PersonV2 person_old = new PersonV2();
        person_old.setName("张三");
        person_old.setAge(18);
        person_old.setHome("chengdu");
        person_old.setFriend(new FriendV2("李四",19,"chongqin"));
        System.out.println("初始化下的person：" + person_old);

        PersonV2 person_clone = (PersonV2) person_old.clone();

        person_clone.setName("张武");
        person_clone.setAge(19);
        person_clone.getFriend().setAge(20);

        System.out.println("克隆出来的person：" + person_clone);
        System.out.println("修改之后的person：" + person_clone);
        System.out.println("修改后的原person：" + person_old);
    }
}

