import {defaultInstance} from '../index';

const THERAPIST_API_PREFIX = '/api/v1/therapists';

export const registerTherapistApi = (data) => {
    return defaultInstance.post(`${THERAPIST_API_PREFIX}/register`, JSON.stringify(data));
}
