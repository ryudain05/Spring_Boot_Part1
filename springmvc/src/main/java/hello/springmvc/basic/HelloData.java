package hello.springmvc.basic;

import lombok.Data;


//Getter, Setter, ToString, EqualsAndHashCode,
//RequiredArgsConstructor 자동 생성
@Data
public class HelloData {
    private String username;
    private int age;
}
