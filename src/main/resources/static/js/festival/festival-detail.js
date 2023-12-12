const likeBtn = document.querySelector('.like');
const scrollBtn = document.querySelector('.scroll');

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

  fetch("/festival/detail", {
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

scrollBtn.addEventListener('click', () => {
  scrollTo({ top: 0, behavior: 'smooth' });
});
