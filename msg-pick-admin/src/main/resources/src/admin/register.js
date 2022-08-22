import {registerAdminApi} from '../api/admin';
import {indicatorOff, indicatorOn} from '../common/indicator';
import {APP_ERROR, FORM_VALID} from '../common/constants';
import {alertMessage, initMaxLength, successMessage} from '../common/utils';

const AdminRegister = (function () {
    let adminRegisterFormEl;
    let inputLoginIdEl;
    let inputNameEl;
    let inputPasswordEl;
    let inputRoleEl;
    let registerButtonEl;

    let validator;

    const initValidator = () => {
        validator = FormValidation.formValidation(
            adminRegisterFormEl,
            {
                fields: {
                    'loginId': {validators: {notEmpty: {message: '아이디를 입력해주세요.'}}},
                    'name': {validators: {notEmpty: {message: '이름을 입력해주세요.'}}},
                    'password': {validators: {notEmpty: {message: '비밀번호를 입력해주세요.'}}},
                    'role': {validators: {notEmpty: {message: '권한을 선택해주세요.'}}},
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

        onClickRegisterButton: async function () {
            const valid = await validator.validate();
            if (valid !== FORM_VALID) {
                return;
            }

            try {
                indicatorOn(registerButtonEl);

                const {data: response} = await registerAdminApi({
                    loginId: inputLoginIdEl.value.trim(),
                    name: inputNameEl.value.trim(),
                    password: inputPasswordEl.value.trim(),
                    role: inputRoleEl.value.trim(),
                });

                if (!response.success) {
                    alertMessage(response.message);
                    return;
                }

                successMessage("관리자 등록을 완료했습니다.")
                    .then((result) => {
                        if (result.isConfirmed) {
                            location.href = '/admins';
                        }
                    });
            } catch (error) {
                const message = error.response?.data?.message ?? APP_ERROR;
                alertMessage(message);
            } finally {
                indicatorOff(registerButtonEl);
            }
        }
    }

    const init = () => {
        adminRegisterFormEl = document.querySelector("#adminRegisterForm");
        inputLoginIdEl = document.querySelector("#loginId");
        inputNameEl = document.querySelector("#name");
        inputPasswordEl = document.querySelector("#password");
        inputRoleEl = document.querySelector("#role");

        registerButtonEl = document.querySelector("#registerButton");
        registerButtonEl.addEventListener('click', handlers.onClickRegisterButton);

        initValidator();
        initMaxLength();
    }

    return {
        init
    }
})();

window.AdminRegister = AdminRegister;
