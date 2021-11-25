/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const imgDiv = document.querySelector('update-pic-div');
const img = document.querySelector('#previewPhoto');
const file = document.querySelector('#file');
const uploadBtn = document.querySelector('#uploadBtn');

file.addEventListener('change', function(){
    const choosedFile = this.files[0];
    if (choosedFile){
        const reader = new FileReader();

        reader.addEventListener('load', function
        (){
            img.setAttribute('src', reader.result);
        });
        reader.readAsDataURL(choosedFile);
    }
});
