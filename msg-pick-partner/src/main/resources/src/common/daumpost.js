const DaumPost = (function (){

    const functions = {
        open: (callback) => {
            new daum.Postcode({
                oncomplete: function(data) {
                    callback(data);
                }
            }).open();
        }
    }

    return {
        open: (callback) => functions.open(callback),
    }

})();

window.DaumPost = DaumPost;
