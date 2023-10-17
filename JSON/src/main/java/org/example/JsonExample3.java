package org.example;

import org.json.JSONObject;

public class JsonExample3 {

    public static class Address{
        private JSONObject address;
        public Address(String city, String postNumber){
            address = new JSONObject();
            address.put("city",city);
            address.put("postNumber",postNumber);
        }

        public JSONObject getAddress() {
            return address;
        }

        public void setAddress(JSONObject address) {
            this.address = address;
        }
    }
    public static class Person
    {
        private String name;
        public Person(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Person person = new Person("nhn");
        JSONObject object = new JSONObject(person);

        System.out.println(object.toString());

        Address address = new Address("성남","13487");
        object = new JSONObject(address);
        System.out.println(object.toString());
    }
}
