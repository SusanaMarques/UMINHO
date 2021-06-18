const form = document.querySelector(".top-banner form");
const input = document.querySelector(".top-banner input");
const msg = document.querySelector(".top-banner .msg");
const list = document.querySelector(".ajax-section .cities");

const apiKey = "b13742f412a674ccbdc4542af86bbce3";

var t=0; 

//create a client instance
client = new Paho.MQTT.Client("io.adafruit.com", Number(443), "JS_Client");
//set callback handlers
client.onConnectionLost = onConnectionLost;
client.onMessageArrived = onMessageArrived;
//connect the client
client.connect({onSuccess:onConnect, userName:"SusanaMarques",
password:"aio_zorT69vzZZTDox4KySzZxTyiV0WC", useSSL:true, mqttVersion:4});

form.addEventListener("submit", e => {
    e.preventDefault();
    let inputVal = input.value;
    //check if there's already a city
    const listItems = list.querySelectorAll(".ajax-section .city");
    const listItemsArray = Array.from(listItems);
  

    if (listItemsArray.length > 0) {
      const filteredArray = listItemsArray.filter(el => {
        let content = "";
        //athens,gr
        if (inputVal.includes(",")) {
          //athens,grrrrrr->invalid country code, so we keep only the first part of inputVal
          if (inputVal.split(",")[1].length > 2) {
            inputVal = inputVal.split(",")[0];
            content = el
              .querySelector(".city-name span")
              .textContent.toLowerCase();
          } else {
            content = el.querySelector(".city-name").dataset.name.toLowerCase();
          }
        } else {
          //athens
          content = el.querySelector(".city-name span").textContent.toLowerCase();
        }
        return content == inputVal.toLowerCase();
      });

      if (filteredArray.length > 0) {
        msg.textContent = `You already know the weather for ${
          filteredArray[0].querySelector(".city-name span").textContent
        } ...otherwise be more specific by providing the country code as well`;
        form.reset();
        input.focus();
        return;
      }
    }

    fetch(`https://geocode.xyz/${inputVal}?json=1`)
      .then(response => response.json())
      .then(cityInfo => fetchData(inputVal, cityInfo))

//ajax loading

    msg.textContent = "";
    form.reset();
    input.focus();
  });

//called when the client connects
function onConnect() {
  console.log("onConnect");
  //subscribe
  client.subscribe("SusanaMarques/feeds/sensorfeed");
}

//called when the client loses its connection
function onConnectionLost(responseObject) {
  if (responseObject.errorCode !== 0) {
    console.log("onConnectionLost:" + responseObject.errorMessage);
  }
}

//called when a message arrives
function onMessageArrived(message) {
  console.log("onMessageArrived:" + message.payloadString);
  //var h1 = document.createElement("h1");
  //h1.appendChild(document.createTextNode(message.payloadString));
  //document.body.appendChild(h1);
}


function fetchData(inputVal, cityInfo){

  latt = cityInfo['latt']
  longt = cityInfo['longt']

  //para conectar com a api
  const onecall_url = `https://api.openweathermap.org/data/2.5/onecall?lat=${latt}&lon=${longt}&exclude=current,minutely,alerts&appid=${apiKey}&units=metric`
  const current_weather_url = `https://api.openweathermap.org/data/2.5/weather?q=${inputVal}&appid=${apiKey}&units=metric`;

  fetch(current_weather_url)
    .then(response => response.json())
    .then(data => {
      const { main, name, sys, weather } = data;
      const icon = `https://s3-us-west-2.amazonaws.com/s.cdpn.io/162656/${
        weather[0]["icon"]
      }.svg`;
      


      fetch(onecall_url)
        .then(response => response.json())
        .then(data => { 
          var i=0; var uvii=0; var tempp=0;var tempmin=50; var hum=0;
          list.textContent = '';
          while(i<12){
            var d = new Date();
            d.setHours(d.getHours() + i);
            const temp = data['hourly'][d.getHours()]['temp'];
            const icon = `https://s3-us-west-2.amazonaws.com/s.cdpn.io/162656/${
              data['hourly'][d.getHours()]['weather'][0]['icon']
            }.svg`;
            const humidity = data['hourly'][d.getHours()]['humidity'];
            const description = data['hourly'][d.getHours()]['weather'][0]['description'];
           const uvi = data['daily'][0]['uvi'];
            console.log(temp, icon, description);
            displayHourlyInfo(d.getHours()+":00",name, sys.country, temp, icon, description, uvi, humidity);
            if(uvi>uvii) uvii=uvi;
            if(humidity> hum) hum= humidity;
            if(temp>tempp) tempp=temp;
            if(temp<tempmin) tempmin=temp;
            i++;
          }
          send_to_adafruit(tempp,tempmin, uvii, hum);
        });

    })
    .catch(() => {
      msg.textContent = "Please search for a valid city";
    });
  }

  function displayHourlyInfo(hourss,cityName, country, temp, icon, description, uvi, humidity){
    console.log("AQUI")
    temperatura = Math.round(temp);
    var chare = '\u00E9';

    const li = document.createElement("li");
    li.classList.add("city");
    const markup = `
      <h2 class="city-name" data-name="${cityName},${country}">
        <span>${cityName}</span>
        <sup>${country}</sup>
        <span>${hourss}</span>
      </h2>
      <span style="font-weight:900">${"UV: "}</span><span">${uvi+ " | "}</span>
      <span style="font-weight:900">${"Humidity: "}</span><span">${humidity}</span>
      <div class="city-temp">${temperatura}<sup>&#176C</sup>
      </div>
      <figure>
        <img class="city-icon" src="${icon}" alt="${description}">
        <figcaption>${description}</figcaption>
      </figure>
    `;

    
    li.innerHTML = markup;
    list.appendChild(li);
  }

  function send_to_adafruit( temp,temp_min, uvi, humidity){
  temperatura = Math.round(temp);
  temperaturaa = new Paho.MQTT.Message(" "+temperatura);
    temperaturaa.destinationName = "SusanaMarques/feeds/TemperatureMax";
    client.send(temperaturaa);

    temperatura_min = Math.round(temp_min);
    temperaturaa_min = new Paho.MQTT.Message(" "+temperatura_min);
      temperaturaa_min.destinationName = "SusanaMarques/feeds/TemperatureMin";
      client.send(temperaturaa_min);

    humidadee = new Paho.MQTT.Message(" "+humidity);
    humidadee.destinationName = "SusanaMarques/feeds/Humidity";
    client.send(humidadee);


    uvii = new Paho.MQTT.Message(" "+uvi);
    uvii.destinationName = "SusanaMarques/feeds/UV";
    client.send(uvii);
  }
