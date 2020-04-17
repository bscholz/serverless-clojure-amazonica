(ns serverless-clojure-amazonica.handler
  (:require [clojure.string :refer [split-lines]]
            [amazonica.aws.s3 :as s3])
  ;; this method declaration makes the AWS SDK magically tranform JSON
  ;; into Java data structures (Map, List, String, etc.)
  (:gen-class :methods [^:static [handle [java.util.Map] String]]))

(defn s3-read-lines [bucket key]
  (-> (s3/get-object bucket key)
      :input-stream
      slurp
      split-lines))

(defn clojurify [obj]
  (cond
    (instance? java.util.Map obj) (into {} (for [[k v] obj] [(keyword k) (clojurify v)]))
    (instance? java.util.List obj) (into [] (map clojurify obj))
    :else obj))

(defn -handle
  "Counts the lines of the given s3 files.
  Expects JSON input of the form {\"files\": [{\"bucket\": \"mybucket\", \"key\": \"mykey\"}]}"
  [java-map-event]
  (let [event (clojurify java-map-event)
        {:keys [files]} event
        counts (map #(count (s3-read-lines (:bucket %) (:key %))) files)
        lines (reduce + counts)]
    (str lines " lines in " (count counts) " files")))
