(ns aws-clojure-simple-http-endpoint.core
  (:gen-class
    :implements [com.amazonaws.services.lambda.runtime.RequestHandler])
  (:import com.amazonaws.services.lambda.runtime.Context
           com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
           java.util.Map
           java.time.ZonedDateTime
           java.time.format.DateTimeFormatter)
  (:require [clojure.data.json :as json]))

(defn lambda-integration-response
  "Wraps message into lambda integration response object"
  [^String msg ^long status-code]
  (doto (new APIGatewayProxyResponseEvent)
    (.setStatusCode (int status-code))
    (.setHeaders {"Content-Type" "application/json" "Access-Control-Allow-Origin" "*"})
    (.setBody msg)))

(defn -handleRequest
  "Returns the current time"
  [_this ^Map _input ^Context _context]
  (let [current-time (->> (ZonedDateTime/now) (.format DateTimeFormatter/RFC_1123_DATE_TIME))
        msg (json/write-str {:message (str "Hello, the current time is, " current-time)})]
    (lambda-integration-response msg 200)))
