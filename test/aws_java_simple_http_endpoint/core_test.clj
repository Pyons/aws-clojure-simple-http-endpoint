(ns aws-java-simple-http-endpoint.core-test
  (:require
   [aws-clojure-simple-http-endpoint.core :refer [-handleRequest
                                                  lambda-integration-response]]
   [clojure.data.json :as json]
   [clojure.string :as str]
   [clojure.test :refer [deftest is testing]]))

(deftest lambda-integration-reponse-test
  (testing "Test lambda-integration-response"
    (let [result (lambda-integration-response "Hello" 200)]
      (is (= {"Content-Type" "application/json", "Access-Control-Allow-Origin" "*"} (.getHeaders result)))
      (is (= "Hello" (.getBody result)))
      (is (= 200 (.getStatusCode result))))))

(deftest handlne-request-test
  (testing "Test handle-request"
    (let [result (-handleRequest nil nil nil)]
      (is (= {"Content-Type" "application/json", "Access-Control-Allow-Origin" "*"} (.getHeaders result)))
      (is (str/starts-with? (-> (.getBody result) json/read-str (get "message")) "Hello" ))
      (is (= 200 (.getStatusCode result))))))
