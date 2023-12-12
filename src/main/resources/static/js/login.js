const idInput = document.querySelector('.input-id');
const pwInput = document.querySelector('.input-pw');
const pwToggleBtn = document.querySelector('.pw-show');
const btnLogin = document.querySelector('.btn-login');
const form = document.querySelector('#form');
const warningBlank = document.querySelector('.blank');
const warningError = document.querySelector('.error');

let isClick = false;

pwToggleBtn.addEventListener('click', () => {
  pwInput.focus();
  pwInput.type = isClick ? 'password' : 'text';
  if (isClick) {
    pwToggleBtn.firstElementChild.classList.replace('fa-eye-slash', 'fa-eye');
  } else {
    pwToggleBtn.firstElementChild.classList.replace('fa-eye', 'fa-eye-slash');
  }
  isClick = !isClick;
});

btnLogin.addEventListener('click', () => {
  if (idInput.value.length == 0 || pwInput.value.length == 0) {
    warningBlank.style.display = 'block';
    if (warningError) warningError.style.display = 'none';
    warningBlank.innerHTML = '아이디 혹은 비밀번호를 입력해주세요';
    alert('아이디 혹은 비밀번호를 입력해주세요');
    return;
  }
  form.submit();
})