package com.wei;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person implements Cloneable{
    private String name;
    private Integer age;
    private String home;
    private Friend friend;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Friend{
    private String name;
    private Integer age;
    private String home;
}


public class CloneTestV1 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person person_old = new Person();
        person_old.setName("张三");
        person_old.setAge(18);
        person_old.setHome("chengdu");
        person_old.setFriend(new Friend("李四",19,"chongqin"));
        System.out.println("初始化下的person：" + person_old);

        Person person_clone = (Person) person_old.clone();

        person_clone.setName("张武");
        person_clone.setAge(19);
        person_clone.getFriend().setAge(20);

        System.out.println("克隆出来的person：" + person_clone);
        System.out.println("修改之后的person：" + person_clone);
        System.out.println("修改后的原person：" + person_old);
    }
}

