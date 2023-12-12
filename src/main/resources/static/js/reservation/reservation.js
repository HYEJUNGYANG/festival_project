const inputDate = document.querySelector('.input-date');
const btnSelDate = document.querySelector('.btn-sel-date');
const pay = document.querySelector('.pay-value');
const btnMinus = document.querySelector('.btn-minus');
const btnPlus = document.querySelector('.btn-plus');
const inputPeople = document.querySelector('.input-people');
const totalValue = document.querySelector('.total-value > span');
const payMethods = document.getElementsByName('pay');
const btnRe = document.querySelector('.btn-re');
const form = document.querySelector('#form');

const PAY = pay.innerHTML.replace(/,/gi, "");
let isMethod = false;

function clearInputPeople() {
  btnPlus.classList.remove('none');
  btnMinus.classList.add('none');
  inputPeople.value = 1;
  totalValue.innerHTML = Number(PAY).toLocaleString();
  payMethods[0].checked = false;
  payMethods[1].checked = false;
}

inputDate.addEventListener('change', () => {
  btnSelDate.innerHTML = inputDate.value;
  btnSelDate.classList.add('sel-date');
  clearInputPeople();
});

btnMinus.addEventListener('click', () => {
  if (inputDate.value.length == 0 || inputPeople.value == 1) {
    return;
  }
  btnPlus.classList.remove('none');
  inputPeople.value = Number(inputPeople.value) - 1;
  totalValue.innerHTML = Number(PAY * Number(inputPeople.value)).toLocaleString();
  if (inputPeople.value == 1) {
    btnMinus.classList.add('none');
  }
});

btnPlus.addEventListener('click', () => {
  if (inputDate.value.length == 0 || inputPeople.max == inputPeople.value) {
    return;
  }
  btnMinus.classList.remove('none');
  inputPeople.value = Number(inputPeople.value) + 1;
  totalValue.innerHTML = Number(PAY * Number(inputPeople.value)).toLocaleString();
  if (inputPeople.max == inputPeople.value) {
    btnPlus.classList.add('none');
  }
});

// 예약하기 버튼 눌렀을 때
btnRe.addEventListener('click', () => {
  // 결제 수단 체크여부
  let methodValue = "";
  payMethods.forEach(method => {
    if (method.checked) {
      isMethod = true;
      methodValue = method.value;
    }
  });
  if (isMethod && inputDate.value.length > 0) {
    const idx = location.search.replace('?idx=', '');
    const p_name = document.createElement('p');
    p_name.innerHTML = `<input hidden="hidden" type="text" name="e_name" value="${document.querySelector('.e-name').innerHTML}"/>`
    const p_price = document.createElement('p');
    p_price.innerHTML = `<input hidden="hidden" type="number" name="e_price" value="${PAY}"/>`
    const p_idx = document.createElement('p');
    p_idx.innerHTML = `<input hidden="hidden" type="number" name="e_idx" value="${idx}" />`
    const p_total = document.createElement('p');
    p_total.innerHTML = `<input hidden="hidden" type="number" name="total" value="${totalValue.innerHTML.replace(",", "")}"/>`;

    form.appendChild(p_name);
    form.appendChild(p_price);
    form.appendChild(p_idx);
    form.appendChild(p_total);

    form.submit();
  } else {
    alert('모든 항목을 선택해주세요');
  }
});
