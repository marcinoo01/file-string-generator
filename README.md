# file-string-generator
# java 17
Application generates a file with random unique strings after entering specified endpoint. User of api has to specify from what charaters the strings
are about to be made, min and max size of strings and amount of them. Project uses webflux, and embedded mongo-db for storing payloads.

Three endpoints are specified:

# url: api/v1/payloads
  usage: determines if possible to generate declared amount of strings then generates a file, and fullfil it with desired amount of strings otherwise throws exception<br>
  request type: POST<br>
  expected response: 201<br>
  example request: <img width="1014" alt="Screenshot 2022-10-24 at 20 26 35" src="https://user-images.githubusercontent.com/85891362/197598770-b6ad963e-cabe-461d-8bb5-fb5e5d9da49b.png">
  generated file: <img width="1372" alt="Screenshot 2022-10-24 at 20 27 20" src="https://user-images.githubusercontent.com/85891362/197598779-c8db362c-3e59-4ea7-a290-b232a05c0f99.png">
# url: api/v1/payloads
  usage: checks how many files are currently being written<br>
  request type: GET<br>
  expected response: 200<br>
  example request with 3 running task in parallel: 
![Untitled](https://user-images.githubusercontent.com/85891362/197617561-cb593f89-ac0b-4477-8499-32183a781c28.png)
# url: api/v1/payloads/{id}
  usage: gets details about desired payload<br>
  request type: GET<br>
  expected response: 200<br>
  example request: <img width="1028" alt="Screenshot 2022-10-24 at 22 04 30" src="https://user-images.githubusercontent.com/85891362/197618453-90879f28-9a3a-45b9-bedb-b5ded216be6e.png">
