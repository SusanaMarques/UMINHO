function drawChart(data) {
  console.log(data)
  Chart.defaults.global.legend.labels.usePointStyle = true;

  var ctx = document.getElementById("myChart");
  var myChart = new Chart(ctx, {
      type: 'bar',
      data: {
          labels: data.map(value => value.key),
          datasets: [{
              label: 'Ocorrências',
              data: data.map(value => value.counter),
              yAxisID: 'A',
              backgroundColor: ['#a6cee3','#1f78b4','#b2df8a','#33a02c','#fb9a99','#e31a1c','#fdbf6f','#ff7f00','#cab2d6','#6a3d9a']
          }]
      },
      options: {
          legend: {
              display: false,
            position: 'right'
          },
          title: {
            display: true,
            text: 'TOP 10 Teclas mais utilizadas'
          },
          scales: {
            yAxes: [{
                scaleLabel: {
                    labelString: 'Ocorrências',
                    display: true
                },
                gridLines: {
                    display: false
                },
                
                ticks: {
                    beginAtZero: true
                },
                id: 'A',
                type: 'linear',
                position: 'left',
            }],
            xAxes: [{
                gridLines: {
                    display: false
                }
            }]
        }
      }
  });
}

function drawChart2(ref, data) {
    console.log(data)
    Chart.defaults.global.legend.labels.usePointStyle = true;

    let library = ['#a6cee3','#1f78b4','#b2df8a','#33a02c','#fb9a99','#e31a1c','#fdbf6f','#ff7f00','#cab2d6','#6a3d9a']
    let colors = []
    let topCount = ref.map(value => value.key)
    let taps = data.map(value => value.key)

    for(let i = 0; i < topCount.length; i++) {
        if(topCount.indexOf(taps[i]) >= 0) {
            colors[i] = library[topCount.indexOf(taps[i])];
        } else {
            colors[i] = '#eee'
        }
    }
  
    var ctx = document.getElementById("myChart2");
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: data.map(value => value.key),
            datasets: [{
                label: 'Ocorrências',
                data: data.map(value => value.taps),
                backgroundColor: colors
            }]
        },
        options: {
            legend: {
                display: false,
              position: 'right'
            },
            title: {
              display: true,
              text: 'TOP 10 Teclas toque rápido'
            },
            scales: {
                yAxes: [{
                    scaleLabel: {
                        labelString: 'Ocorrências',
                        display: true
                    },
                    ticks: {
                        beginAtZero: true
                    },
                    gridLines: {
                        display: false
                    }
                }],
                xAxes: [{
                    gridLines: {
                        display: false
                    }
                }]
            }
        }
    });
}



function drawChart3(data) {
    console.log(data)
    Chart.defaults.global.legend.labels.usePointStyle = true;

  var ctx = document.getElementById("myChart3");
  var myChart = new Chart(ctx, {
      type: 'radar',
      data: {
          labels: data.map(value => value.key),
          datasets: [{
              label: 'Contador',
              data: data.map(value => value.counter),
              backgroundColor: '#e31a1c77'
          },{
            label: 'Duração',
            data: data.map(value => value.duration / 100),
            backgroundColor: '#a6cee9bb'
          }]
      },
      options: {
          legend: {
              display: false,
            position: 'right'
          },
          title: {
            display: true,
            text: 'TOP 10 Teclas relação Contador/Duração'
          }
          
      }
  });
}

function drawChart4(ref, data) {
    console.log(data)
    Chart.defaults.global.legend.labels.usePointStyle = true;

    let library = ['#a6cee3','#1f78b4','#b2df8a','#33a02c','#fb9a99','#e31a1c','#fdbf6f','#ff7f00','#cab2d6','#6a3d9a']
    let colors = []
    let topCount = ref.map(value => value.key)
    let holds = data.map(value => value.key)

    for(let i = 0; i < topCount.length; i++) {
        if(topCount.indexOf(holds[i]) >= 0) {
            colors[i] = library[topCount.indexOf(holds[i])];
        } else {
            colors[i] = '#eee'
        }
    }
  
    var ctx = document.getElementById("myChart4");
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: data.map(value => value.key),
            datasets: [{
                label: 'Ocorrências',
                data: data.map(value => value.holds),
                backgroundColor: colors
            }]
        },
        options: {
            legend: {
                display: false,
              position: 'right'
            },
            title: {
              display: true,
              text: 'Top 10 Teclas com toques longos'
            },
            scales: {
                yAxes: [{
                    scaleLabel: {
                        labelString: 'Ocorrências',
                        display: true
                    },
                    ticks: {
                        beginAtZero: true
                    },
                    gridLines: {
                        display: false
                    }
                }],
                xAxes: [{
                    gridLines: {
                        display: false
                    }
                }]
            }
        }
    });
  }