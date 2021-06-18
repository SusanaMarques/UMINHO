const { default: axios } = require("axios");
const fs = require('fs');

const databasePath = "../coordsdb.json"
const roadMap = JSON.parse(fs.readFileSync("../roadMap.json"))
console.log("Lista de pontos subscritos: "+roadMap.map(ob=> {return (ob.roadName) }))
console.log("Obtendo API keys do TomTom...")

const apiKeys=fs.readFileSync("../../api.txt").toString().split("\n").filter((s)=>{return s.length>20})[1]

var coordsN101=[]
var coordsN103=[]
var coordsCent=[]
var coordMap= new Map()

async function callAPI(){
    element = roadMap[it++]
    if(it==roadMap.length){
        process.exit()
    }
    
    var callCoordinate =""+ element.coordinate.latitude + ","+ element.coordinate.longitude 
    var callRoad = element.roadCode
    var call = `https://api.tomtom.com/traffic/services/4/flowSegmentData/relative0/10/json?point=${callCoordinate}&unit=KMPH&openLr=false&key=${apiKeys}`
    axios.get(call)
    .then(response=>{
        var data = response.data.flowSegmentData
        console.log()
        console.log(it)
        var ret= JSON.stringify(data.coordinates.coordinate)
        fs.appendFileSync(databasePath,ret.toString()+"\n")
        var key = element.roadCode.split('.')[0]
        console.log(key)
        
})  
    .catch(e=> {
        console.log("Error: ")
        console.log(e.Error)
        console.log(e)
        fs.writeFileSync("./errorLog.json", JSON.stringify(e))
        process.exit()

    })

}
it=0
setInterval(callAPI,1000 *10)