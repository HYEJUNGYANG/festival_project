const form = document.querySelector('#form');
const btnSubmit = document.querySelector('.btn-submit');

let editor;

ClassicEditor.create(document.querySelector('#editor'), {
  language: 'ko',
  toolbar: ['heading', '|', 'bold', 'italic']
})
  .then(newEditor => {
    editor = newEditor;
  })
  .catch(error => {
    console.error(error);
  });

btnSubmit.addEventListener('click', () => {
  const idx = location.search.replace('?idx=', '');
  const pathName = location.pathname;

  if (pathName.includes('modify')) {
    form.action = "/qna/modify";
  } else {
    form.action = "/qna/write";
  }

  const p1 = document.createElement('p');
  p1.innerHTML = `<input name="content" type="text" value="${editor.getData()}" />`;
  form.appendChild(p1);
  const p2 = document.createElement('p');
  p2.innerHTML = `<input name="idx" type="number" value="${idx}"/>`;
  form.appendChild(p2);

  form.submit();
});
