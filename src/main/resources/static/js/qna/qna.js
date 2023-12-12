const replyCon = document.querySelectorAll('.qna-detail-reply');
const qnaTitles = document.querySelectorAll('.list-align > .qna-title');
const btnWrite = document.querySelector('.btn-write');

let conCount = -1;

if (btnWrite != null) {
  btnWrite.addEventListener('click', () => {
    location.href = `/qna/write`;
  });
}

qnaTitles.forEach((title, idx) => {
  title.addEventListener('click', () => {
    if (title.firstElementChild.classList.contains('secret')) {
      alert('비공개 문의내역은 작성자 본인만 확인할  수 있습니다.');
      return;
    }
    replyCon[idx].classList.toggle('show');
    if (conCount != -1 && !(idx == conCount)) {
      replyCon[conCount].classList.remove('show');
    }
    conCount = idx;
  });
});