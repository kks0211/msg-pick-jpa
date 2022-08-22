import {defaultInstance} from '../index';

const SHOP_API_PREFIX = '/api/v1/shops/register';

const registerShopApi = (data) => {
    return defaultInstance.post(`${SHOP_API_PREFIX}`, data, {
        headers: {
            'Content-Type': 'multipart/form-data',
        }
    });
}

export {
    registerShopApi
}
