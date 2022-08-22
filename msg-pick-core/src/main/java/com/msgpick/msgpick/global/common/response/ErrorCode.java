package com.msgpick.msgpick.global.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."), // 장애 상황
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_ENTITY_NOT_FOUND("존재하지 않는 엔티티입니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다."),

    // 권한
    ACCESS_DENIED("접근 권한이 없습니다."),

    // 관리자
    USERNAME_PASSWORD_INVALID("아이디 또는 비밀번호가 올바르지 않습니다."),

    // 회원
    EMAIL_ALREADY_USED("이미 사용중인 이메일입니다."),
    PHONE_ALREADY_USED("이미 사용중인 휴대폰입니다."),
    PHONE_NOT_FOUND("휴대폰 번호를 입력해주세요."),
    EMAIL_PASSWORD_INVALID("이메일 또는 비밀번호가 올바르지 않습니다."),
    PASSWORD_CONFIRM_NOT_MATCHED("비밀번호가 일치하지 않습니다."),
    PHONE_PASSWORD_CONFIRM_NOT_MATCHED("휴대폰번호 또는 비밀번호가 일치하지 않습니다."),

    // 인증
    EMAIL_VERIFICATION_CODE_NOT_REGISTERED("인증코드 생성에 실패했습니다. 잠시 후 다시 시도해주세요."),
    EMAIL_VERIFICATION_CODE_NOT_MATCHED("인증코드가 일치하지 않습니다."),
    EMAIL_VERIFICATION_NOT_FOUND("인증코드를 찾을 수 없습니다. 인증코드 요청 후 다시 시도해주세요."),
    EMAIL_VERIFICATION_CODE_EXPIRED("인증코드 만료시간이 지났습니다."),

    VERIFICATION_CODE_EXPIRED("인증코드 만료시간이 지났습니다."),
    VERIFICATION_CODE_NOT_MATCHED("인증코드가 일치하지 않습니다."),
    VERIFICATION_NUMBER_NOT_MATCHED("인증번호가 일치하지 않습니다."),
    VERIFICATION_NOT_FOUND("인증코드를 찾을 수 없습니다. 인증코드 요청 후 다시 시도해주세요."),

    // GIFT
    GIFT_NOT_RECEIVABLE_CONDITION("선물 수락이 가능한 상태가 아닙니다."),
    GIFT_NOT_MODIFY_DELIVERY_CONDITION("배송지 변경이 가능한 상태가 아닙니다.");

    private final String errorMsg;

    public String getErrorMsg(Object... arg) {
        return String.format(errorMsg, arg);
    }
}
