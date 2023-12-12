const btnNickModify = document.querySelector('.btn-nick-modify');
const btnTelModify = document.querySelector('.btn-tel-modify');
const inputNick = document.querySelector('.input-nick');
const inputTel = document.querySelector('.input-tel');
const btnPwChange = document.querySelector('.btn-pw-change');

function handleInputActive(element) {
  const input = element.previousElementSibling;
  const isModify = input.classList.contains('modify');
  input.readOnly = isModify ? true : false;
  element.innerHTML = isModify ? '수정' : '확인';
  element.setAttribute('type', isModify ? 'submit' : 'button'); // 버튼 누를때마다 submit과 button으로 type변경
  input.classList.toggle('modify');
}

function handleEnterKey(element, event) {
  const key = event.key;
  if (
    key == 'Enter' &&
    element.previousElementSibling.classList.contains('modify')
  )
    handleInputActive(element);
}

btnNickModify.addEventListener('click', () => {
  handleInputActive(btnNickModify);
});

btnTelModify.addEventListener('click', () => {
  handleInputActive(btnTelModify);
});

inputTel.addEventListener('input', () => {
  inputTel.value = inputTel.value
    .replace(/[^0-9]/g, '')
    .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
});

inputNick.addEventListener('keydown', e => {
  handleEnterKey(btnNickModify, e);
});

inputTel.addEventListener('keydown', e => {
  handleEnterKey(btnTelModify, e);
});

btnPwChange.addEventListener('click', () => {
  const width = window.screen.width;
  const height = window.screen.height;

  const popWidth = 850;
  const popHeight = 650;
  open(
    '/mypage/profile/pw-change',
    'review',
    `resizable=no width=${popWidth}, height=${popHeight} top=${
      height / 2 - popHeight / 2
    } left=${width / 2 - popWidth / 2}`
  );
});
