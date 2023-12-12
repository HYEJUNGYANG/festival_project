const likeBtn = document.querySelector('.like');
const btnReservation = document.querySelector('.btn-re');
const scrollBtn = document.querySelector('.scroll');
const modifyBtns = document.querySelectorAll('.btn-modify');
const btnDeletes = document.querySelectorAll('.btn-del');

// db에 넘기기 전에는 like 여부 알 수 없으니 테스트용으로 변수 생성
let isLike = false;

addEventListener('scroll', () => {
  const y = scrollY;
  if (y > 150) {
    scrollBtn.classList.add('show');
  } else {
    scrollBtn.classList.remove('show');
  }
});

likeBtn.addEventListener('click', () => {
  let itemId = likeBtn.dataset.itemId; // data-item-id 값을 가져옵니다.

  fetch("/experience/detail", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body: "itemId=" + itemId
  })
      .then(response => {
        if (!response.ok) {
          throw new Error("HTTP error " + response.status);
        }
        // 찜 목록 업데이트 후 처리할 로직
        window.location.href = response.url; // 페이지를 새로고침합니다.
      })
      .catch(error => console.error(error));
});

btnReservation.addEventListener('click', () => {
  if (btnReservation.dataset.count == 0) {
    alert('예약 가능한 인원이 모두 찼습니다!');
    return;
  }
  const idx = location.search.replace("?idx=", "");
  location.href = `/reservation?idx=${idx}`;
});

scrollBtn.addEventListener('click', () => {
  scrollTo({ top: 0, behavior: 'smooth' });
});

modifyBtns.forEach(modify => {
  modify.addEventListener('click', () => {
    const width = window.screen.width;
    const height = window.screen.height;

    const popWidth = 650;
    const popHeight = 500;
    open(
        `/mypage/review/modify?idx=${modify.dataset.idx}`,
      'review',
      `resizable=no width=${popWidth}, height=${popHeight} top=${
        height / 2 - popHeight / 2
      } left=${width / 2 - popWidth / 2}`
    );
  });
});

btnDeletes.forEach((btnDelete, idx) => {
  btnDelete.addEventListener('click', () => {

    const idx = btnDelete.dataset.idx;
    if (confirm('정말 삭제하시겠습니까?')) {
      location.href = `/mypage/review/delete?idx=${idx}`;
    }
  })
});