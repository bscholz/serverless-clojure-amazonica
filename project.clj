(defproject serverless-clojure-amazonica "0.1.0-SNAPSHOT"
  :description "AWS Lambda to analyze meetings"
  :url "http://github.com/bscholz/serverless-clojure-amazonica"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [amazonica "0.3.152" :exclusions [com.amazonaws/aws-java-sdk
                                                   com.amazonaws/amazon-kinesis-client]]
                 ;; note: these aws sdk versions must match amazonica's dependencies
                 [com.amazonaws/aws-java-sdk-core "1.11.763" :exclusions [commons-logging]]
                 [com.amazonaws/aws-java-sdk-s3 "1.11.763" :exclusions [commons-logging]]]
  :profiles {:uberjar {:aot :all
                       :global-vars {*warn-on-reflection* false}}}
  :main serverless-clojure-amazonica.handler)
