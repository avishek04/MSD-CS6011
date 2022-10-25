document.writeln("hello world");
console.log("hello world");

arr = ["Hello", true, 10, 5.67, { name: "Avishek", age: 26}];
console.log(arr);

arr[0] = "Hello World";
console.log(arr);

function f(a, b) {
    return a + b;
}

let myFunction = function (a, b) {
    return a + b;
}

console.log(f(5,6));
console.log(myFunction(5, 6));

console.log(f(0.5, 0.6));
console.log(myFunction(0.5, 0.6));

console.log(f("Hello", "World"));
console.log(myFunction("Hello", "World"));

console.log(f(1, "World"));
console.log(myFunction(1, "World"));