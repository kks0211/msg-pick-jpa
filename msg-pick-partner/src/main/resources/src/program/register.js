import {alertMessage, initMaxLength, removeComma, setComma} from '../common/utils';
import {indicatorOff, indicatorOn} from '../common/indicator';
import {APP_ERROR, FORM_VALID} from '../common/constants';
import {registerProgramApi} from '../api/program';

const ProgramRegister = (function () {
    let programsEl;
    let programRegisterFormEl;
    let showRegisterFormButtonEl;
    let inputIndexEl;
    let inputNameEl;
    let inputPriceEl;
    let inputDescriptionEl;
    let addButtonEl;
    let modifyButtonEl;
    let cancelButtonEl;
    let nextButtonEl;
    let prevButtonEl;

    let programs = [];
    let validator;

    const initValidator = () => {
        validator = FormValidation.formValidation(
            programRegisterFormEl,
            {
                fields: {
                    'name': {validators: {notEmpty: {message: '서비스 이름을 입력해주세요.'}}},
                    'price': {validators: {notEmpty: {message: '가격을 입력해주세요.'}}},
                    'description': {validators: {notEmpty: {message: '설명을 입력해주세요.'}}},
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
            let formDisplay = programRegisterFormEl.style.display;
            const valid = await validator.validate();

            return formDisplay === "block" && valid !== FORM_VALID;
        },

        setPrograms: (sessionPrograms) => {
            programs = sessionPrograms;
        }
    }

    const handlers = {
        renderPrograms: () => {
            if (programs.length === 0) {
                programsEl.innerHTML = `
                    <div class="py-15 align-items-center">
                        <p class="fs-5 text-gray-800 text-center fw-bold mb-0">아직 추가된 프로그램이 없습니다.</p>
                        <p class="fs-6 text-gray-700 text-center mb-0">프로그램을 추가해주세요.</p>
                    </div>
                `
                return;
            }

            programsEl.innerHTML = programs.map((program, index) => {
                return `
                <div class="border border-gray-200 p-5 mb-3">
                    <div class="d-flex align-items-center flex-grow-1">
                        <span class="badge badge-square badge-light me-10">${index + 1}</span>
                        <div class="d-flex flex-column">
                            <div class="d-flex flex-row">
                                <p class="text-gray-800 fs-3 mb-1 fw-normal me-3">${program.name}</p> 
                                <p class="text-gray-800 fs-3 mb-1 fw-normal">${setComma(program.price)}원</p>
                            </div>
                            <p class="text-gray-700 fs-5 mb-1 fw-normal">${program.description}</p>
                        </div>
                    </div>
                    <div class="d-flex justify-content-end">
                        <button type="button" class="btn btn-sm btn btn-white me-2" data-action="delete" data-index="${index}">삭제</button>
                        <button type="button" class="btn btn-sm btn btn-white" data-action="modify" data-index="${index}">수정</button>
                    </div>
                </div>`
            }).join('');
        },

        onChangePrice: () => {
            inputPriceEl.value = setComma(inputPriceEl.value);
        },

        onClickItem: (e) => {
            const target = e.target;
            const action = target.dataset.action;
            const index = target.dataset.index;

            if (action && action === "delete") {
                handlers.deleteProgram(index);
            }

            if (action && action === "modify") {
                handlers.showModifyProgramForm(index);
            }
        },

        showModifyProgramForm: (index) => {
            const program = programs[index];

            inputIndexEl.value = index;
            inputNameEl.value = program.name;
            inputPriceEl.value = program.price;
            inputDescriptionEl.value = program.description;

            handlers.showRegisterForm('modify');
        },

        addProgram: async () => {
            const valid = await validator.validate();
            if (valid === FORM_VALID) {
                const program = {
                    name: inputNameEl.value,
                    price: removeComma(inputPriceEl.value),
                    description: inputDescriptionEl.value
                };
                programs.push(program);
                handlers.resetRegisterForm();
                handlers.renderPrograms();

                programRegisterFormEl.style.display = 'none';
            }
        },

        modifyProgram: async () => {
            const valid = await validator.validate();
            if (valid === FORM_VALID) {
                const index = inputIndexEl.value;
                const program = programs[index];

                program.name = inputNameEl.value;
                program.price = removeComma(inputPriceEl.value);
                program.description = inputDescriptionEl.value;

                handlers.resetRegisterForm();
                handlers.renderPrograms();
                programRegisterFormEl.style.display = 'none';
            }
        },

        deleteProgram: (index) => {
            programs.splice(index, 1);
            handlers.renderPrograms();
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

            programRegisterFormEl.style.display = 'block';
        },

        cancelForm: () => {
            handlers.resetRegisterForm();
            programRegisterFormEl.style.display = 'none';
        },

        resetRegisterForm: () => {
            inputIndexEl.value = "";
            inputNameEl.value = "";
            inputPriceEl.value = "";
            inputDescriptionEl.value = "";
        },

        onClickPrevButton: async () => {
            const writing = await functions.isFormWriting();
            if (writing) {
                alertMessage("작성중인 프로그램이 있습니다.");
                return;
            }

            location.href = "/shops/register";
        },

        onClickNextButton: async () => {
            const writing = await functions.isFormWriting();
            if (writing) {
                alertMessage("작성중인 프로그램이 있습니다.");
                return;
            }

            if (programs.length < 1) {
                alertMessage("프로그램을 1개 이상 등록해주세요.");
                return;
            }

            try {
                indicatorOn(nextButtonEl);
                await registerProgramApi(programs);
                location.href = '/therapists/register';
            } catch (error) {
                const message = error.response?.data?.message ?? APP_ERROR;
                alertMessage(message);
            } finally {
                indicatorOff(nextButtonEl);
            }
        }
    }

    const init = () => {
        programsEl = document.querySelector("#programs");
        programRegisterFormEl = document.querySelector("#programRegisterForm");
        showRegisterFormButtonEl = document.querySelector("#showRegisterFormButton");

        inputIndexEl = document.querySelector("#index");
        inputNameEl = document.querySelector("#name");
        inputPriceEl = document.querySelector("#price");
        inputDescriptionEl = document.querySelector("#description");

        modifyButtonEl = document.querySelector("#modifyButton");
        addButtonEl = document.querySelector("#addButton");
        cancelButtonEl = document.querySelector("#cancelButton");
        prevButtonEl = document.querySelector("#prevButton");
        nextButtonEl = document.querySelector("#nextButton");

        inputPriceEl.addEventListener('input', handlers.onChangePrice);
        programsEl.addEventListener('click', handlers.onClickItem);
        showRegisterFormButtonEl.addEventListener('click', handlers.showRegisterForm);
        addButtonEl.addEventListener('click', handlers.addProgram);
        modifyButtonEl.addEventListener('click', handlers.modifyProgram);
        cancelButtonEl.addEventListener('click', handlers.cancelForm);
        prevButtonEl.addEventListener('click', handlers.onClickPrevButton);
        nextButtonEl.addEventListener('click', handlers.onClickNextButton);

        handlers.renderPrograms();
        initValidator();
        initMaxLength();
    }

    return {
        init,
        setPrograms: functions.setPrograms
    }
})();

window.ProgramRegister = ProgramRegister;
