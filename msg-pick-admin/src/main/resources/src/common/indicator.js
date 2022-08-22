export const indicatorOn = (el) => {
    el.disabled = true;
    el.setAttribute("data-kt-indicator", "on");
};

export const indicatorOff = (el) => {
    el.disabled = false;
    el.removeAttribute("data-kt-indicator");
};
