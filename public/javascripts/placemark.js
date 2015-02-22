ymaps.ready(init);

function init () {
	var myMap = new ymaps.Map("map", {
		center: [50.4505, 30.523],
		zoom: 10
	});
		
	var onlinePrinters = new ymaps.GeoObjectCollection();
	var offlinePrinters = new ymaps.GeoObjectCollection();
	
	var printers = [
		{id : 100, coords : [50.3505, 30.423], name : "Andrew's printer", online : true /*, other fields */},
		{id : 101, coords : [50.3505, 30.623], name : "Artem's printer", online : true /*, other fields */},
		{id : 102, coords : [50.5505, 30.423], name : "Sasha's printer", online : false /*, other fields */},
		{id : 103, coords : [50.5505, 30.623], name : "Vitya's printer", online : false /*, other fields */}
	];
	
	for (var printer of printers) {
		if (printer.id % 2 == 0) {
			myPlacemark = new ymaps.Placemark(printer.coords, {
				balloonContent: '<b>' + printer.name +'</b><br><a href="http://www.i.ua">Click to print something!</a>',
				hintContent: (printer.online ? 'ONline' : 'OFFline') + ' printer mark with balloon'
			}, {
				preset: 'islands#icon',
				iconColor: printer.online ? 'blue' : 'red'
			});
		} else {
			myPlacemark = new ymaps.Placemark(printer.coords, {
				iconContent: printer.name,
				hintContent: (printer.online ? 'ONline' : 'OFFline') + ' printer mark withOUT balloon'
			}, {
				preset: printer.online ? 'islands#blueStretchyIcon' : 'islands#redStretchyIcon',
				//iconColor: '#0095b6'
			});
			myPlacemark.events.add('click', function() {
				document.location = "http://www.i.ua";
			});
		}
		
		if (printer.online) {
			onlinePrinters.add(myPlacemark);
		}else{
			offlinePrinters.add(myPlacemark);
		}
	}
	myMap.geoObjects.add(onlinePrinters);
	myMap.geoObjects.add(offlinePrinters);
	
        /*myGeoObject = new ymaps.GeoObject({
            // Описание геометрии.
            geometry: {
                type: "Point",
                coordinates: [55.8, 37.8]
            },
            // Свойства.
            properties: {
                // Контент метки.
                iconContent: '<a href="http://www.i.ua">влюбленной жабы</a>',
                hintContent: 'Ну давай уже тащи'
            }
        }, {
            // Опции.
            // Иконка метки будет растягиваться под размер ее содержимого.
            preset: 'islands#blackStretchyIcon',
            // Метку можно перемещать.
            draggable: true
        });

	myGeoObject.events.add('click', function() {
		myMap.geoObjects.removeAll();
	});*/

    /*myMap.geoObjects
        .add(myPlacemark)
        .add(new ymaps.Placemark([55.684758, 37.738521], {
            balloonContent: 'цвет <strong>воды пляжа бонди</strong>'
        }, {
            preset: 'islands#icon',
            iconColor: '#0095b6'
        }))
        .add(new ymaps.Placemark([55.833436, 37.715175], {
            balloonContent: '<strong>серобуромалиновый</strong> цвет'
        }, {
            preset: 'islands#dotIcon',
            iconColor: '#735184'
        }))
        .add(new ymaps.Placemark([55.687086, 37.529789], {
            balloonContent: 'цвет <a href="http://www.i.ua">влюбленной жабы</a>'
        }, {
            preset: 'islands#circleIcon',
            iconColor: '#3caa3c'
        }))
        .add(new ymaps.Placemark([55.782392, 37.614924], {
            balloonContent: 'цвет <strong>детской неожиданности</strong>'
        }, {
            preset: 'islands#circleDotIcon',
            iconColor: 'yellow'
        }))
        .add(new ymaps.Placemark([55.642063, 37.656123], {
            balloonContent: 'цвет <strong>бисмарк-фуриозо</strong>'
        }, {
            preset: 'islands#icon',
            iconColor: '#a5260a'
        }))
        .add(new ymaps.Placemark([55.826479, 37.487208], {
            balloonContent: 'цвет <strong>фэйсбука</strong>'
        }, {
            preset: 'islands#dotIcon',
            iconColor: '#3b5998'
        }))
        .add(new ymaps.Placemark([55.694843, 37.435023], {
            balloonContent: 'цвет <strong>вконтакте</strong>'
        }, {
            preset: 'islands#circleIcon',
            iconColor: '#4d7198'
        }))
        .add(new ymaps.Placemark([55.790139, 37.814052], {
            balloonContent: 'цвет <strong>твиттера</strong>'
        }, {
            preset: 'islands#circleDotIcon',
            iconColor: '#1faee9'
        }));*/
}
