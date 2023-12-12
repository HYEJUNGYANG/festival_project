const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
const mapCon = document.querySelector('.map-container');

const options = {
  //지도를 생성할 때 필요한 기본 옵션
  // 위도, 경도 순서
  center: new kakao.maps.LatLng(mapCon.dataset.l, mapCon.dataset.h), //지도의 중심좌표.
  level: 3 //지도의 레벨(확대, 축소 정도)
};

const map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

// 마커가 표시될 위치입니다
const markerPosition = new kakao.maps.LatLng(mapCon.dataset.l, mapCon.dataset.h);

// 마커를 생성합니다
const marker = new kakao.maps.Marker({ position: markerPosition });

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

console.log(mapCon.dataset.l);
console.log(mapCon.dataset.h);

// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
// marker.setMap(null);
