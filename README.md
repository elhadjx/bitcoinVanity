# Bitcoin Vanity
## Custom Bitcoin Address Generator


[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Bitcoin Vanity is a local bitcoin address generator, it uses multithreading to 
brute-froce the generation of bitcoin private keys and calculates their address
to search for the desired address.


## Args

- Desired String to start with ( the longer it is the more time it requires )
- Case sensitive boolean
- Threads number integer



## Tech

- [JAVA] - Clearly!
- [BouncyCastle] - v 15

## How To Use
- Desired Start of Btc Address: hadj
- Case sensitive: false
- Threads: 20
### Input:
```sh
java -jar bitcoinVanity.jar hadj false 20
```
### Result:
```sh
**************
Private Key: 5CC57DDC00CA635930F829B422EF0B47A01AD8C41B96BA58A57305E872CC243C
Public Key: 0493FBDEDBF979C1FD7949B617D6A75EE9B5D980B7049045E0F7610138AC4D8DBDA16618B86B52D5FFB
            4965214D86929A1B585E4A85EE2625730339EF2AD4CFB2F
Address: 1HAdJSEAAbo5BECBG5mFw8imZTcMjBKSwT
```


## Donations
Btc: bc1q48s6asjrp4tqthnyhhwswwxkkyqjw7jq5fd6lv

<h3 align="left">Support:</h3>
<p><a href="https://www.buymeacoffee.com/elhadjx"> <img align="left" src="https://cdn.buymeacoffee.com/buttons/v2/default-yellow.png" height="50" width="210" alt="elhadjx" /></a></p><br><br>
