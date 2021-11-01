package sj.simpleboard.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Member {

    @NotNull
    @Length(max = 30)
    private String memberId;
    @NotEmpty
    @Range(min = 8, max = 16)
    private String memberPwd;
    @NotEmpty
    private String memberNM;

    public Member() {
    }

    public Member(@NotNull @Length(max = 30) String memberId, @NotEmpty @Range(min = 8, max = 16) String memberPwd, @NotEmpty String memberNM) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberNM = memberNM;
    }

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
