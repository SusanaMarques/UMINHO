const { default: axios } = require("axios");
const fs = require('fs');

file = fs.readFileSync("../1").toString().split("\n")
var rua =""
for(var line in file){
    lspl =line.split(":")
    if(lspl[1]=="")
        rua = lspl[0]
    else{
        [lat, long] = lspl[1].split(",")
    }
}