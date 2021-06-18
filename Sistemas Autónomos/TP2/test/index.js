const { default: axios } = require("axios");
const fs = require('fs');

const serviceAccount = require('./fireStoreKey.json');
const databasePath = "./db.csv"
const header = [ "currentSpeed","freeFlowSpeed","currentTravelTime","freeFlowTravelTime","roadName","roadClosure","timeStamp"]

const roadMap = JSON.parse(fs.readFileSync("../roadMap.json"))
console.log("Lista de pontos subscritos: "+roadMap.map(ob=> {return (ob.roadName) }))
console.log("Obtendo API keys do TomTom...")

const apiKeys=fs.readFileSync("../../api.txt").toString().split("\n").filter((s)=>{return s.length>20})

// MIN INTERVAL 34.56s para funcionamento 24h
var interval = 35 * 1000; // 45s;
console.log("Obtendo dados a cada : " + interval/1000 + " segundos")

// Starting Iteration
var it=0;

function callAPI(){
    var l = roadMap.length
    var indx= it%l;
    var keyIndx = it%apiKeys.length
    it++
    var callCoordinate =""+ roadMap[indx].coordinate.latitude + ","+ roadMap[indx].coordinate.longitude 
    var callRoad = roadMap[indx].roadCode
    var call = `https://api.tomtom.com/traffic/services/4/flowSegmentData/relative0/10/json?point=${callCoordinate}&unit=KMPH&openLr=false&key=${apiKeys[keyIndx]}`
    axios.get(call)
    .then(response=>{
        var data = response.data.flowSegmentData
        console.log()
        var newData = [data.currentSpeed, data.freeFlowSpeed, data.currentTravelTime,data.freeFlowTravelTime,
            callRoad,data.roadClosure,new Date().toString().split("(")[0]]
        
        fs.appendFileSync(databasePath,newData.toString()+'\n')
        console.log("ADDED REPORT FOR:"+ roadMap[indx].roadName)
        
})  
    .catch(e=> {
        console.log("Error: ")
        it--
        console.log(e.Error)
        console.log(e)
        fs.writeFileSync("./errorLog.json", JSON.stringify(e))
        console.log("SCRIPT VAI PARAR NA ITERAÇÃO "+ it+"\nMais info em errorLog.json\nGoodbye :(")
        process.exit()

    })

}

setInterval( callAPI, interval/apiKeys.length);
