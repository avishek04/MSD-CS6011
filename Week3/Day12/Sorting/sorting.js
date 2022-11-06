"use strict";

function comparator (a,b)
{
    if (a <= b) {
        return true;
    }
    else {
        return false;
    }
}

function comparatorGreater (a,b)
{
    if (a >= b) {
        return true;
    }
    else {
        return false;
    }
}

function comparatorFirstName (a,b)
{
    if(a.first <= b.first) {
        return true;
    }
    else {
        return false;
    }
}

function comparatorLastName (a,b)
{
    if(a.last <= b.last) {
        if (a.last === b.last) {
            return comparatorFirstName(a, b);
        }
        return true;
    }
    else {
        return false;
    }
}

// function findSmallestIndex(array, startIndex, comparator) {
//     let smallIndex = startIndex;
//     for (let i = startIndex + 1; i < array.length; i++) {
//         if (comparator(array[i] , array[smallIndex])) {
//             smallIndex = i;
//         }
//     }
//     return smallIndex;
// }

function findSmallestIndex(array, startIndex) {
    let smallIndex = startIndex;
    for (let i = startIndex + 1; i < array.length; i++) {
        if (array[i] < array[smallIndex]) {
            smallIndex = i;
        }
    }
    return smallIndex;
}
function selectionSort(array) {
// function selectionSort(array, func) {
    for (let i = 0; i < array.length; i++) {
        // let sIndex = findSmallestIndex(array, i, func);
        let sIndex = findSmallestIndex(array, i);
        let temp = array[i];
        array[i] = array[sIndex];
        array[sIndex] = temp;
    }
}

function main() {
  //   let myArray = [ -1, 8 ,0,-4 , 7, 99, 98]
  //   console.log(myArray) ;
  //
  //   selectionSort(myArray, comparator);
  //   console.log(myArray) ;
  //
  // let NamesArray =[{ first: "Sheldon" , last: "Cooper"},
  //     { first: "Leonard" , last: "Hofstader"},
  //     { first: "Penny" , last: ""},
  //     { first: "Howard" , last: "Wolowitz"},
  //     { first: "Rajesh" , last: "Koothrappali"},
  //     { first: "Stuart" , last: "Bloom"},
  //     { first: "Alex" , last: "Bloom"}] ;
  //
  // console.log(NamesArray) ;
  //
  // selectionSort(NamesArray, comparatorLastName);
  // console.log(NamesArray);
  //
  //   let myArray1 = [ -1.0, 8.908 ,0.98,4.978 , 7.66, 99.99, 99.999]
  //   console.log(myArray1) ;
  //
  //   selectionSort(myArray1, comparator);
  //   console.log(myArray1) ;

    let myArray2 = [ "aabbdd", "aabbcc" , "AABBCC", "AABBDD" , "AAbbcc", "aabbDD"]
    console.log(myArray2) ;

    selectionSort(myArray2);
    console.log(myArray2) ;

    let myArray3 = [ "aabbdd", 980128 , "AABBCC", 8.080 , 9092, 9999.9999]
    console.log(myArray3) ;

    selectionSort(myArray3);
    console.log(myArray3) ;
}

window.onload = main();