let canvas = document.getElementsByTagName("canvas")[0];
canvas.style.width = "1000px";
canvas.style.height = "1000px";
let cnvs = canvas.getContext("2d");


let beeHive = new Image();
beeHive.src = "beeHive.jpg";
// beeHive.width = "2px";
// beeHive.height = "2px";

function main() {
    cnvs.drawImage(beeHive, 10, 10, 20, 20);
}

window.onload = main;