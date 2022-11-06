"use strict";

let canvas = document.getElementsByTagName("canvas")[0];
let ctx = canvas.getContext("2d");

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

let canWid = canvas.width;
let canHei = canvas.height;

let beeImg = new Image();
beeImg.src = "beeHive.jpg";
let beeImgxPos = 20;
let beeImgyPos = 20;

let gameOver = false;

let numEnem = 5;
let enem = {};
enem.img = new Image();
enem.img.src = "bees.jpg";
enem.xPos = Math.floor(Math.random() * canWid);
enem.yPos = Math.floor(Math.random() * canHei);

let enemies = [];

function main() {
    generateEnemies();
    window.requestAnimationFrame(animate);
}

window.onload = main;


function generateEnemies () {

    for (let i = 0; i < numEnem; i ++) {
        let enem = {};
        enem.img = new Image();
        enem.img.src = "bees.jpg";
        enem.xPos = Math.floor(Math.random() * canWid);
        enem.yPos = Math.floor(Math.random() * canHei);
        enemies.push(enem);
    }
}

function animate () {
    erase();
    ctx.drawImage(beeImg, beeImgxPos, beeImgyPos, 80, 100);
    for (let i = 0; i < numEnem; i ++) {
        ctx.drawImage(enemies[i].img, enemies[i].xPos, enemies[i].yPos, 100, 100);
        if (Math.abs((beeImgxPos - enemies[i].xPos)) > 10) {
            if (enemies[i].xPos >= beeImgxPos) {
                enemies[i].xPos -= 5;
            } else {
                enemies[i].xPos += 5;
            }
        }

        if (Math.abs((beeImgyPos - enemies[i].yPos)) > 10) {
            if (enemies[i].yPos >= beeImgyPos) {
                enemies[i].yPos -= 5;
            } else {
                enemies[i].yPos += 5;
            }
        }
        if (Math.abs((beeImgxPos - enemies[i].xPos)) < 10 && Math.abs((beeImgyPos - enemies[i].yPos)) < 10) {
            gameOver = true;
        }
    }
    if (!gameOver) {
        window.requestAnimationFrame(animate);
    }
}

function erase () {
    ctx.fillStyle = "#FFFFFF";
    ctx.fillRect(0, 0, canWid, canHei);
}

document.onmousemove = followMouse;

function followMouse(e) {
    beeImgxPos = e.x;
    beeImgyPos = e.y;
}