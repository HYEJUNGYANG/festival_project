const form = document.querySelector('#form');
const txtContent = document.querySelector('#txt_content');
const btnSubmit = document.querySelector('#btn-submit');

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
    txtContent.value = editor.getData();
    form.submit();
})