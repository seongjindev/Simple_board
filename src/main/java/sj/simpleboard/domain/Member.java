package sj.simpleboard.domain;

public class Member {
    private String memberId;
    private String memberPwd;
    private String memberNM;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getMemberNM() {
        return memberNM;
    }

    public void setMemberNM(String memberNM) {
        this.memberNM = memberNM;
    }
}
