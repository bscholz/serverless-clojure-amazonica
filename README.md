# serverless-clojure-amazonica

Example of an AWS Lambda function using Amazonica.

## Requirements

* [leiningen](leiningen.org)
* [serverless](serverless.com)

## Build

    lein uberjar

## Deploy

    serverless deploy

## Test

    serverless invoke -f countLines --data "{\"files\": [{\"bucket\": \"mybucket\", \"key\": \"mykey\"}]}"

## Remove

    serverless remove
