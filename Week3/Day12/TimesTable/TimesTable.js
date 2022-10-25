function main() {
    document.body.style.backgroundColor = "lightblue";
    let table = document.createElement("table");
    let th = document.createElement("th");
    let nIntervId = null;
    let colorIndx = 0;
    let color = "#FFFFFF";

    function changeColor() {
        if (!nIntervId) {
            nIntervId = setInterval(flashBackground, 1000);
        }
    }

    function stopColor() {
        clearInterval(nIntervId);
        // release our intervalID from the variable
        nIntervId = null;
    }

    function flashBackground() {
        document.body.style.backgroundColor = generateRandomColor();
    }

    function generateRandomColor() {
        let letters = '0123456789ABCDEF';
        colorIndx = Math.floor(Math.random() * 6 + 1);
        letters.indexOf(color[colorIndx + 1]);

        let firstPart = color.substr(0, colorIndx);
        let lastPart = color.substr(colorIndx + 1);

        color = firstPart + letters[(letters.indexOf(color[colorIndx])) % 15 + 1] + lastPart;
        return color;
    }

    for (let i = 0; i < 11; i++) {
        let td = document.createElement("td");
        td.style.border = "1px solid red";
        td.style.padding = "10px";
        td.style.margin = "1px";
        if (i !== 0) {
            td.innerText = i.toString();
        }
        th.append(td);
    }
    table.append(th);
    let row = 0, col = 0, bRow = 0, bCol = 0;
    for (let i = 1; i < 11; i++) {
        let tr = document.createElement("tr");
        for (let j = 0; j < 11; j++) {
            let numTd = document.createElement("td");
            numTd.innerText = (j * i).toString();
            if (j === 0) {
                numTd.innerText = i.toString();
            }
            numTd.style.border = "1px solid red";
            numTd.style.padding = "10px";
            numTd.style.margin = "1px";
            tr.append(numTd);
        }
        table.append(tr);
    }

    for (let i = 0; i < 10; i++) {
        let cTr = table.getElementsByTagName("tr")[i];
        for (let j = 0; j < 11; j++) {
            cTr.getElementsByTagName("td")[j].addEventListener("mouseover", function(event) {
                table.getElementsByTagName("tr")[bRow].getElementsByTagName("td")[bCol].style.border = "1px solid red";
                cTr.getElementsByTagName("td")[j].style.border = "3px solid blue";
                bRow = i;
                bCol = j;
            });
            cTr.getElementsByTagName("td")[j].addEventListener("click", function(event) {
                table.getElementsByTagName("th")[0].getElementsByTagName("td")[col].style.backgroundColor = document.body.style.backgroundColor;
                table.getElementsByTagName("tr")[row].getElementsByTagName("td")[0].style.backgroundColor = document.body.style.backgroundColor;
                table.getElementsByTagName("tr")[row].getElementsByTagName("td")[col].style.backgroundColor = document.body.style.backgroundColor;
                cTr.getElementsByTagName("td")[j].style.backgroundColor = "red";
                table.getElementsByTagName("th")[0].getElementsByTagName("td")[j].style.backgroundColor = "red";
                cTr.getElementsByTagName("td")[0].style.backgroundColor = "red";
                row = i;
                col = j;
            });
        }
    }

    document.body.append(table);

    let strBtn = document.createElement("button");
    strBtn.innerText = "Start";
    strBtn.addEventListener("click", changeColor);


    let stpBtn = document.createElement("button");
    stpBtn.innerText = "Stop";
    stpBtn.addEventListener("click", stopColor);

    document.body.append(strBtn);
    document.body.append(stpBtn);
}

window.onload = main;