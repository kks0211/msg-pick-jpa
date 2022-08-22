import axios from 'axios';
import setInterceptor from './interceptors';

const baseURL = 'http://localhost:8080';

const createDefaultInstance = () => {
    const instance = axios.create({
        baseURL: baseURL,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    return setInterceptor(instance);
}

export const defaultInstance = createDefaultInstance();
