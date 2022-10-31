let usrName = document.getElementById("userName");
let roomName = document.getElementById("roomName");
let joinBtn = document.getElementById("join");
let leaveBtn = document.getElementById("leave");
let chats = document.getElementById("chats");
let mem = document.getElementById("mem");
let message = document.getElementById("msg");
let send = document.getElementById("send");

function addRoomNameDiv(rmName, userName) {
    let rmNameDiv = document.createElement("div");
    rmNameDiv.innerText = `${userName} has joined the ${rmName}`;
    chats.append(rmNameDiv);
    return;
}

function addMember(userName) {
    let nameDiv = document.createElement("div");
    nameDiv.innerText = `${userName}`;
    mem.append(nameDiv);
    return;
}

function addLeaveRoomDiv(rmName, userName) {
    let rmNameDiv = document.createElement("div");
    rmNameDiv.innerText = `${userName} has left the ${rmName}`;
    chats.append(rmNameDiv);
    return;
}

function removeMember(userName) {
    let memDivs = mem.getElementsByTagName("div");
    let divPos = 0;
    for(let i = 0; i < memDivs.length; i++) {
        if (memDivs[i].innerText === userName) {
            memDivs.removeChild(memDivs[i]);
        }
    }
    return;
}

function addMessageDiv(rmName, userName, message) {
    let messageDiv = document.createElement("div");
    messageDiv.innerText = `${userName} in ${rmName}: ${message}`;
    chats.append(messageDiv);
    return;
}

let wsOpen = false;

function joinRoom(event) {
    let name = usrName.value;
    let rmName = roomName.value;
    let i = 0;
    while (i < rmName.length) {
        let char = rmName.charAt(i);
        if (char !== char.toLowerCase() || char === ' ') {
            alert("Enter room name in correct format");
            return;
        }
        i++;
    }
    if (wsOpen) {
        wbSocket.send(`join ${name} ${rmName}`);
    }
}

function leaveRoom(event) {
    let name = usrName.value;
    let rmName = roomName.value;
    let i = 0;
    while (i < rmName.length) {
        let char = rmName.charAt(i);
        if (char !== char.toLowerCase() || char === ' ') {
            alert("Enter room name in correct format");
            return;
        }
        i++;
    }
    if (wsOpen) {
        wbSocket.send(`leave ${name} ${rmName}`);
    }
}

function sendChatMsg(event) {
    let name = usrName.value;
    let msg = message.value;
    if (wsOpen) {
        wbSocket.send(`${name} ${msg}`);
    }
}

function handleConnectCB(event) {
    wsOpen = true;
    console.log(event);
    console.log("Socket opened");
    //wbSocket.send("Hello");
}

function handleErrorCB(event) {
    console.log(event);
}

function handleCloseCB() {
    wsOpen = false;
}

function handleMessageCB(event) {
    let response = JSON.parse(event.data);
    console.log(response);
    if (response.type === "join") {
        addRoomNameDiv(response.room, response.user);
        addMember(response.user);
    }
    if (response.type === "leave") {
        addLeaveRoomDiv(response.room, response.user);
        removeMember(response.user);
    }

    if (response.type === "message") {
        addMessageDiv(response.room, response.user, response.message);
    }
}

let wbSocket = new WebSocket("ws://localhost:8080/");
wbSocket.onopen = handleConnectCB;
wbSocket.onclose = handleCloseCB;
wbSocket.onerror = handleErrorCB;
wbSocket.onmessage = handleMessageCB;

joinBtn.onclick = joinRoom;
leaveBtn.onclick = leaveRoom;
send.onclick = sendChatMsg;
