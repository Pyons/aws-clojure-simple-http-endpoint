(ns aws-java-simple-http-endpoint.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [aws-clojure-simple-http-endpoint.core :refer [lambda-integration-response -handleRequest]]))

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
      (is (= 200 (.getStatusCode result))))))
