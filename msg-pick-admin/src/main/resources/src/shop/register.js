import {indicatorOff, indicatorOn} from '../common/indicator';
import {registerShopApi} from '../api/shop';
import {alertMessage, serializeJSON} from '../common/utils';
import {APP_ERROR} from '../common/constants';

const ShopRegister = (function () {
    let shopRegisterFormEl;
    let selectTypeEl;
    let inputHowToComeEl;
    let inputHomeCareAreaEl;
    let inputAddressEl;
    let inputZonecodeEl;
    let addressDetailAreaEl;
    let inputAddressDetailEl;
    let selectOpenAtEl;
    let selectCloseAtEl;
    let selectScaleEl;
    let selectHomeScaleEl;
    let shopImageDropzoneEl;
    let shopRegisterButtonEl;

    let validator;

    const initValidator = () => {
        validator = FormValidation.formValidation(
            shopRegisterFormEl,
            {
                fields: {
                    'type': {validators: {notEmpty: {message: '샵 유형을 선택해주세요.'}}},
                    'name': {validators: {notEmpty: {message: '상호명을 입력해주세요.'}}},
                    'businessArea': {validators: {notEmpty: {message: '핵심상권을 입력해주세요.'}}},
                    'howToCome': {validators: {notEmpty: {message: '오는길을 입력해주세요.'}}},
                    'homeCareArea': {validators: {notEmpty: {message: '홈케어 가능지역을 입력해주세요.'}}},
                    'address': {validators: {notEmpty: {message: '주소를 입력해주세요.'}}},
                    'addressDetail': {validators: {notEmpty: {message: '상세주소를 입력해주세요.'}}},
                    'contact': {validators: {notEmpty: {message: '연락처를 입력해주세요.'}}},
                    'theme': {validators: {notEmpty: {message: '샵 테마를 선택해주세요.'}}},
                    'scale': {validators: {notEmpty: {message: '샵 규모를 선택해주세요.'}}},
                    'homeCareScale': {validators: {notEmpty: {message: '샵 규모를 선택해주세요.'}}},
                    'dayOff': {validators: {notEmpty: {message: '휴일을 선택해주세요.'}}},
                    'openAt': {validators: {notEmpty: {message: '오픈시간을 선택해주세요.'}}},
                    'closeAt': {validators: {notEmpty: {message: '마감시간을 선택해주세요.'}}},
                    'payment': {validators: {notEmpty: {message: '결제방법을 선택해주세요.'}}},
                    'introduce': {validators: {notEmpty: {message: '샵소개를 입력해주세요.'}}},
                    'notice': {validators: {notEmpty: {message: '공지사항을 입력해주세요.'}}},
                    'serviceTarget': {validators: {notEmpty: {message: '서비스 대상을 선택해주세요.'}}},
                    'etiquette': {validators: {notEmpty: {message: '예약 에티켓을 선택해주세요.'}}},
                    'serviceTime': {validators: {notEmpty: {message: '서비스 시간을 선택해주세요.'}}},
                    'manner': {validators: {notEmpty: {message: '매너 규정을 선택해주세요.'}}},
                    'facilities': {validators: {notEmpty: {message: '제공 서비스를 1개 이상 선택해주세요.'}}},
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
        onFocusInputAddress: () => {
            addressDetailAreaEl.style.display = 'none';
            inputAddressEl.value = '';
            inputZonecodeEl.value = '';
            inputAddressDetailEl.value = '';

            window.DaumPost.open(function (response) {
                inputAddressEl.value = response.address;
                inputZonecodeEl.value = response.zonecode;

                addressDetailAreaEl.style.display = 'block';
                inputAddressDetailEl.focus();
                validator.revalidateField('address');
            });
        },

        onClickType: (e) => {
            const type = e.target.value;

            const elementsForDefaultCare = [inputZonecodeEl, inputAddressEl, inputAddressDetailEl, inputHowToComeEl, selectScaleEl];
            const elementsForHomeCare = [inputHomeCareAreaEl, selectHomeScaleEl];

            if (type === "HOMECARE") {
                elementsForDefaultCare.forEach(el => {
                    el.closest('.form-wrapper').style.display = 'none';
                    el.required = false;
                    el.value = '';
                });

                elementsForHomeCare.forEach(el => {
                    el.closest('.form-wrapper').style.display = 'block';
                    el.required = true;
                });
            } else {
                elementsForDefaultCare.forEach(el => {
                    el.closest('.form-wrapper').style.display = 'block';
                    el.required = true;
                });

                elementsForHomeCare.forEach(el => {
                    el.closest('.form-wrapper').style.display = 'none';
                    el.required = false;
                    el.value = '';
                });
            }
        },

        onClickNextButton: async () => {
            validator.validate()
                .then(async status => {
                    if (status !== 'Valid') {
                        return;
                    }

                    try {
                        indicatorOn(shopRegisterButtonEl);
                        const payload = serializeJSON(shopRegisterFormEl);
                        await registerShopApi(payload);

                        location.href = '/programs/register';
                    } catch (error) {
                        const message = error.response.data?.message ?? APP_ERROR;
                        alertMessage(message)
                    } finally {
                        indicatorOff(shopRegisterButtonEl);
                    }
                })
        }
    }

    const init = () => {
        shopRegisterFormEl = document.querySelector("#shopRegisterForm");
        selectTypeEl = document.querySelectorAll('select[name=type]');
        inputHowToComeEl = document.querySelector("#howToCome");
        inputHomeCareAreaEl = document.querySelector("#homeCareArea");
        inputAddressEl = document.querySelector("#address");
        inputZonecodeEl = document.querySelector("#zonecode");
        addressDetailAreaEl = document.querySelector("#addressDetailArea");
        inputAddressDetailEl = document.querySelector("#addressDetail");
        selectOpenAtEl = document.querySelector("#openAt");
        selectCloseAtEl = document.querySelector("#closeAt");
        selectScaleEl = document.querySelector("#scale");
        selectHomeScaleEl = document.querySelector("#homeCareScale");
        shopImageDropzoneEl = document.querySelector("#shopImageDropzone");
        shopRegisterButtonEl = document.querySelector("#shopRegisterButton");

        const flatpickrConfig = {
            enableTime: true,
            noCalendar: true,
            time_24hr: true,
            dateFormat: "H:i",
        };

        flatpickr(selectOpenAtEl, flatpickrConfig);
        flatpickr(selectCloseAtEl, flatpickrConfig);

        ['focus'].forEach(e => inputAddressEl.addEventListener(e, handlers.onFocusInputAddress));
        selectTypeEl.forEach(el => el.addEventListener('change', handlers.onClickType));
        shopRegisterButtonEl.addEventListener('click', handlers.onClickNextButton);

        document.querySelector("#type").dispatchEvent(new Event("change"));
        initValidator();
    }

    return {
        init
    }
})();

window.ShopRegister = ShopRegister;
