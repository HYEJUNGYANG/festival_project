const pwShows = document.querySelectorAll('.pw-show');
const btnChange = document.querySelector('.btn-change');
const btnClose = document.querySelector('.btn-close');
const inputPw = document.querySelector('.input-pw');
const inputNewPw = document.querySelector('.input-new-pw');
const inputNewPwChk = document.querySelector('.input-new-pw-check');
const pwWarning = document.querySelector('.pw-warning');
const NewpwWarning = document.querySelector('.new-pw-warning');
const NewpwChkWarning = document.querySelector('.new-pw-check-warning');

addEventListener('keydown', e => {
  const key = e.key;
  if (key == 'Escape') {
    window.close();
  }
});

pwShows.forEach(pwShow => {
  pwShow.addEventListener('click', () => {
    const input = pwShow.previousElementSibling;
    const icon = pwShow.firstElementChild;
    const condition = input.type == 'password';
    icon.classList.replace(
        `${condition ? 'fa-eye' : 'fa-eye-slash'}`,
        `${condition ? 'fa-eye-slash' : 'fa-eye'}`
    );
    input.focus();
    input.type = condition ? 'text' : 'password';
  });
});

btnClose.addEventListener('click', () => {
  window.close();
});
const xhr = new XMLHttpRequest();
var currentPassword = inputPw.value;
var newPassword =inputNewPw.value;
btnChange.addEventListener('click', () => {
  //form.action = "/mypage/profile/pw-change";
  xhr.open('POST', '/mypage/profile/pw-change', true);
  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xhr.send(`pw=${inputPw.value}&new_pw=${inputNewPw.value}`);
  xhr.onreadystatechange = function () {
    if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
      if (xhr.response == "success") {
        alert('비밀번호가 변경되었습니다!');
        window.close();
      } else if(xhr.response == "fail"){
        alert('실패하였습니다!');
        console.log('fail')
      }
    }
    else if (xhr.status == 500){
      alert('실패2!');
    }

  };
});


inputNewPw.addEventListener("keyup", function () { //새 비밀번호 입력시 패턴과 맞을때까지 warning 출력

  // 새 비밀번호 값.
  let new_pw = this.value;
  // 비밀번호 패턴 정규식
  var pwPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;

  // 입력한 비밀번호가 패턴에 맞지 않으면 경고 메시지를 보이게 하고, 그렇지 않으면 숨깁니다.
  if (pwPattern.test(new_pw)) {
    NewpwWarning.style.visibility = "hidden";
  } else {
    NewpwWarning.style.visibility = "visible";
    NewpwWarning.innerText = "새 비밀번호는 영어, 숫자, 특수문자 조합 8-16자여야 합니다.";
  }
});

inputNewPwChk.addEventListener("keyup", function () { ////비밀번호 확인란 입력시 warning 숨기기


  NewpwChkWarning.style.visibility = "hidden";

});

document.querySelector("#pw-form").addEventListener("submit", function (event) {
  event.preventDefault(); // form의 기본 제출 동작을 취소합니다.

  var pwPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;

  //새 비밀번호 패턴 검증
  if (!pwPattern.test(inputNewPw.value)) {
    NewpwWarning.focus();
    NewpwWarning.style.visibility = "visible";
    NewpwWarning.innerText = "새 비밀번호는 영어, 숫자, 특수문자 조합 8-16자여야 합니다.";

    return false;
  }
  //비밀번호 확인과 동일한지 체크
  if (inputNewPw.value != inputNewPwChk.value) {
    inputNewPwChk.focus();
    NewpwChkWarning.style.visibility = "visible";
    NewpwChkWarning.innerText = "비밀번호 확인이 일치하지 않습니다.";
    return false;
  }
  // form을 제출합니다.
  event.target.submit();
});
