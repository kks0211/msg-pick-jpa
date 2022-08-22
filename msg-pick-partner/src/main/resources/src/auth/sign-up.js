import {requestPhoneAuthCodeApi, verifyPhoneAuthCodeApi, signUpApi} from '../api/auth';
import {indicatorOff, indicatorOn} from '../common/indicator';
import {APP_ERROR, FORM_VALID} from '../common/constants';
import {alertMessage, initMaxLength, successMessage} from '../common/utils';

const SignUp = (function () {
    let signUpFormEl;
    let inputPhoneEl;
    let inputEmailEl;
    let inputPasswordEl;
    let inputPasswordConfirmEl;
    let inputPhoneAuthCodeEl;
    let requestPhoneAuthCodeButtonEl;
    let phoneAuthCodeAreaEl;
    let verifyPhoneAuthCodeButtonEl;
    let signUpButtonEl;

    let validator;

    const initValidator = () => {
        validator = FormValidation.formValidation(
            signUpFormEl,
            {
                fields: {
                    'phone': {
                        validators: {
                            notEmpty: {message: '휴대폰번호를 입력해주세요.'},
                            apiResponse: {}
                        }},
                    'phoneAuthCode': {
                        validators: {
                            notEmpty: {message: '인증번호를 입력해주세요.'},
                            apiResponse: {}
                        }},
                    'password': {validators: {notEmpty: {message: '비밀번호를 입력해주세요.'}}},
                    'passwordConfirm': {
                        validators: {
                            notEmpty: {message: '비밀번호 확인을 입력해주세요.'},
                            identical: {
                                compare: function () {
                                    return inputPasswordEl.value;
                                },
                                message: '비밀번호가 일치하지 않습니다.',
                            },
                        }},
                    'email': {validators: {notEmpty: {message: '이메일을 입력해주세요.'}}},
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
        onChangePasswordConfirm: function () {
            validator.validateField('passwordConfirm');
        },

        onClickRequestPhoneAuthCodeButton: async function () {
            const valid = await validator.validateField('phone');
            if (valid !== FORM_VALID) {
                return;
            }

            try {
                indicatorOn(requestPhoneAuthCodeButtonEl);

                inputPhoneAuthCodeEl.value = '';
                const {data: response} = await requestPhoneAuthCodeApi({
                    phone: inputPhoneEl.value.trim(),
                })

                if (!response.success) {
                    validator.updateValidatorOption('phone', 'apiResponse', 'message', response.message)
                        .updateFieldStatus('phone', 'Invalid', 'apiResponse');

                    return;
                }

                phoneAuthCodeAreaEl.style.display = 'block';
                requestPhoneAuthCodeButtonEl.textContent = '재전송';
            } catch (error) {
                const message = error.response.data?.message ?? APP_ERROR;
                alertMessage(message);
            } finally {
                indicatorOff(requestPhoneAuthCodeButtonEl);
            }
        },

        onClickVerifyPhoneAuthCode: async function () {
            const valid = await validator.validateField('phoneAuthCode');
            if (valid !== FORM_VALID) {
                return;
            }

            try {
                indicatorOn(verifyPhoneAuthCodeButtonEl);
                const {data: response} = await verifyPhoneAuthCodeApi({
                    phone: inputPhoneEl.value.trim(),
                    verificationCode: inputPhoneAuthCodeEl.value.trim(),
                })

                if (!response.success) {
                    validator.updateValidatorOption('phoneAuthCode', 'apiResponse', 'message', response.message)
                        .updateFieldStatus('phoneAuthCode', 'Invalid', 'apiResponse');

                    return;
                }

                phoneAuthCodeAreaEl.style.display = 'none';
                requestPhoneAuthCodeButtonEl.disabled = true;
                requestPhoneAuthCodeButtonEl.textContent = '인증완료';
            } catch (error) {
                const message = error.response.data?.message ?? APP_ERROR;
                alertMessage(message);
            } finally {
                indicatorOff(verifyPhoneAuthCodeButtonEl);
            }
        },

        onClickSignUpButton: async function () {
            const valid = await validator.validate();
            if(valid !== FORM_VALID){
                return;
            }

            try {
                indicatorOn(signUpButtonEl);

                const {data: response} = await signUpApi({
                    phone: inputPhoneEl.value.trim(),
                    password: inputPasswordEl.value.trim(),
                    email: inputEmailEl.value.trim(),
                });

                if (!response.success) {
                    alertMessage(response.message);
                    return;
                }

                successMessage("회원가입을 완료했습니다. 계속해서 로그인해주세요.")
                    .then((result) => {
                        if (result.isConfirmed) {
                            location.href = '/auth/sign-in';
                        }
                    });
            } catch(error) {
                const message = error.response?.data?.message ?? APP_ERROR;
                alertMessage(message);
            } finally {
                indicatorOff(signUpButtonEl);
            }
        }
    }

    const init = () => {
        signUpFormEl = document.querySelector("#signUpForm");
        inputPhoneEl = document.querySelector("#phone");
        inputEmailEl = document.querySelector("#email");
        inputPasswordEl = document.querySelector("#password");
        inputPasswordConfirmEl = document.querySelector("#passwordConfirm");
        inputPhoneAuthCodeEl = document.querySelector("#phoneAuthCode")
        requestPhoneAuthCodeButtonEl = document.querySelector("#requestPhoneAuthCodeButton");
        phoneAuthCodeAreaEl = document.querySelector("#phoneAuthCodeArea");
        verifyPhoneAuthCodeButtonEl = document.querySelector("#verifyPhoneAuthCodeButton");
        signUpButtonEl = document.querySelector("#signUpButton");

        inputPasswordConfirmEl.addEventListener('input', handlers.onChangePasswordConfirm);
        requestPhoneAuthCodeButtonEl.addEventListener('click', handlers.onClickRequestPhoneAuthCodeButton);
        verifyPhoneAuthCodeButtonEl.addEventListener('click', handlers.onClickVerifyPhoneAuthCode);
        signUpButtonEl.addEventListener('click', handlers.onClickSignUpButton);

        initValidator();
        initMaxLength();
    }

    return {
        init
    }
})();

window.SignUp = SignUp;
