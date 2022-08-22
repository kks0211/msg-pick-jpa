import {defaultInstance} from '../index';

const AUTH_API_PREFIX = '/api/v1/auth';

const checkDuplicatedEmailApi = (data) => {
    return defaultInstance.get('/api/v1/partners/if-exist-email', {
        params: data
    });
}

// 휴대폰 인증번호 요청
const requestPhoneAuthCodeApi = (data) => {
    return defaultInstance.post(`${AUTH_API_PREFIX}/phone-verifications/new`, JSON.stringify(data));
}

// 휴대폰 인증번호 확인
const verifyPhoneAuthCodeApi = (data) => {
    return defaultInstance.post(`${AUTH_API_PREFIX}/phone-verifications/confirm`, JSON.stringify(data));
}

// 회원가입
const signUpApi = (data) => {
    return defaultInstance.post('/api/v1/auth/sign-up', JSON.stringify(data));
}

// 로그인
const signInApi = (data) => {
    return defaultInstance.post('/auth/sign-in', data, {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    });
}

export {
    requestPhoneAuthCodeApi,
    verifyPhoneAuthCodeApi,

    checkDuplicatedEmailApi,
    signUpApi,
    signInApi
}
