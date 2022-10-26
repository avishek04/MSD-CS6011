
let xVal = document.getElementById("xValue");
let yVal = document.getElementById("yValue");
let btn = document.getElementsByTagName("button")[0];
let result = document.getElementById("result");
let wbResult = document.getElementById("wbResult");
let wsOpen = false;

function handleConnectCB() {
    wsOpen = true;
}

function handleErrorCB(event) {
    console.log(event.target.response);
}

function handleCloseCB() {
    wsOpen = false;
}

function handleMessageCB(event) {
    wbResult.value = event.data;
}

function handleSuccessResponse(event) {
    result.value = event.target.response;
}

function handleErrorResponse() {

}

let httpReq = new XMLHttpRequest();
httpReq.addEventListener("error", handleErrorResponse);
httpReq.addEventListener("load", handleSuccessResponse);

let wbSocket = new WebSocket("ws://localhost:8080/");
wbSocket.onopen = handleConnectCB;
wbSocket.onclose = handleCloseCB;
wbSocket.onerror = handleErrorCB;
wbSocket.onmessage = handleMessageCB;

function addNumbers() {
    let x = parseFloat(xVal.value);
    let y = parseFloat(yVal.value);

    httpReq.open("GET", `http://localhost:8080/calculate?x=${x}&y=${y}`);
    httpReq.send();

    if (wsOpen) {
        wbSocket.send(`${x} ${y}`);
    }
}

function handleKeyPressCB(event) {
    if (event.keyCode === 13 && xVal.value.length > 0) {
        let x = parseFloat(xVal.value);
        if (isNaN(x)) {
            alert("Please make sure x is a number");
            xVal.value = '';
            return;
        }
    }

    if (event.keyCode === 13 && yVal.value.length > 0) {
        let y = parseFloat(yVal.value);
        if (isNaN(y)) {
            alert("Please make sure y is a number");
            yVal.value = '';
            return;
        }
    }
}

xVal.addEventListener("keypress", handleKeyPressCB);
yVal.addEventListener("keypress", handleKeyPressCB);

btn.onclick = addNumbers;