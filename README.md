# symetric-springboot
this project describe how to encrypt and decrypt message using java cryptoraphy and spring boot

How to encrypt and decrypt message using secret key (symetric) in spring boot app
Prerequisite
1. You understand concept symetric encryption
2. You know, how to create api using spring boot
3. You know how Config springboot with database mysql/postgresql
4. You know how to create secret key, create salt(ivParameterSpec)(if not, see my previous tutorial=https://www.youtube.com/watch?v=u945ILRPrL0)


In this tutorial i will use this scenario
1. client send sensitiv data to server(i use postman as my client app)
2. before send data to the server, sensitiv data encrypt using secret key(in this example the sensitiv datas are phone number and identity number)
3. when data receive by server decrypt the data using secret key
4. store data into database
5. this behaviour(encrypt and decrypt data) prevent man in the middle attack
NOTE
**client send secret key to server(by email)**
**store secret key in application.properties or database**

# Let's Jump right in

A. CREATE SECRET KEY

	1. Create secretKey Key using AES algorithm and create salt(ivParameterSpec)
	
	2. encode secret key and ivParameterSpec using base 64
	
	3. send secret key to server and store in database/application.properties(use the secret key to decode data, when sensitiv data send by client)
	STORE SECRET KEY AND SALT FOR ENCRYPT, DECRYPT
	Secret Key encode>>> CVrCuABDW0jSWN1SwwE/EBOzIThFPey923GM6BPZoVs=
	ivParameterSpec Encode>> FQucujsS74/QIbfg4vuf5g==
	
B. STRUCTURE APPLICATION
	
	1. see my package structure
	
	2. create class Users
	
	3. create class UsersController
	
	4. create UserRepository interface
	
	5. create class Symetricservice
	
	6. config database in application.properties
	
	7. config secret key and salt which send by client
	
C. ENCRYPT DATA USING SECRET KEY AND SALT
	
	1. client encrypt sensitiv data using secret key and ivParameterSpec
	
	2. send request using post man
	
		- header send salt(ivParameterSpec)
	
		- send phone number already encrypt
	
		- send identity number which already encrypt
	
	3. run the app
	
	4. send request, check data in database
	
	5. the data succeed decrypted and the data which save is plain text


	NOTE : the salt/ivParameterSpec is send by client not stored in properties file
		   in the real app, salt always change(generated by client app)

	Happy learning and happy sharing!!!
	
	you can fork this tutorial on my github
