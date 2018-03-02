# ses

Simple Encryption Service - Webservice to expose AES-256 symmetric encryption (CBC) as a service. Online AES encryption HEX-based.

[![CircleCI](https://circleci.com/gh/anandchakru/ses.svg?style=svg)](https://circleci.com/gh/anandchakru/ses)


## Demo hosted on `heroku`

  * Encrypt FooBar with BJsP3xAX9Qq6G5D6 as password using a [HTTP.GET](http://still-hamlet-17048.herokuapp.com/e/FooBar/BJsP3xAX9Qq6G5D6)
  * Decrypt the above using a [HTTP.GET](http://still-hamlet-17048.herokuapp.com/d/cb227bcec3fb608de8a58988701c6977/fa6190fc6cfcee1ffdc9ada1b788dd4c/BJsP3xAX9Qq6G5D6)
  * You can use HTTP.POST like below
    * Encryption
    `curl -d '{"payload":"FooBar", "pwd":"BJsP3xAX9Qq6G5D6"}' -H "Content-Type: application/json" -X POST http://still-hamlet-17048.herokuapp.com/el`
    * Decryption
    `curl -d '{"payload":"eb0fa49228d2d4c1b5f59a263b561c1e", "iv":"73a3aca1cebe98b2f7ba81a713b56681", "pwd":"BJsP3xAX9Qq6G5D6"}' -H "Content-Type: application/json" -X POST http://still-hamlet-17048.herokuapp.com/dl`


## Running Locally

Make sure you have Java and Maven installed.  Also, install the [Heroku CLI](https://cli.heroku.com/).

```sh
$ git clone https://github.com/anandchakru/ses.git
$ cd ses
$ mvn install
$ heroku local:start
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

## Deploying to Heroku

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

For more information about using Java on Heroku, see these Dev Center articles:

- [Java on Heroku](https://devcenter.heroku.com/categories/java)

```sh
$ git remote add heroku https://git.heroku.com/still-hamlet-17048.git
$ git push heroku master
$ heroku open
```

## Deploying to a new Heroku instance

```
$ git clone https://github.com/anandchakru/ses.git
$ heroku create
$ git remote -v
#heroku	https://git.heroku.com/murmuring-retreat-23440.git (fetch)
#heroku	https://git.heroku.com/murmuring-retreat-23440.git (push)
#origin	https://github.com/anandchakru/ses.git (fetch)
#origin	https://github.com/anandchakru/ses.git (push)
$ git push heroku master
$ heroku open

```
