import {defaultInstance} from '../index';

const PROGRAM_API_PREFIX = '/api/v1/programs';

const registerProgramApi = (data) => {
    return defaultInstance.post(`${PROGRAM_API_PREFIX}/register`, JSON.stringify(data));
}

export {
    registerProgramApi
}
