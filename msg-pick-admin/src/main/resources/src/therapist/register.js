import {alertMessage} from '../common/utils';
import {indicatorOff, indicatorOn} from '../common/indicator';
import {APP_ERROR, FORM_VALID} from '../common/constants';
import {registerTherapistApi} from '../api/therapist';

const TherapistRegister = (function () {
    let therapistsEl;
    let therapistRegisterFormEl;
    let showRegisterFormButtonEl;
    let inputIndexEl;
    let inputNameEl;
    let inputPositionEl;
    let selectNationalityEl;
    let inputDescriptionEl;
    let addButtonEl;
    let modifyButtonEl;
    let cancelButtonEl;
    let prevButtonEl;
    let registerButtonEl;

    let therapists = [];
    let validator;

    const initValidator = () => {
        validator = FormValidation.formValidation(
            therapistRegisterFormEl,
            {
                fields: {
                    'name': {validators: {notEmpty: {message: '이름을 입력해주세요.'}}},
                    'position': {validators: {notEmpty: {message: '직책을 입력해주세요.'}}},
                    'nationality': {validators: {notEmpty: {message: '국적을 선택해주세요..'}}},
                    'description': {validators: {notEmpty: {message: '소개를 입력해주세요.'}}},
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


    const functions = {
        isFormWriting: async () => {
            let formDisplay = therapistRegisterFormEl.style.display;
            const valid = await validator.validate();

            return formDisplay === "block" && valid !== FORM_VALID;
        }
    }

    const handlers = {
        renderTherapists: () => {
            if (therapists.length === 0) {
                therapistsEl.innerHTML = `
                    <div class="py-15 align-items-center">
                        <p class="fs-5 text-gray-800 text-center fw-bold mb-0">아직 추가된 관리사가 없습니다.</p>
                        <p class="fs-6 text-gray-700 text-center mb-0">관리사를 추가해주세요.</p>
                    </div>
                `
                return;
            }

            therapistsEl.innerHTML = therapists.map((therapist, index) => {
                return `
                <div class="border border-gray-200 p-5 mb-3">
                    <div class="d-flex align-items-center flex-grow-1">
                        <span class="badge badge-square badge-light me-10">${index + 1}</span>
                        <div class="d-flex flex-column">
                            <div class="d-flex flex-row">
                                <p class="text-gray-800 fs-3 mb-1 fw-normal">${therapist.name}</p> / 
                                <p class="text-gray-800 fs-3 mb-1 fw-normal">${therapist.position}</p> /
                                <p class="text-gray-800 fs-3 mb-1 fw-normal">${therapist.nationalityDisplayName}</p>
                            </div>
                            <p class="text-gray-700 fs-5 mb-1 fw-normal">${therapist.description}</p>
                        </div>
                    </div>
                    <div class="d-flex justify-content-end">
                        <button type="button" class="btn btn-sm btn btn-white me-2" data-action="delete" data-index="${index}">삭제</button>
                        <button type="button" class="btn btn-sm btn btn-white" data-action="modify" data-index="${index}">수정</button>
                    </div>
                </div>`
            }).join('');
        },

        onClickItem: (e) => {
            const target = e.target;
            const action = target.dataset.action;
            const index = target.dataset.index;

            if (action && action === "delete") {
                handlers.delete(index);
            }

            if (action && action === "modify") {
                handlers.showModifyForm(index);
            }
        },

        showModifyForm: (index) => {
            const therapist = therapists[index];

            inputIndexEl.value = index;
            inputNameEl.value = therapist.name;
            inputPositionEl.value = therapist.position;
            selectNationalityEl.value = therapist.nationality;
            inputDescriptionEl.value = therapist.description;

            handlers.showRegisterForm('modify');
        },

        add: async () => {
            const valid = await validator.validate();
            if (valid === FORM_VALID) {
                const therapist = {
                    name: inputNameEl.value,
                    position: inputPositionEl.value,
                    nationality: selectNationalityEl.value,
                    nationalityDisplayName: selectNationalityEl.options[selectNationalityEl.selectedIndex].text,
                    description: inputDescriptionEl.value
                };
                therapists.push(therapist);

                handlers.resetRegisterForm();
                handlers.renderTherapists();
                handlers.hideRegisterForm();
            }
        },

        modify: async () => {
            const valid = await validator.validate();
            if (valid === FORM_VALID) {
                const index = inputIndexEl.value;
                const therapist = therapists[index];

                therapist.name = inputNameEl.value;
                therapist.position = inputPositionEl.value;
                therapist.nationality = selectNationalityEl.value;
                therapist.nationalityDisplayName = selectNationalityEl.options[selectNationalityEl.selectedIndex].text;
                therapist.description = inputDescriptionEl.value;

                handlers.resetRegisterForm();
                handlers.renderTherapists();
                handlers.hideRegisterForm();
            }
        },

        delete: (index) => {
            therapists.splice(index, 1);
            handlers.renderTherapists();
        },

        showRegisterForm: (type) => {
            // 수정
            if (typeof type === "string" && type === 'modify') {
                addButtonEl.style.display = 'none';
                modifyButtonEl.style.display = 'block';
            } else {
                // 추가
                handlers.resetRegisterForm();
                addButtonEl.style.display = 'block';
                modifyButtonEl.style.display = 'none';
            }

            therapistRegisterFormEl.style.display = 'block';
        },

        hideRegisterForm: () => {
            therapistRegisterFormEl.style.display = 'none';
        },

        onClickPrevButton: async () => {
            const isFormWriting = await functions.isFormWriting();
            if (isFormWriting) {
                alertMessage("작성중인 관리사 정보가 있습니다.");
                return;
            }

            location.href = "/programs/register";
        },

        onClickRegisterButton: async () => {
            const isFormWriting = await functions.isFormWriting();
            if (isFormWriting) {
                alertMessage("작성중인 관리사 정보가 있습니다.");
                return;
            }

            if (therapists.length < 1) {
                alertMessage("관리사를 1명 이상 등록해주세요.");
                return;
            }

            try {
                indicatorOn(registerButtonEl);
                const {data: response} = await registerTherapistApi(therapists);
                if (!response.success) {
                    alertMessage(response.message);
                    return;
                }

                location.replace('/shops/register-complete');
            } catch (error) {
                const message = error.response?.data?.message ?? APP_ERROR;
                alertMessage(message);
            } finally {
                indicatorOff(registerButtonEl);
            }
        },

        cancelForm: () => {
            handlers.resetRegisterForm();
            therapistRegisterFormEl.style.display = 'none';
        },

        resetRegisterForm: () => {
            inputIndexEl.value = "";
            inputNameEl.value = "";
            inputPositionEl.value = "";
            selectNationalityEl.value = "";
            inputDescriptionEl.value = "";
        }
    }

    const init = () => {
        therapistsEl = document.querySelector("#therapists");
        therapistRegisterFormEl = document.querySelector("#therapistRegisterForm");
        showRegisterFormButtonEl = document.querySelector("#showRegisterFormButton");

        inputIndexEl = document.querySelector("#index");
        inputNameEl = document.querySelector("#name");
        inputPositionEl = document.querySelector("#position");
        selectNationalityEl = document.querySelector("#nationality");
        inputDescriptionEl = document.querySelector("#description");

        modifyButtonEl = document.querySelector("#modifyButton");
        addButtonEl = document.querySelector("#addButton");
        cancelButtonEl = document.querySelector("#cancelButton");
        prevButtonEl = document.querySelector("#prevButton");
        registerButtonEl = document.querySelector("#registerButton");

        therapistsEl.addEventListener('click', handlers.onClickItem);
        showRegisterFormButtonEl.addEventListener('click', handlers.showRegisterForm);
        addButtonEl.addEventListener('click', handlers.add);
        modifyButtonEl.addEventListener('click', handlers.modify);
        cancelButtonEl.addEventListener('click', handlers.cancelForm);
        prevButtonEl.addEventListener('click', handlers.onClickPrevButton);
        registerButtonEl.addEventListener('click', handlers.onClickRegisterButton);

        handlers.renderTherapists();
        initValidator();
    }

    return {
        init
    }
})();

window.TherapistRegister = TherapistRegister;
