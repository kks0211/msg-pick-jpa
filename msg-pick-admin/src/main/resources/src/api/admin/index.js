import {defaultInstance} from '../index';

const ADMIN_API_PREFIX = '/api/v1/admins';

// 관리자 등록
export const registerAdminApi = (data) => {
    return defaultInstance.post(`${ADMIN_API_PREFIX}/register`, JSON.stringify(data));
}
