package hello.hellospring.controller;

public class MemberForm {
    private String name;    // createMemberForm.html 에 있는 name 과 매칭됨.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
