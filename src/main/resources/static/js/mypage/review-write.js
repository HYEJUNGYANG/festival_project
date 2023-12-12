const starBtns = document.querySelectorAll('.star');
const txt = document.querySelector('.input-txt');
const charNum = document.querySelector('.char-num > p > span');
const btnClose = document.querySelector('.btn-close');
const btnSubmit = document.querySelector('.btn-submit');
const eName = document.querySelector('.e-name');
const form = document.querySelector('#form');

let starCount = form.dataset.star; // 0은 입력 안된 상태, 1~5까지 값이 입력된 상태임

function handleStarIcon(idx) {
  starBtns.forEach(star => {
    star.firstElementChild.innerHTML = `<i class="fa-regular fa-star"></i>`;
  });
  for (let i = 0; i <= idx; i++) {
    const icon = starBtns[i].firstElementChild;
    icon.innerHTML = `<i class="fa-solid fa-star"></i>`;
  }
}

// esc 키 눌렀을 때 창 닫히도록 설정
window.addEventListener('keyup', e => {
  const key = e.key;
  if (key == 'Escape') {
    window.close();
  }
});

starBtns.forEach((starBtn, idx) => {
  starBtn.addEventListener('click', () => {
    handleStarIcon(idx);
    starCount = idx + 1;
  });
});

txt.addEventListener('input', () => {
  charNum.innerHTML = txt.value.length;
});

btnClose.addEventListener('click', () => {
  window.close();
});

const xhr = new XMLHttpRequest();
// 리뷰 수정, 작성
btnSubmit.addEventListener('click', () => {
  const num = location.search.replace('?num=', '');
  const idx = location.search.replace('?idx=', '');
  const pathName = location.pathname;

  if (pathName.includes('modify')) {
    form.action = "/mypage/review/modify";
    xhr.open('POST', '/mypage/review/modify', true);

    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(`content=${txt.value}&e_idx=${eName.dataset.idx}&e_name=${eName.innerHTML}&star=${starCount}&idx=${idx}`);
    xhr.onreadystatechange = function () {
      if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
        alert('리뷰 수정이 완료되었습니다!');
        window.close();
      }
      else if (xhr.status == 500){
        alert('실패하였습니다!');
      }
    };
  } else {
    form.action = "/mypage/review/write";
    xhr.open('POST', '/mypage/review/write', true);

    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(`content=${txt.value}&e_idx=${eName.dataset.idx}&e_name=${eName.innerHTML}&star=${starCount}&num=${num}`);
    xhr.onreadystatechange = function () {

      if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
        alert('리뷰 작성이 완료되었습니다!');
        window.close();
      }
      else if (xhr.status == 500){
        alert('실패하였습니다!');
      }
    };
  }
});
