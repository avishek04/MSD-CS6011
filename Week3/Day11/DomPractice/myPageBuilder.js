
function main() {
    let h1 = document.createElement("h1");
    let text = document.createTextNode("Hello World");
    h1.append(text);

    let p = document.createElement("p");
    let textP = document.createTextNode("Welcome to CS6011");
    p.append(textP);

    document.body.append(h1);
    document.body.append(p);
}
window.onload = main;