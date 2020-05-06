import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public class MyTest {
public static String DF_AA;

    public static void main(String[] args) {
        int x=4;

        System.out.println(++x);
        System.out.println(DF_AA);
        System.out.println(DF_AA);
        System.out.println(MyTest.DF_AA);
        Map<Person, Object> map = new HashMap<Person,Object>();
        Person person = new Person("123", "234");
        map.put(person, "a");
        System.out.println(person);
        System.out.println(map.get(person));
        System.out.println(new Person("123", "234"));
        System.out.println(map.get(new Person("123", "234")));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Person{
        private String idCard;
        private String username;

        @Override
        public boolean equals(Object o){
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()){
                return false;
            }
            Person person = (Person)o;
            return this.idCard == person.idCard;
        }
    }

}
