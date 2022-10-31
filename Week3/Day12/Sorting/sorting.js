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
    if(a.first >= b.first) {
        return true;
    }
    else {
        return false;
    }
}

function findSmallestIndex(array, startIndex, comparator) {
    let smallIndex = startIndex;
    for (let i = startIndex + 1; i < array.length; i++) {
        if (comparator(array[i] , array[smallIndex])) {
            smallIndex = i;
        }
    }
    return smallIndex;
}

function selectionSort(array) {
    for (let i = 0; i < array.length; i++) {
        let sIndex = findSmallestIndex(array, i, comparatorGreater);
        let temp = array[i];
        array[i] = array[sIndex];
        array[sIndex] = temp;
    }
}

function main() {
    let array = [4, 2, 0, 8, 3, 7, 21];
    console.log(array);

    selectionSort(array);
    console.log(array);
}

window.onload = main();