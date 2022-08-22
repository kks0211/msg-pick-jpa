import {defaultInstance} from '../index';

const SHOP_API_PREFIX = '/api/v1/shops/register';

const registerShopApi = (data) => {
    return defaultInstance.post(`${SHOP_API_PREFIX}`, JSON.stringify(data));
}

export {
    registerShopApi
}
