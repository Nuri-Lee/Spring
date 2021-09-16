package hello.core.singleton;

// 완벽한 싱글톤
// 구현 방법 중 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 사용했다.
public class SingletonService {

    // 자기 자신을 내부에 private 으로 가지고 있는데, static 으로 가지고 있는 것이다.
    // JVM 이 뜨면서 static 영역에 있는 SingletonService 를 초기화 하면서
    // new SingletonService() 를 자체적으로 실행해서 instance 에 참조를 넣어 둔다.
    private static final SingletonService instance = new SingletonService();

    // 조회
    public static SingletonService getInstance() {
        return instance;    // instance 의 참조를 꺼낼 수 있는 방법은 얘 밖에 없다.
                            // 그리고 SingletonService 를 생성할 수 있는 곳은 아예 없다.
    }

    // 생성자를 private 으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {}

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}

/*
* 장점 : 고객의 요청이 올 때마다 객체를 생성하는 것이 아니라,
*       이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있다.
* 문제점
*   - 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다. -> 21을 하기 위해 10, 13-16을 다 적어줘야 한다.
*   - 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP 위반
*   - 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
*   - 테스트가 어렵다.
*   - 내부 속성을 변경하거나 초기화 하기 어렵다.
*   - private 생성자로 자식 클래스를 만들기 어렵다.
*   - 결론적으로 유연성이 떨어진다.
*   - 안티패턴으로 불리기도 한다.
* */
