package com.roy_sun.infomanager.domain;

/**
 * 个人信息的业务实体
 * Created by Roy_Sun on 2015/11/14 0014.
 */
public class Person {
    private String person_id;
    private String name;
    private String phone;


    @Override
    public String toString () {
//        return "Person{" +
//                "person_id='" + person_id + '\'' +
//                ", name='" + name + '\'' +
//                ", phone='" + phone + '\'' +
//                '}';

        return "[person_id=" + person_id + ", name=" + name
                + ", phone=" + phone + "]";
    }

    public Person () {
    }

    public Person (String person_num, String name, String phone) {
        this.person_id = person_num;
        this.name = name;
        this.phone = phone;
    }

    public void setPerson_num (String person_num) {
        this.person_id = person_num;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getPerson_num () {
        return person_id;
    }

    public String getName () {
        return name;
    }

    public String getPhone () {
        return phone;
    }
}
