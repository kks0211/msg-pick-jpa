const setInterceptor = (instance) => {
    instance.interceptors.response.use(
        function (response) {
            return response;
        },
        function (error) {
            console.log(error.response)
            return Promise.reject(error);
        });

    return instance;
}

export default setInterceptor;
