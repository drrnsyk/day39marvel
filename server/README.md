# Setup

## Marvel API

Requires a public key which is also the api key and a private key
Params required for the api query string: ts - long string timestamp, public key and a hash (md5(ts+privateKey+publicKey))
**Set enviroment variables for public key and private key**
Some basic Git commands are:
```
set PUBLIC_KEY=<publicKey>
set PRIVATE_KEY=<publicKey>
```
For deployment, add it into the enviroment variables