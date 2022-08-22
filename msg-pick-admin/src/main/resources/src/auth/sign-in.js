import {signInApi} from '../api/auth';
import {indicatorOff, indicatorOn} from '../common/indicator';
import {APP_ERROR} from '../common/constants';
import {alertMessage} from '../common/utils';

const SignIn = (function () {
    let signInFormEl;
    let inputLoginIdEl;
    let inputPasswordEl;
    let signInButtonEl;
    let validator;

    const initValidator = () => {
        validator = FormValidation.formValidation(
            signInFormEl,
            {
                fields: {
                    'loginId': {validators: {notEmpty: {message: '아이디를 입력해주세요.'}}},
                    'password': {validators: {notEmpty: {message: '비밀번호를 입력해주세요.'}}},
                },

                plugins: {
                    trigger: new FormValidation.plugins.Trigger(),
                    bootstrap: new FormValidation.plugins.Bootstrap5({
                        rowSelector: '.form-wrapper',
                        eleInvalidClass: '',
                        eleValidClass: ''
                    }),
                    excluded: new FormValidation.plugins.Excluded(),
                }
            }
        );
    };

    const handlers = {
        onClickSignInButton: async function () {
            const valid = await validator.validate();
            if (valid !== "Valid") {
                return;
            }

            try {
                indicatorOn(signInButtonEl);

                const signInFormData = new URLSearchParams();
                signInFormData.append('loginId', inputLoginIdEl.value.trim());
                signInFormData.append('password', inputPasswordEl.value.trim())

                const {data: response} = await signInApi(signInFormData);
                if (response.success && response.data) {
                    location.href = response.data;
                    return;
                }

                location.href = "/dashboard";
            } catch (error) {
                const message = error.response?.data?.message ?? APP_ERROR;
                alertMessage(message);
            } finally {
                indicatorOff(signInButtonEl);
            }
        }
    }

    const init = () => {
        signInFormEl = document.querySelector("#signInform");
        inputLoginIdEl = document.querySelector("#loginId");
        inputPasswordEl = document.querySelector("#password");
        signInButtonEl = document.querySelector("#signInButton");

        signInButtonEl.addEventListener('click', handlers.onClickSignInButton);

        initValidator();
    }

    return {
        init
    }
})();

window.SignIn = SignIn;
