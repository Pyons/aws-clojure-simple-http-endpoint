(ns aws-clojure-simple-http-endpoint.core
  (:gen-class
   :implements [com.amazonaws.services.lambda.runtime.RequestHandler])
  (:import [com.amazonaws.services.lambda.runtime.events APIGatewayProxyResponseEvent])
  (:require [clj-time.format :as f]
            [clj-time.core :as t]
            [clojure.data.json :as json]))

(defn lambda-integration-response [^String msg ^long status-code]
  "Wraps message into lambda integration response object"
  (doto (new APIGatewayProxyResponseEvent)
    (.setStatusCode (int status-code))
    (.setHeaders {"Content-Type" "application/json" "Access-Control-Allow-Origin" "*"})
    (.setBody msg)))

(defn -handleRequest
  "Returns the current time"
  [this input context]
  (let [current-time (->> (t/now) (f/unparse (f/formatters :rfc822)))
        msg (json/write-str {:message (str "Hello, the current time is, " current-time)})]
    (lambda-integration-response msg 200)))
