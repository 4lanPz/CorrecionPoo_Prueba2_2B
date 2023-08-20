# CorrecionPoo_Prueba2_2B
Alan Perez

Primero al crear el GUI FORM le agregamos los botones, textpanels, etc. Que luego lo vamos a programar
 
Aqui podemos ver como se llaman los botones, panels, combobox,etc y a demas he declarado los datos que se va a usar para la conexion como servidor, base de datos, usuario, contraseña y certificado que necesita SQL Server.

Para que funcione el programa tambien necesitaremos el driver de SQL Server que se descarga desde la pagina de Microsoft y que debemos poner en las librerias del proyecto
 
Ahora ya con estos datos ya podemos empezar a codificar los botones en los cuales utilizaremos los datos que se envien desde la ventana java y luego con un try catch, realizaremos la conexion en la cual establecemos el Query que va a tener parametros   ?   que luego los enviaremos con Statement.setInt o setString segun sea requerido, con esto enviaremos el query dependiendo de lo que necesitemos, como en este caso se usa Insert Into Datos 


En este caso es lo mismo pero con Update necesitamos indicar un valor que indique donde se va a actualizar los datos, en este caso se utiliza otra variable en la cual se indica el ID, con el cual vamos a indicar que todos los datos que tengan ese ID van a cambiar, esto con el query Update que tiene la palabra   WHERE 

En este caso es igual pero en este caso cambiaria de donde se obtienen los datos porque necesitamos getSelectedItem, de alli el codigo sigue siendo igual que   select   y   where  

 

 

 

 

 
