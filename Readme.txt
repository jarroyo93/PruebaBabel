El proceso de automatización  fue construído con selenium, TesNG la librería POI para lectura del archivo Excel.
Se utilizó un solo caso de prueba que usa una sola instacia del navegador de google chrome para ejecutar toda la data incluída en el Excel.

Por lo anterior se tuvo en cuenta lo siguiente:

-En el excel se encuentran 3 hojas, que corresponden a
	Hoja Data: Donde se encuentra toda la data que el caso de prueba a usar para ejecutar los escenarios. 
	Se solicita mantener los formatos para que no haya inconveniente a la hora de cargar la data a los campos de la calculadora
	También es necerio no dejar espacio en blanco entre un conjunto de datos y otros, ni celdas en blanco ya que la calculadora los toma como 0 en cualquiera de sus Builts
	Adicional se debe mantener el documento de Excel cerrado para que el test corra de manera exitosa.
	
	Hoja Parameters: Esta hoja facilita agregar nueva data teniendo en cuenta los parámetros exclusivos de cada campo en la calculadora.
	
	Hoja Errors: Estos erros fueron creados para validad no solo el resultado sino también el comportamiento de los campos.

-Del Excel no solo se extrae data, si no qu también sirve como lmacenamiento de resultados y de errores encontrados en cada caso. Estos se consignan en las columnas
	Obtained Result	Test Result y Erros Codes. Se hizo de esta forma para que la evidencia fuera inmediata y se pudieran comprar los resultados de manera eficiente.


	


