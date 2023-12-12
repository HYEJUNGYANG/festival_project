const imgBg = document.querySelector('.show-align');
const rightBtn = document.querySelector('.control-right');
const leftBtn = document.querySelector('.control-left');
const imgSlide = document.querySelector('.show-img');
const imgSlideLength = document.querySelectorAll('.img-slider').length - 2;
const conSlideLength = document.querySelectorAll('.content-slider').length - 2;
const state = document.querySelector('.state');
const pauseBtn = document.querySelector('.control-btn');
const play = document.querySelector('.control-btn > i');
const pause = document.querySelector('.fa-pause');
const stateBar = document.querySelector('.state-bar');
const conSlide = document.querySelector('.contents');

// 임시 데이터 나중에 따로 설정하기
const imgColorData = {
  0: '#FFDFDC',
  1: '#FDF5DC',
  2: 'rgb(222, 247, 255)', // 청양알프스
  3: 'rgb(255, 231, 225)', // 동백수목원
  4: '#E6FFE6', //롯데월드
  5: '#FFDFDC', //동백포레스트
  6: '#FDF5DC' //일루미네이션
};
const sec = 6000;

let nIntervId;

let imgNum = 1;
init();
intervStart();

function init() {
  imgSlide.style.width = `${45 * (imgSlideLength + 2)}vw`;
  imgSlide.style.transform = `translateX(-45vw)`;
  conSlide.style.width = `${22 * (conSlideLength + 2)}vw`;
  conSlide.style.transform = `translateX(-22vw)`;
}

function intervStart() {
  stateBar.classList.add('full');
  if (!nIntervId) {
    nIntervId = setInterval(() => {
      stateBar.classList.remove('full');
      handleRightSlide();
      stateBar.classList.add('full');
    }, sec);
  }
}

function handlePrevImg() {
  console.log(`왼쪽 버튼 클릭 했음!! ${imgNum}`);
  imgSlide.style.transform = `translateX(${0 - 45 * imgNum + 45}vw)`;
  imgSlide.style.transition = `0.8s`;
  conSlide.style.transform = `translateX(${0 - 22 * imgNum + 22}vw)`;
  conSlide.style.transition = `0.8s`;
  imgNum--;
  imgBg.style.backgroundColor = imgColorData[imgNum];
  imgBg.style.transition = `0.8s`;
  if (imgNum == 0) {
    state.innerHTML = `05`;
    return;
  }
  state.innerHTML = `0${imgNum}`;
}

function handleNextImg() {
  console.log(`오른쪽 버튼 클릭 했음!! ${imgNum}`);
  imgSlide.style.transform = `translateX(${0 - 45 * (imgNum + 1)}vw)`;
  imgSlide.style.transition = `0.8s`;
  conSlide.style.transform = `translateX(${0 - 22 * (imgNum + 1)}vw)`;
  conSlide.style.transition = `0.8s`;
  imgNum++;
  imgBg.style.backgroundColor = imgColorData[imgNum];
  imgBg.style.transition = `0.8s`;
  if (imgNum == imgSlideLength + 1) {
    state.innerHTML = `01`;
    return;
  }
  state.innerHTML = `0${imgNum}`;
}

function handlePauseOrPlay() {
  if (play.className.includes('fa-pause')) {
    play.classList.replace('fa-pause', 'fa-play');
  } else {
    play.classList.replace('fa-play', 'fa-pause');
  }
}

function handleRightSlide() {
  if (imgNum == imgSlideLength) {
    rightBtn.disabled = true;
    handleNextImg();
    setTimeout(() => {
      imgSlide.style.transform = `translateX(-45vw)`;
      imgSlide.style.transition = `0s`;
      conSlide.style.transform = `translateX(-22vw)`;
      conSlide.style.transition = `0s`;
      imgNum = 1;
      rightBtn.disabled = false;
    }, 800);
    return;
  }
  handleNextImg();
}

function intervStop() {
  const icon = document.querySelector('.fa-pause');
  icon.classList.replace('fa-pause', 'fa-play');
  clearInterval(nIntervId);
  // 변수에서 intervalID를 해제
  nIntervId = null;
  stateBar.classList.remove('full');
}

rightBtn.addEventListener('click', () => {
  if (nIntervId) {
    intervStop();
  }
  handleRightSlide();
});

leftBtn.addEventListener('click', () => {
  if (nIntervId) {
    intervStop();
  }
  if (imgNum == 1) {
    leftBtn.disabled = true;
    handlePrevImg();
    setTimeout(() => {
      imgSlide.style.transform = `translateX(-${45 * imgSlideLength}vw)`;
      imgSlide.style.transition = `0s`;
      conSlide.style.transform = `translateX(-${22 * conSlideLength}vw)`;
      conSlide.style.transition = `0s`;
      imgNum = imgSlideLength;
      leftBtn.disabled = false;
    }, 800);

    return;
  }
  handlePrevImg();
});

pauseBtn.addEventListener('click', () => {
  if (document.querySelector('.fa-pause')) {
    intervStop();
  } else {
    const icon = document.querySelector('.fa-play');
    icon.classList.replace('fa-play', 'fa-pause');
    intervStart();
  }
});
