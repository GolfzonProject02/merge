// img_slide
function next(x) {
    let slides = document.querySelector(`.${x}_slides_img`);
    let slides_img = document.querySelectorAll(`.${x}_slides_img div`);
    if(slides_img[slides_img.length-2].style.display == 'none') {
        for(let i = slides_img.length -1; i >= 0; i--){
            slides_img[i].style.display = 'block';
        }
    } else {
        for (let i = 0; i < slides_img.length; i++) {
            if(slides_img[i].style.display != 'none') {
                return slides_img[i].style.display = 'none';
            }
        }
    }
}
function prev(x) {
    let slides = document.querySelector(`.${x}_slides_img`);
    let slides_img = document.querySelectorAll(`.${x}_slides_img div`);
    if(slides_img[0].style.display != 'none') {
        for(let i = 0; i < slides_img.length-1; i++){
            slides_img[i].style.display = 'none';
        }
    } else {
        for (let i = 0; i < slides_img.length; i++) {
            if(slides_img[i].style.display != 'none') {
                return slides_img[i-1].style.display = 'block';
            }
        }
    }
}