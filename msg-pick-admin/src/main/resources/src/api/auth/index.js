import {defaultInstance} from '../index';

// 로그인
export const signInApi = (data) => {
    return defaultInstance.post('/auth/sign-in', data, {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    });
}
